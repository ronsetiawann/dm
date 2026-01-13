package com.marketdata.market_data_service.cacp.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.marketdata.market_data_service.cacp.dto.BaseCADTO;
import com.marketdata.market_data_service.cacp.entity.BaseCAEntity;
import com.marketdata.market_data_service.cacp.repository.BaseCARepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.regex.Pattern;

@Service
@Slf4j
@RequiredArgsConstructor
public class GenericCACorporateActionService {

    private final ObjectMapper objectMapper;

    @Value("${WebTrading.DataEnableCACP:true}")
    private boolean dataEnableCacp;

    private static final Pattern STOCK_ID_PATTERN = Pattern.compile("[^A-Z0-9-/]+");

    @Async
    public <E extends BaseCAEntity, D extends BaseCADTO> CompletableFuture<ResponseEntity<String>> getCorporateAction(
            String caType,
            BaseCARepository<E> repository,
            Function<E, D> mapper,
            CAFilterParams params,
            boolean isJSON) {

        try {
            if (!dataEnableCacp) {
                return CompletableFuture.completedFuture(
                        ResponseEntity.ok("Feature Not Enabled")
                );
            }

            List<D> data;

            // If code is provided, get by IDs
            if (params.getCode() != null && !params.getCode().isEmpty()) {
                String[] ids = params.getCode().split("\\|");
                List<Long> idList = Arrays.stream(ids)
                        .map(id -> {
                            try {
                                return Long.parseLong(id.trim());
                            } catch (NumberFormatException e) {
                                return null;
                            }
                        })
                        .filter(java.util.Objects::nonNull)
                        .collect(Collectors.toList());

                data = repository.findAllById(idList).stream()
                        .map(mapper)
                        .collect(Collectors.toList());
            } else {
                // Get ALL entities, then filter in memory
                List<E> entities = repository.findAll();

                // Apply filters in memory (because stockGroup and dateKey are @Transient)
                entities = filterInMemory(entities, params);

                // Sort by dateKey (descending - newest first)
                entities.sort((a, b) -> {
                    LocalDateTime dateA = a.getDateKey() != null ? a.getDateKey() : LocalDateTime.MIN;
                    LocalDateTime dateB = b.getDateKey() != null ? b.getDateKey() : LocalDateTime.MIN;
                    return dateB.compareTo(dateA);
                });

                // Apply pagination
                int startIdx = params.getPage() * params.getSize();
                int endIdx = Math.min(startIdx + params.getSize(), entities.size());

                if (startIdx < entities.size()) {
                    entities = entities.subList(startIdx, endIdx);
                }

                data = entities.stream()
                        .map(mapper)
                        .collect(Collectors.toList());
            }

            String result = formatResponse(data, isJSON);

            return CompletableFuture.completedFuture(
                    ResponseEntity.ok()
                            .header("Content-Type",
                                    isJSON ? "application/json" : "application/javascript")
                            .body(result)
            );

        } catch (Exception e) {
            //log.error("Error in getCorporateAction for {}", caType, e);
            return CompletableFuture.completedFuture(
                    ResponseEntity.internalServerError()
                            .body(createErrorResponse(e))
            );
        }
    }

    /**
     * Filter entities in memory (stockGroup and dateKey are @Transient)
     */
    private <E extends BaseCAEntity> List<E> filterInMemory(List<E> entities, CAFilterParams params) {
        List<E> filtered = new ArrayList<>();

        String cleanedStockID = null;
        if (params.getStockID() != null && !params.getStockID().isEmpty()) {
            cleanedStockID = cleanStockID(params.getStockID()).toUpperCase();
        }

        for (E entity : entities) {
            // Check stockGroup filter
            if (cleanedStockID != null) {
                String entityStockGroup = entity.getStockGroup();
                if (entityStockGroup == null ||
                        !entityStockGroup.toUpperCase().equals(cleanedStockID)) {
                    continue;
                }
            }

            // Check startDate filter
            if (params.getStartDate() != null) {
                LocalDateTime entityDate = entity.getDateKey();
                if (entityDate == null || entityDate.isBefore(params.getStartDate())) {
                    continue;
                }
            }

            // Check endDate filter
            if (params.getEndDate() != null) {
                LocalDateTime entityDate = entity.getDateKey();
                if (entityDate == null || entityDate.isAfter(params.getEndDate())) {
                    continue;
                }
            }

            filtered.add(entity);
        }

        return filtered;
    }

    private String formatResponse(List<?> data, boolean isJSON) throws Exception {
        String jsonArray = objectMapper.writeValueAsString(data);
        return isJSON ? jsonArray : "ca(" + jsonArray + ");";
    }

    private String createErrorResponse(Exception e) {
        try {
            return objectMapper.writeValueAsString(
                    new ErrorResponse(e.getMessage())
            );
        } catch (Exception ex) {
            return "{\"error\":\"Internal Server Error\"}";
        }
    }

    private String cleanStockID(String stockID) {
        if (stockID == null) return "";

        String cleaned = STOCK_ID_PATTERN.matcher(stockID.split("-")[0]).replaceAll("");

        if ("GOTOM".equals(cleaned) || "GOTOMNG".equals(cleaned) || "GOTOMTN".equals(cleaned)) {
            cleaned = "GOTO";
        }

        String result = cleaned.toUpperCase();
        if (result.length() > 8) {
            return result.substring(0, 4);
        }
        return result;
    }

    public static class CAFilterParams {
        private String code;
        private String stockID;
        private Integer page;
        private Integer size;
        private LocalDateTime startDate;
        private LocalDateTime endDate;

        public CAFilterParams(String code, String stockID, Integer page, Integer size,
                              LocalDateTime startDate, LocalDateTime endDate) {
            this.code = code;
            this.stockID = stockID;
            this.page = page != null && page >= 0 ? page : 0;
            this.size = size != null && size > 0 ? size : 100;
            this.startDate = startDate;
            this.endDate = endDate;
        }

        public String getCode() { return code; }
        public String getStockID() { return stockID; }
        public Integer getPage() { return page; }
        public Integer getSize() { return size; }
        public LocalDateTime getStartDate() { return startDate; }
        public LocalDateTime getEndDate() { return endDate; }
    }

    private record ErrorResponse(String error) {}
}
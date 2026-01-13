package com.marketdata.market_data_service.cacp.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.marketdata.market_data_service.cacp.dto.CompanyProfileDTO;
import com.marketdata.market_data_service.cacp.entity.CompanyProfileEntity;
import com.marketdata.market_data_service.cacp.repository.CompanyProfileRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Service with dual-format support (HTML legacy + JSON new)
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class CompanyProfileService {

    private final CompanyProfileRepository repository;
    private final ObjectMapper objectMapper;

    @Cacheable(value = "companyProfiles", key = "'all'")
    public List<CompanyProfileDTO> getAllProfiles() {
        //log.info("Fetching all company profiles from database");
        return repository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public List<CompanyProfileDTO> getProfilesByCodes(List<String> codes) {
        List<String> upperCodes = codes.stream()
                .map(String::toUpperCase)
                .collect(Collectors.toList());

        return repository.findByCodeIn(upperCodes).stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public CompanyProfileDTO getProfileByCode(String code) {
        return repository.findById(code.toUpperCase())
                .map(this::toDTO)
                .orElse(null);
    }

    /**
     * Convert Entity to DTO - handles both HTML and JSON formats
     */
    private CompanyProfileDTO toDTO(CompanyProfileEntity entity) {
        // Parse share_holders to generate info and history
        JsonNode shareHolder = parseJson(entity.getShareHolders());
        JsonNode shareHolderInfo = generateShareHolderInfo(shareHolder);
        JsonNode shareHolderHistory = generateShareHolderHistory(shareHolder);

        return CompanyProfileDTO.builder()
                .stockID(entity.getCode())
                .address(transformAddress(entity.getAddress()))
                .background(entity.getBackground())
                .business(entity.getBusinessCpProduct())
                .subsidiary(parseJson(entity.getSubsidiaryAffiliation()))
                .commissioners(transformCommissioners(entity.getCommissioners()))
                .directors(transformDirectors(entity.getDirectors()))
                .underwriter(transformUnderwriter(entity.getUnderwriter()))
                .shareRegistrar(transformShareRegistrar(entity.getShareRegistrar()))
                .shareHolder(shareHolder)
                .shareHolderInfo(shareHolderInfo)
                .shareHolderHistory(shareHolderHistory)
                .historyStocks(parseJson(entity.getHistoryStocks()))
                .otherInfos(entity.getOtherInfos())
                .build();
    }

    /**
     * Transform address - handles both formats:
     * NEW: {"alamatPerusahaan": "...", "email": "...", "phone": "..."}
     * OLD: HTML table format
     */
    private JsonNode transformAddress(String addressStr) {
        JsonNode parsed = parseJson(addressStr);
        if (parsed == null) return null;

        // Check if new JSON format (has "alamatPerusahaan" field)
        if (parsed.isObject() && parsed.has("alamatPerusahaan")) {
            // Transform to array format expected by frontend
            ArrayNode array = objectMapper.createArrayNode();
            ObjectNode addr = objectMapper.createObjectNode();

            addr.put("Label", "Head Office");
            addr.put("Address", parsed.path("alamatPerusahaan").asText(""));
            addr.put("Phone", parsed.path("phone").asText("-"));
            addr.put("Fax", parsed.path("fax").asText("-"));
            addr.put("Email", parsed.path("email").asText("-"));
            addr.put("Website", parsed.path("website").asText("-"));

            array.add(addr);
            return array;
        }

        // Already in correct format or HTML (return as-is)
        return parsed;
    }

    /**
     * Transform commissioners - add empty text field if missing
     */
    private JsonNode transformCommissioners(String comStr) {
        JsonNode parsed = parseJson(comStr);
        if (parsed == null || !parsed.isArray()) return parsed;

        ArrayNode result = objectMapper.createArrayNode();
        for (JsonNode node : parsed) {
            if (node.isObject()) {
                ObjectNode obj = (ObjectNode) node;
                if (!obj.has("text")) obj.put("text", "");
                result.add(obj);
            }
        }
        return result;
    }

    /**
     * Transform directors - add empty text field if missing
     */
    private JsonNode transformDirectors(String dirStr) {
        JsonNode parsed = parseJson(dirStr);
        if (parsed == null || !parsed.isArray()) return parsed;

        ArrayNode result = objectMapper.createArrayNode();
        for (JsonNode node : parsed) {
            if (node.isObject()) {
                ObjectNode obj = (ObjectNode) node;
                if (!obj.has("text")) obj.put("text", "");
                result.add(obj);
            }
        }
        return result;
    }

    /**
     * Transform underwriter - handle both comma-separated string and JSON array
     */
    private JsonNode transformUnderwriter(String uwStr) {
        if (uwStr == null || uwStr.trim().isEmpty()) return null;
        // Try parse as JSON first
        JsonNode parsed = parseJson(uwStr);
        if (parsed != null && parsed.isArray()) {
            return parsed;
        }
        // Fallback: comma-separated string
        ArrayNode array = objectMapper.createArrayNode();
        String[] parts = uwStr.split(",");
        for (String part : parts) {
            if (part.trim().length() > 0) {
                ObjectNode obj = objectMapper.createObjectNode();
                obj.put("Label", part.trim());
                array.add(obj);
            }
        }
        return array.size() > 0 ? array : null;
    }

    /**
     * Transform share registrar - wrap string in array if needed
     */
    private JsonNode transformShareRegistrar(String srStr) {
        if (srStr == null || srStr.trim().isEmpty()) return null;

        // Try parse as JSON first
        JsonNode parsed = parseJson(srStr);
        if (parsed != null && parsed.isArray()) {
            return parsed;
        }

        // Fallback: single string
        ArrayNode array = objectMapper.createArrayNode();
        ObjectNode obj = objectMapper.createObjectNode();
        obj.put("Label", srStr.trim());
        array.add(obj);
        return array;
    }

    /**
     * Generate shareHolderInfo from shareHolder data
     * Looks for "Additional Information" section
     */
    private JsonNode generateShareHolderInfo(JsonNode shareHolder) {
        if (shareHolder == null || !shareHolder.isArray()) return null;

        ArrayNode result = objectMapper.createArrayNode();
        boolean inAdditionalInfo = false;

        for (JsonNode node : shareHolder) {
            String text = node.path("text").asText("");

            if ("Additional Information".equals(text)) {
                inAdditionalInfo = true;
                continue;
            }
            if ("Monthly Number of Shareholders".equals(text)) {
                break; // End of Additional Information section
            }
            if (inAdditionalInfo && node.has("sharesPrct")) {
                ObjectNode info = objectMapper.createObjectNode();
                info.put("text", "");
                info.put("infoName", node.path("shareholder").asText(""));
                info.put("shares", node.path("shares").asText(""));
                info.put("sharesPrct", node.path("sharesPrct").asDouble(0) / 100.0);
                result.add(info);
            }
        }
        return result.size() > 0 ? result : null;
    }

    /**
     * Generate shareHolderHistory from shareHolder data
     * Looks for "Monthly Number of Shareholders" section
     */
    private JsonNode generateShareHolderHistory(JsonNode shareHolder) {
        if (shareHolder == null || !shareHolder.isArray()) return null;

        ArrayNode result = objectMapper.createArrayNode();
        boolean inHistory = false;

        for (JsonNode node : shareHolder) {
            String text = node.path("text").asText("");

            if ("Monthly Number of Shareholders".equals(text)) {
                inHistory = true;
                continue;
            }

            if (inHistory && node.has("shareholder")) {
                ObjectNode history = objectMapper.createObjectNode();
                history.put("text", "");
                history.put("monthYear", node.path("shareholder").asText(""));
                history.put("shareholders", node.path("shares").asText(""));

                // Parse change value
                try {
                    int change = Integer.parseInt(
                            node.path("sharesPrct").asText("0")
                                    .replace("%", "")
                                    .replace("+", "")
                                    .trim()
                    );
                    history.put("change", change);
                } catch (Exception e) {
                    history.put("change", 0);
                }

                result.add(history);
            }
        }

        return result.size() > 0 ? result : null;
    }

    /**
     * Parse JSON string to JsonNode
     */
    private JsonNode parseJson(String jsonStr) {
        if (jsonStr == null || jsonStr.trim().isEmpty() || "[NULL]".equalsIgnoreCase(jsonStr)) {
            return null;
        }

        try {
            return objectMapper.readTree(jsonStr);
        } catch (Exception e) {
            //log.warn("Failed to parse JSON: {}", jsonStr.substring(0, Math.min(100, jsonStr.length())));
            return null;
        }
    }
}

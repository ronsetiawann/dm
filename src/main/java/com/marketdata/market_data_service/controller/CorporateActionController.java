package com.marketdata.market_data_service.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.marketdata.market_data_service.cacp.dto.CompanyProfileDTO;
import com.marketdata.market_data_service.cacp.dto.FundamentalDTO;
import com.marketdata.market_data_service.cacp.dto.FundamentalIndDTO;
import com.marketdata.market_data_service.cacp.service.CompanyProfileService;
import com.marketdata.market_data_service.cacp.service.CorporateActionFacadeService;
import com.marketdata.market_data_service.cacp.service.FundamentalService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Simplified controller using facade service
 * All 11 endpoints use the same pattern - no code duplication
 */
@RestController
@RequestMapping("/Data")
@RequiredArgsConstructor
public class CorporateActionController {

    private final CorporateActionFacadeService facadeService;
    private final CompanyProfileService service;
    private final FundamentalService fundamentalService;
    private final ObjectMapper objectMapper;

    private static final Pattern INVALID_STOCK_PATTERN = Pattern.compile("[\\s/|?]");

    @GetMapping("/caBonus")
    @PostMapping("/caBonus")
    public CompletableFuture<ResponseEntity<String>> getBonus(
            @RequestParam(defaultValue = "") String code,
            @RequestParam(defaultValue = "") String stockID,
            @RequestParam(defaultValue = "0") String isJSONStr,
            @RequestParam(defaultValue = "0") int p,
            @RequestParam(defaultValue = "100") int n,
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {

        return facadeService.getBonus(code, stockID, "1".equals(isJSONStr), p, n, startDate, endDate);
    }

    @GetMapping("/caDvden")
    @PostMapping("/caDvden")
    public CompletableFuture<ResponseEntity<String>> getDividen(
            @RequestParam(defaultValue = "") String code,
            @RequestParam(defaultValue = "") String stockID,
            @RequestParam(defaultValue = "0") String isJSONStr,
            @RequestParam(defaultValue = "0") int p,
            @RequestParam(defaultValue = "100") int n,
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {

        return facadeService.getDividen(code, stockID, "1".equals(isJSONStr), p, n, startDate, endDate);
    }

    @GetMapping("/caSplit")
    @PostMapping("/caSplit")
    public CompletableFuture<ResponseEntity<String>> getSplit(
            @RequestParam(defaultValue = "") String code,
            @RequestParam(defaultValue = "") String stockID,
            @RequestParam(defaultValue = "0") String isJSONStr,
            @RequestParam(defaultValue = "0") int p,
            @RequestParam(defaultValue = "100") int n,
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {

        return facadeService.getSplit(code, stockID, "1".equals(isJSONStr), p, n, startDate, endDate);
    }

    @GetMapping("/caIPO")
    @PostMapping("/caIPO")
    public CompletableFuture<ResponseEntity<String>> getIPO(
            @RequestParam(defaultValue = "") String code,
            @RequestParam(defaultValue = "") String stockID,
            @RequestParam(defaultValue = "0") String isJSONStr,
            @RequestParam(defaultValue = "0") int p,
            @RequestParam(defaultValue = "100") int n,
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {

        return facadeService.getIPO(code, stockID, "1".equals(isJSONStr), p, n, startDate, endDate);
    }

    @GetMapping("/caCvrse")
    @PostMapping("/caCvrse")
    public CompletableFuture<ResponseEntity<String>> getKonversi(
            @RequestParam(defaultValue = "") String code,
            @RequestParam(defaultValue = "") String stockID,
            @RequestParam(defaultValue = "0") String isJSONStr,
            @RequestParam(defaultValue = "0") int p,
            @RequestParam(defaultValue = "100") int n,
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {

        return facadeService.getKonversi(code, stockID, "1".equals(isJSONStr), p, n, startDate, endDate);
    }

    @GetMapping("/caPuExp")
    @PostMapping("/caPuExp")
    public CompletableFuture<ResponseEntity<String>> getPublicExpose(
            @RequestParam(defaultValue = "") String code,
            @RequestParam(defaultValue = "") String stockID,
            @RequestParam(defaultValue = "0") String isJSONStr,
            @RequestParam(defaultValue = "0") int p,
            @RequestParam(defaultValue = "100") int n,
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {

        return facadeService.getPublicExpose(code, stockID, "1".equals(isJSONStr), p, n, startDate, endDate);
    }

    @GetMapping("/caRvrse")
    @PostMapping("/caRvrse")
    public CompletableFuture<ResponseEntity<String>> getReverse(
            @RequestParam(defaultValue = "") String code,
            @RequestParam(defaultValue = "") String stockID,
            @RequestParam(defaultValue = "0") String isJSONStr,
            @RequestParam(defaultValue = "0") int p,
            @RequestParam(defaultValue = "100") int n,
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {

        return facadeService.getReverse(code, stockID, "1".equals(isJSONStr), p, n, startDate, endDate);
    }

    @GetMapping("/caRight")
    @PostMapping("/caRight")
    public CompletableFuture<ResponseEntity<String>> getRight(
            @RequestParam(defaultValue = "") String code,
            @RequestParam(defaultValue = "") String stockID,
            @RequestParam(defaultValue = "0") String isJSONStr,
            @RequestParam(defaultValue = "0") int p,
            @RequestParam(defaultValue = "100") int n,
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {

        return facadeService.getRight(code, stockID, "1".equals(isJSONStr), p, n, startDate, endDate);
    }

    @GetMapping("/caRupsR")
    @PostMapping("/caRupsR")
    public CompletableFuture<ResponseEntity<String>> getRupsResult(
            @RequestParam(defaultValue = "") String code,
            @RequestParam(defaultValue = "") String stockID,
            @RequestParam(defaultValue = "0") String isJSONStr,
            @RequestParam(defaultValue = "0") int p,
            @RequestParam(defaultValue = "100") int n,
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {

        return facadeService.getRupsResult(code, stockID, "1".equals(isJSONStr), p, n, startDate, endDate);
    }

    @GetMapping("/caRupsS")
    @PostMapping("/caRupsS")
    public CompletableFuture<ResponseEntity<String>> getRupsSchedule(
            @RequestParam(defaultValue = "") String code,
            @RequestParam(defaultValue = "") String stockID,
            @RequestParam(defaultValue = "0") String isJSONStr,
            @RequestParam(defaultValue = "0") int p,
            @RequestParam(defaultValue = "100") int n,
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {

        return facadeService.getRupsSchedule(code, stockID, "1".equals(isJSONStr), p, n, startDate, endDate);
    }

    @GetMapping("/caWrant")
    @PostMapping("/caWrant")
    public CompletableFuture<ResponseEntity<String>> getWarrant(
            @RequestParam(defaultValue = "") String code,
            @RequestParam(defaultValue = "") String stockID,
            @RequestParam(defaultValue = "0") String isJSONStr,
            @RequestParam(defaultValue = "0") int p,
            @RequestParam(defaultValue = "100") int n,
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {

        return facadeService.getWarrant(code, stockID, "1".equals(isJSONStr), p, n, startDate, endDate);
    }

    @GetMapping("/RemoveCACaches")
    @PostMapping("/RemoveCACaches")
    public ResponseEntity<String> removeCaches() {
        facadeService.removeCachedData();
        return ResponseEntity.ok("Success");
    }

    /**
     * Examples:
     * - /Data/cp?code=AALI&isJSONStr=1
     * - /Data/cp?code=AALI|BBRI|BBCA&isJSONStr=0
     * - /Data/cp?code=AALI&isJSONStr=1&returnMode=obj
     */
    @GetMapping("/cp")
    @PostMapping("/cp")
    public ResponseEntity<String> getCompanyProfile(
            @RequestParam(defaultValue = "AALI") String code,
            @RequestParam(defaultValue = "0") String isJSONStr,
            @RequestParam(defaultValue = "") String returnMode) {

        try {
            boolean isJSON = "1".equals(isJSONStr);
            List<String> stockIDs = Arrays.stream(code.split("\\|"))
                    .map(String::trim)
                    .map(String::toUpperCase)
                    .collect(Collectors.toList());
            for (String stockID : stockIDs) {
                if (INVALID_STOCK_PATTERN.matcher(stockID).find()) {
                    return createErrorResponse("Invalid StockID", returnMode, HttpStatus.BAD_REQUEST);
                }
            }
            List<CompanyProfileDTO> data = service.getProfilesByCodes(stockIDs);

            String jsonData = objectMapper.writeValueAsString(data);

            if ("obj".equals(returnMode)) {
                String response = String.format("{\"success\":true,\"data\":%s}", jsonData);
                return ResponseEntity.ok()
                        .header("Content-Type", "application/json")
                        .body(response);
            } else {
                String response = isJSON ? jsonData : "cp(" + jsonData + ");";
                String contentType = isJSON ? "application/json" : "application/javascript";
                return ResponseEntity.ok()
                        .header("Content-Type", contentType)
                        .body(response);
            }

        } catch (Exception e) {
            ////log.error("Error in getCompanyProfile", e);
            return createErrorResponse(e.getMessage(), returnMode, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Get all company profiles
     */
    @GetMapping("/cpAll")
    @PostMapping("/cpAll")
    public ResponseEntity<String> getAllCompanyProfiles(
            @RequestParam(defaultValue = "1") String isJSONStr) {
        try {
            boolean isJSON = "1".equals(isJSONStr);
            List<CompanyProfileDTO> data = service.getAllProfiles();
            String jsonData = objectMapper.writeValueAsString(data);
            String response = isJSON ? jsonData : "cp(" + jsonData + ");";
            String contentType = isJSON ? "application/json" : "application/javascript";
            return ResponseEntity.ok()
                    .header("Content-Type", contentType)
                    .body(response);
        } catch (Exception e) {
            ////log.error("Error in getAllCompanyProfiles", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .header("Content-Type", "application/json")
                    .body("{\"error\":\"" + e.getMessage() + "\"}");
        }
    }

    /**
     * Create error response based on returnMode
     */
    private ResponseEntity<String> createErrorResponse(String message, String returnMode, HttpStatus status) {
        try {
            if ("obj".equals(returnMode)) {
                Map<String, Object> error = new HashMap<>();
                error.put("success", false);
                error.put("message", message);
                String json = objectMapper.writeValueAsString(error);
                return ResponseEntity.status(status)
                        .header("Content-Type", "application/json")
                        .body(json);
            } else {
                return ResponseEntity.status(status)
                        .header("Content-Type", "text/plain")
                        .body(message);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("{\"error\":\"Internal Server Error\"}");
        }
    }

    //FUNDAMENTAL
    @GetMapping("/fd")
    public ResponseEntity<String> getFundamental(
            @RequestParam(defaultValue = "AALI:2025:1") String code,
            @RequestParam(defaultValue = "0") String isJSONStr
    ) {
        return processFundamentalRequestEnglish(code, isJSONStr);
    }

    @PostMapping("/fd")
    public ResponseEntity<String> getFundamentalPost(@RequestBody String code) {
        if (code == null || code.trim().isEmpty()) {
            return ResponseEntity.badRequest()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body("{\"error\":\"Invalid Input\"}");
        }
        return processFundamentalRequestEnglish(code.trim(), "1");
    }

    /**
     * Get all fundamental data - English version
     */
    @GetMapping("/fdAll")
    public ResponseEntity<String> getAllFundamentals(
            @RequestParam(defaultValue = "0") String isJSONStr
    ) {
        try {
            //log.info("Getting all fundamental data (English)");

            List<FundamentalDTO> data = fundamentalService.getAllFundamentalsEnglish();

            String json = objectMapper.writeValueAsString(data);
            boolean isJSON = "1".equals(isJSONStr);

            String response = isJSON ? json : "fd(" + json + ");";

            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(response);

        } catch (Exception e) {
            //log.error("Error getting all fundamentals", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body("{\"error\":\"" + e.getMessage() + "\"}");
        }
    }

    @PostMapping("/fdAll")
    public ResponseEntity<String> getAllFundamentalsPost(
            @RequestParam(defaultValue = "0") String isJSONStr
    ) {
        return getAllFundamentals(isJSONStr);
    }

    // ============================================
    // INDONESIAN VERSION
    // ============================================

    /**
     * Get fundamental data by code(s) - Indonesian version
     * Format: STOCKID:TAHUN:KUARTAL
     * Multiple codes separated by |
     * Special cases:
     * - TAHUN=0: ambil 10 tahun terakhir untuk kuartal tersebut
     * - KUARTAL=0: ambil semua kuartal (1-4) untuk tahun tersebut
     * Examples:
     * - /Data/fd-id?code=AALI:2025:1
     * - /Data/fd-id?code=AALI:2025:1|BBCA:2024:4
     * - /Data/fd-id?code=AALI:0:1 (10 tahun terakhir, K1)
     * - /Data/fd-id?code=AALI:2025:0 (semua kuartal tahun 2025)
     */
    @GetMapping("/fd-id")
    public ResponseEntity<String> getFundamentalIndonesia(
            @RequestParam(defaultValue = "AALI:2025:1") String code,
            @RequestParam(defaultValue = "0") String isJSONStr
    ) {
        return processFundamentalRequestIndonesian(code, isJSONStr);
    }

    @PostMapping("/fd-id")
    public ResponseEntity<String> getFundamentalIndonesiaPost(@RequestBody String code) {
        if (code == null || code.trim().isEmpty()) {
            return ResponseEntity.badRequest()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body("{\"error\":\"Input tidak valid\"}");
        }
        return processFundamentalRequestIndonesian(code.trim(), "1");
    }

    /**
     * Get all fundamental data - Indonesian version
     */
    @GetMapping("/fdAll-id")
    public ResponseEntity<String> getAllFundamentalsIndonesia(
            @RequestParam(defaultValue = "0") String isJSONStr
    ) {
        try {
            //log.info("Mengambil semua data fundamental (Indonesian)");

            List<FundamentalIndDTO> data = fundamentalService.getAllFundamentalsIndonesian();

            String json = objectMapper.writeValueAsString(data);
            boolean isJSON = "1".equals(isJSONStr);

            String response = isJSON ? json : "fd(" + json + ");";

            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(response);

        } catch (Exception e) {
            //log.error("Error mengambil semua data fundamental", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body("{\"error\":\"" + e.getMessage() + "\"}");
        }
    }

    @PostMapping("/fdAll-id")
    public ResponseEntity<String> getAllFundamentalsIndonesiaPost(
            @RequestParam(defaultValue = "0") String isJSONStr
    ) {
        return getAllFundamentalsIndonesia(isJSONStr);
    }

    // ============================================
    // PRIVATE HELPER METHODS
    // ============================================

    /**
     * Process fundamental request - English version
     */
    private ResponseEntity<String> processFundamentalRequestEnglish(String code, String isJSONStr) {
        try {
            // Handle POST case where code might be "POST"
            if ("POST".equalsIgnoreCase(code)) {
                return ResponseEntity.badRequest()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body("{\"error\":\"Invalid Input\"}");
            }

            String[] fundamentalCodes = code.split("\\|");
            List<FundamentalDTO> results = new ArrayList<>();

            for (String fundCode : fundamentalCodes) {
                String[] parts = fundCode.split(":");

                if (parts.length != 3) {
                    //log.warn("Invalid code format: {}", fundCode);
                    continue;
                }

                String stockID = parts[0].trim().toUpperCase();

                // Validate stockID
                if (stockID.contains(" ") || stockID.contains("/") ||
                        stockID.contains("|") || stockID.contains("?")) {
                    return ResponseEntity.badRequest()
                            .contentType(MediaType.APPLICATION_JSON)
                            .body("{\"error\":\"Invalid StockID\"}");
                }

                int year = Integer.parseInt(parts[1]);
                int quarter = Integer.parseInt(parts[2]);

                // Special case: year = 0 (get last 10 years)
                if (year == 0) {
                    List<FundamentalDTO> last10Years = fundamentalService.getFundamentalsLast10YearsEnglish(stockID, quarter);
                    results.addAll(last10Years);
                    continue;
                }

                // Special case: quarter = 0 (get all quarters)
                if (quarter == 0) {
                    List<FundamentalDTO> allQuarters = fundamentalService.getFundamentalsAllQuartersEnglish(stockID, year);
                    results.addAll(allQuarters);
                    continue;
                }

                // Normal case
                FundamentalDTO dto = fundamentalService.getFundamentalEnglish(stockID, year, quarter);
                if (dto != null) {
                    results.add(dto);
                }
            }

            // Format response
            String json = objectMapper.writeValueAsString(results);
            boolean isJSON = "1".equals(isJSONStr);

            String response = isJSON ? json : "fd(" + json + ");";

            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(response);

        } catch (NumberFormatException e) {
            //log.error("Invalid number format in code: {}", code, e);
            return ResponseEntity.badRequest()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body("{\"error\":\"Invalid year or quarter format\"}");
        } catch (JsonProcessingException e) {
            //log.error("Error serializing to JSON", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body("{\"error\":\"JSON serialization error\"}");
        } catch (Exception e) {
            //log.error("Error processing fundamental request", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body("{\"error\":\"" + e.getMessage() + "\"}");
        }
    }

    /**
     * Process fundamental request - Indonesian version
     */
    private ResponseEntity<String> processFundamentalRequestIndonesian(String code, String isJSONStr) {
        try {
            // Handle POST case where code might be "POST"
            if ("POST".equalsIgnoreCase(code)) {
                return ResponseEntity.badRequest()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body("{\"error\":\"Input tidak valid\"}");
            }

            String[] fundamentalCodes = code.split("\\|");
            List<FundamentalIndDTO> results = new ArrayList<>();

            for (String fundCode : fundamentalCodes) {
                String[] parts = fundCode.split(":");

                if (parts.length != 3) {
                    //log.warn("Format kode tidak valid: {}", fundCode);
                    continue;
                }

                String stockID = parts[0].trim().toUpperCase();

                // Validate stockID
                if (stockID.contains(" ") || stockID.contains("/") ||
                        stockID.contains("|") || stockID.contains("?")) {
                    return ResponseEntity.badRequest()
                            .contentType(MediaType.APPLICATION_JSON)
                            .body("{\"error\":\"StockID tidak valid\"}");
                }

                int tahun = Integer.parseInt(parts[1]);
                int kuartal = Integer.parseInt(parts[2]);

                // Special case: tahun = 0 (ambil 10 tahun terakhir)
                if (tahun == 0) {
                    List<FundamentalIndDTO> last10Years = fundamentalService.getFundamentalsLast10YearsIndonesian(stockID, kuartal);
                    results.addAll(last10Years);
                    continue;
                }

                // Special case: kuartal = 0 (ambil semua kuartal)
                if (kuartal == 0) {
                    List<FundamentalIndDTO> allQuarters = fundamentalService.getFundamentalsAllQuartersIndonesian(stockID, tahun);
                    results.addAll(allQuarters);
                    continue;
                }

                // Normal case
                FundamentalIndDTO dto = fundamentalService.getFundamentalIndonesian(stockID, tahun, kuartal);
                if (dto != null) {
                    results.add(dto);
                }
            }

            // Format response
            String json = objectMapper.writeValueAsString(results);
            boolean isJSON = "1".equals(isJSONStr);

            String response = isJSON ? json : "fd(" + json + ");";

            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(response);

        } catch (NumberFormatException e) {
            //log.error("Format angka tidak valid pada code: {}", code, e);
            return ResponseEntity.badRequest()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body("{\"error\":\"Format tahun atau kuartal tidak valid\"}");
        } catch (JsonProcessingException e) {
            //log.error("Error serialisasi ke JSON", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body("{\"error\":\"Error serialisasi JSON\"}");
        } catch (Exception e) {
            //log.error("Error memproses request fundamental", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body("{\"error\":\"" + e.getMessage() + "\"}");
        }
    }
}


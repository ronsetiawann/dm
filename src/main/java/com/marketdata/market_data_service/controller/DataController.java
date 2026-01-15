package com.marketdata.market_data_service.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.marketdata.market_data_service.cacp.dto.CompanyProfileDTO;
import com.marketdata.market_data_service.cacp.dto.FundamentalDTO;
import com.marketdata.market_data_service.cacp.dto.FundamentalIndDTO;
import com.marketdata.market_data_service.cacp.dto.NewsDTO;
import com.marketdata.market_data_service.cacp.service.CompanyProfileService;
import com.marketdata.market_data_service.cacp.service.CorporateActionFacadeService;
import com.marketdata.market_data_service.cacp.service.FundamentalService;
import com.marketdata.market_data_service.cacp.service.NewsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class DataController {

    private final CorporateActionFacadeService facadeService;
    private final CompanyProfileService service;
    private final FundamentalService fundamentalService;
    private final ObjectMapper objectMapper;
    private final NewsService newsService;

    private static final Pattern INVALID_STOCK_PATTERN = Pattern.compile("[\\s/|?]");

    @GetMapping("/caBonus")
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
            log.error("Error in getCompanyProfile: code={}, returnMode={}", code, returnMode, e);
            return createErrorResponse(e.getMessage(), returnMode, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Get all company profiles
     */
    @GetMapping("/cpAll")
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
            log.error("Error in getAllCompanyProfiles: isJSONStr={}", isJSONStr, e);
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
            log.error("Error creating error response: message={}, returnMode={}, status={}", message, returnMode, status, e);
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

    public ResponseEntity<String> getFundamentalPost(@RequestBody String code) {
        if (code == null || code.trim().isEmpty()) {
            log.error("Invalid input in getFundamentalPost: code is null or empty");
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
            List<FundamentalDTO> data = fundamentalService.getAllFundamentalsEnglish();
            String json = objectMapper.writeValueAsString(data);
            boolean isJSON = "1".equals(isJSONStr);
            String response = isJSON ? json : "fd(" + json + ");";

            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(response);

        } catch (Exception e) {
            log.error("Error getting all fundamentals: isJSONStr={}", isJSONStr, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body("{\"error\":\"" + e.getMessage() + "\"}");
        }
    }

    public ResponseEntity<String> getAllFundamentalsPost(
            @RequestParam(defaultValue = "0") String isJSONStr
    ) {
        return getAllFundamentals(isJSONStr);
    }

    // ============================================
    // INDONESIAN VERSION
    // ============================================
    @GetMapping("/fd-id")
    public ResponseEntity<String> getFundamentalIndonesia(
            @RequestParam(defaultValue = "AALI:2025:1") String code,
            @RequestParam(defaultValue = "0") String isJSONStr
    ) {
        return processFundamentalRequestIndonesian(code, isJSONStr);
    }

    public ResponseEntity<String> getFundamentalIndonesiaPost(@RequestBody String code) {
        if (code == null || code.trim().isEmpty()) {
            log.error("Invalid input in getFundamentalIndonesiaPost: code is null or empty");
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
            List<FundamentalIndDTO> data = fundamentalService.getAllFundamentalsIndonesian();
            String json = objectMapper.writeValueAsString(data);
            boolean isJSON = "1".equals(isJSONStr);
            String response = isJSON ? json : "fd(" + json + ");";

            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(response);

        } catch (Exception e) {
            log.error("Error getting all fundamentals (Indonesian): isJSONStr={}", isJSONStr, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body("{\"error\":\"" + e.getMessage() + "\"}");
        }
    }

    public ResponseEntity<String> getAllFundamentalsIndonesiaPost(
            @RequestParam(defaultValue = "0") String isJSONStr
    ) {
        return getAllFundamentalsIndonesia(isJSONStr);
    }

    // NEWS
    @GetMapping("/news")
    public ResponseEntity<String> getNews(
            @RequestParam(required = false, defaultValue = "") String stockID,
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "25") int n,
            @RequestParam(required = false, defaultValue = "") String startDate,
            @RequestParam(required = false, defaultValue = "") String endDate,
            @RequestParam(required = false, defaultValue = "0") String isJSONStr
    ) {
        try {
            if (n <= 0) n = 25;
            if (n > 100) n = 100;
            if (page < 0) page = 0;
            List<NewsDTO> result = newsService.getFilteredNews(
                    stockID, startDate, endDate, n, page
            );
            String json = objectMapper.writeValueAsString(result);
            boolean isJSON = "1".equals(isJSONStr);
            String response = isJSON ? json : "news(" + json + ");";

            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(response);
        } catch (Exception e) {
            log.error("Error fetching news: stockID={}, page={}, n={}, startDate={}, endDate={}",
                    stockID, page, n, startDate, endDate, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body("{\"error\":\"" + e.getMessage() + "\"}");
        }
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
                log.error("Invalid POST input detected in processFundamentalRequestEnglish");
                return ResponseEntity.badRequest()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body("{\"error\":\"Invalid Input\"}");
            }

            String[] fundamentalCodes = code.split("\\|");
            List<FundamentalDTO> results = new ArrayList<>();

            for (String fundCode : fundamentalCodes) {
                String[] parts = fundCode.split(":");

                if (parts.length != 3) {
                    log.error("Invalid code format: {}", fundCode);
                    continue;
                }

                String stockID = parts[0].trim().toUpperCase();

                // Validate stockID
                if (stockID.contains(" ") || stockID.contains("/") ||
                        stockID.contains("|") || stockID.contains("?")) {
                    log.error("Invalid StockID detected: {}", stockID);
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
            log.error("Invalid number format in code: {}", code, e);
            return ResponseEntity.badRequest()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body("{\"error\":\"Invalid year or quarter format\"}");
        } catch (JsonProcessingException e) {
            log.error("JSON serialization error for code: {}", code, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body("{\"error\":\"JSON serialization error\"}");
        } catch (Exception e) {
            log.error("Error processing fundamental request: code={}, isJSONStr={}", code, isJSONStr, e);
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
                log.error("Invalid POST input detected in processFundamentalRequestIndonesian");
                return ResponseEntity.badRequest()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body("{\"error\":\"Input tidak valid\"}");
            }

            String[] fundamentalCodes = code.split("\\|");
            List<FundamentalIndDTO> results = new ArrayList<>();

            for (String fundCode : fundamentalCodes) {
                String[] parts = fundCode.split(":");

                if (parts.length != 3) {
                    log.error("Format kode tidak valid: {}", fundCode);
                    continue;
                }

                String stockID = parts[0].trim().toUpperCase();

                // Validate stockID
                if (stockID.contains(" ") || stockID.contains("/") ||
                        stockID.contains("|") || stockID.contains("?")) {
                    log.error("StockID tidak valid: {}", stockID);
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
            log.error("Format angka tidak valid pada code: {}", code, e);
            return ResponseEntity.badRequest()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body("{\"error\":\"Format tahun atau kuartal tidak valid\"}");
        } catch (JsonProcessingException e) {
            log.error("Error serialisasi JSON untuk code: {}", code, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body("{\"error\":\"Error serialisasi JSON\"}");
        } catch (Exception e) {
            log.error("Error memproses request fundamental: code={}, isJSONStr={}", code, isJSONStr, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body("{\"error\":\"" + e.getMessage() + "\"}");
        }
    }

    @GetMapping("/RemoveCaches")
    public ResponseEntity<String> removeAllCaches() {
        try {
            try {
                facadeService.removeCachedData();
                log.info("Corporate Action caches cleared (11 types)");
            } catch (Exception e) {log.error("Failed to clear Corporate Action caches", e);}
            try {
                fundamentalService.clearAllCaches();
                log.info("✓ Fundamental caches cleared (English & Indonesian)");
            } catch (Exception e) {log.error("✗ Failed to clear Fundamental caches", e);}
            try {
                newsService.clearCache();
                log.info("✓ News cache cleared");
            } catch (Exception e) {log.error("✗ Failed to clear News cache", e);}
            try {
                service.clearCache();
                log.info("✓ Company Profile cache cleared");
            } catch (Exception e) {log.error("✗ Failed to clear Company Profile cache", e);}
            return ResponseEntity.ok("Success");
        } catch (Exception e) {
            log.error("Error clearing all caches", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error: " + e.getMessage());
        }
    }
}
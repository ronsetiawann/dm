package com.marketdata.market_data_service.cacp.service;

import com.marketdata.market_data_service.cacp.cache.NewsCache;
import com.marketdata.market_data_service.cacp.dto.NewsDTO;
import com.marketdata.market_data_service.cacp.repository.NewsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Service for news data with in-memory caching
 * Features:
 * - 12-hour cache TTL with auto-refresh
 * - Filter by stockID and date range
 * - Pagination support
 * - Incremental data fetching
 */
@Service
@Slf4j
public class NewsService {

    private final NewsCache cache = new NewsCache();
    private LocalDateTime lastUpdated = LocalDateTime.MIN;
    private final NewsRepository repository;

    private static final int CACHE_HOURS = 12;
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public NewsService(NewsRepository repository) {
        this.repository = repository;
    }

    public List<NewsDTO> getFilteredNews(
            String stockId,
            String startDate,
            String endDate,
            int n,
            int page) {

        log.debug("Getting filtered news: stockId={}, startDate={}, endDate={}, n={}, page={}",
                stockId, startDate, endDate, n, page);

        // Refresh cache if empty or expired
        if (cache.isEmpty() || isCacheExpired()) {
            refreshCache();
        }

        // Apply filters and pagination
        return applyFilters(stockId, startDate, endDate, n, page);
    }

    public void refreshCache() {
        try {
            log.info("Refreshing news cache... Current size: {}, lastNewsId: {}",
                    cache.size(), cache.getLastNewsId());

            // Fetch new news since last update
            long lastId = cache.getLastNewsId();
            List<NewsDTO> fetched = repository.getNews(lastId);

            // Add to cache
            fetched.forEach(cache::add);

            // Update timestamp
            lastUpdated = LocalDateTime.now();

            log.info("Cache refreshed successfully. Total news: {}, New items: {}, Last news ID: {}",
                    cache.size(), fetched.size(), cache.getLastNewsId());

        } catch (Exception e) {
            log.error("Error refreshing news cache", e);
            throw e;
        }
    }

    public void clearCache() {
        cache.clear();
        lastUpdated = LocalDateTime.MIN;
        log.info("News cache cleared completely");
    }

    public Map<String, Object> getCacheStats() {
        Map<String, Object> stats = new HashMap<>();
        stats.put("totalNews", cache.size());
        stats.put("lastNewsId", cache.getLastNewsId());
        stats.put("lastUpdated", lastUpdated);
        stats.put("cacheExpired", isCacheExpired());
        stats.put("cacheAgeDuration", java.time.Duration.between(lastUpdated, LocalDateTime.now()).toString());

        return stats;
    }

    private boolean isCacheExpired() {
        return lastUpdated.plusHours(CACHE_HOURS).isBefore(LocalDateTime.now());
    }

    private List<NewsDTO> applyFilters(
            String stockId,
            String startDate,
            String endDate,
            int n,
            int page) {

        List<NewsDTO> result = new ArrayList<>();

        // Parse date range
        LocalDateTime start = parseDateSafe(startDate, LocalDateTime.MIN);
        LocalDateTime end = parseDateSafe(endDate, LocalDateTime.MAX);

        // Pagination boundaries
        int startIndex = page * n;
        int endIndex = startIndex + n;
        int currentIndex = 0;

        for (NewsDTO news : cache.getCache()) {
            if (!matchesFilters(news, stockId, start, end)) {
                continue;
            }
            currentIndex++;
            if (currentIndex <= startIndex) {
                continue;
            }
            if (currentIndex > endIndex) {
                break;
            }
            result.add(news);
        }

        log.debug("Filtered results: {} items (from {} total after filters)",
                result.size(), currentIndex);

        return result;
    }

    private boolean matchesFilters(
            NewsDTO news,
            String stockId,
            LocalDateTime start,
            LocalDateTime end) {

        // Filter by stockID (case-insensitive)
        if (stockId != null && !stockId.isEmpty()) {
            String companyId = news.getCompanyId();
            if (companyId == null || !stockId.equalsIgnoreCase(companyId.trim())) {
                return false;
            }
        }

        // Filter by start date
        if (start != null && !start.equals(LocalDateTime.MIN)) {
            if (news.getDateTime().isBefore(start)) {
                return false;
            }
        }

        // Filter by end date
        if (end != null && !end.equals(LocalDateTime.MAX)) {
            if (news.getDateTime().isAfter(end)) {
                return false;
            }
        }

        return true;
    }

    private LocalDateTime parseDateSafe(String dateStr, LocalDateTime fallback) {
        if (dateStr == null || dateStr.trim().isEmpty()) {
            return fallback;
        }
        try {
            String dateTimeStr = dateStr.trim() + "T00:00:00";
            return LocalDateTime.parse(dateTimeStr);
        } catch (Exception e) {
            log.warn("Failed to parse date: {}, using fallback", dateStr);
            return fallback;
        }
    }
}

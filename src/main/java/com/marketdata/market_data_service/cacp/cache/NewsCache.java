package com.marketdata.market_data_service.cacp.cache;

import com.marketdata.market_data_service.cacp.dto.NewsDTO;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * In-memory cache for news data
 * Uses Set for deduplication and List for ordered storage
 * Tracks lastNewsId for incremental fetching
 */
public class NewsCache {
    private final Set<Long> cacheSet = new HashSet<>();
    private final List<NewsDTO> cache = new ArrayList<>();
    private long lastNewsId = 0;
    public void clear() {
        cacheSet.clear();
        cache.clear();
        lastNewsId = 0;
    }
    public void add(NewsDTO news) {
        long id = news.getIntNewsId();
        if (id > 0 && !cacheSet.contains(id)) {
            cache.add(news);
            cacheSet.add(id);
            if (id > lastNewsId) {
                lastNewsId = id;
            }
        }
    }

    public List<NewsDTO> getCache() {
        return new ArrayList<>(cache);
    }
    public boolean isEmpty() {
        return cache.isEmpty();
    }
    public int size() {
        return cache.size();
    }
    public long getLastNewsId() {
        return lastNewsId;
    }
    public boolean contains(long newsId) {
        return cacheSet.contains(newsId);
    }
}
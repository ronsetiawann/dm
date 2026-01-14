package com.marketdata.market_data_service.cacp.repository;

import com.marketdata.market_data_service.cacp.dto.NewsDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Repository for accessing news data from database
 * Uses JDBC with PreparedStatement for SQL injection protection
 * GROUP_CONCAT for multi-packet story assembly
 */
@Repository
@Slf4j
public class NewsRepository {

    private final JdbcTemplate jdbcTemplate;

    private static final DateTimeFormatter DATE_FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    public NewsRepository(@Qualifier("primaryJdbcTemplate") JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public List<NewsDTO> getNews(long lastId) {
        try {
            // Set GROUP_CONCAT max length to avoid truncation
            jdbcTemplate.execute("SET SESSION group_concat_max_len = 65536");
            String sql = buildQuery(lastId);
            // Use PreparedStatement via JdbcTemplate with parameter binding
            List<NewsDTO> results;
            if (lastId > 0) {
                results = jdbcTemplate.query(sql, this::mapRow, lastId);
            } else {
                results = jdbcTemplate.query(sql, this::mapRow);
            }
            return results;
        } catch (Exception e) {
            log.error("Error fetching news from database: lastId={}", lastId, e);
            throw e;
        }
    }

    private String buildQuery(long lastId) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ");
        sql.append("    news_id, ");
        sql.append("    MAX(news_date) AS timestamp, ");
        sql.append("    MAX(subject) AS company_id, ");
        sql.append("    MAX(category) AS category, ");
        sql.append("    MAX(headline) AS headline, ");
        sql.append("    GROUP_CONCAT(story ORDER BY num_packet SEPARATOR '') AS story, ");
        sql.append("    MAX(source) AS source, ");
        sql.append("    MAX(sector_id) AS sector_id, ");
        sql.append("    MAX(link) AS link, ");
        sql.append("    MAX(status) AS status ");
        sql.append("FROM news ");

        if (lastId > 0) {
            sql.append("WHERE news_id > ? ");
        }

        sql.append("GROUP BY news_id ");
        sql.append("ORDER BY MAX(news_date) DESC ");
        sql.append("LIMIT 2000");

        return sql.toString();
    }

    private NewsDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        NewsDTO news = new NewsDTO();
        try {
            // Basic fields
            news.setNewsId(rs.getString("news_id"));
            news.setCompanyId(rs.getString("company_id"));
            news.setCategory(rs.getString("category"));
            news.setHeadline(rs.getString("headline"));
            news.setStory(rs.getString("story"));
            // Numeric fields
            news.setSource(rs.getObject("source", Integer.class));
            news.setSectorId(rs.getObject("sector_id", Integer.class));
            news.setStatus(rs.getObject("status", Integer.class));
            // String fields
            news.setLink(rs.getString("link"));
            // Hardcoded country
            news.setCountry("ID");
            // Parse datetime
            String timestampStr = rs.getString("timestamp");
            news.setDateTime(parseDateTime(timestampStr));
            // Parse news_id to long
            news.setIntNewsId(parseNewsId(news.getNewsId()));
        } catch (Exception e) {
            log.error("Error mapping news row: newsId={}", rs.getString("news_id"), e);
        }

        return news;
    }

    private LocalDateTime parseDateTime(String timestampStr) {
        if (timestampStr == null || timestampStr.isEmpty()) {
            return LocalDateTime.MIN;
        }
        try {
            return LocalDateTime.parse(timestampStr, DATE_FORMATTER);
        } catch (Exception e) {
            log.warn("Failed to parse datetime: {}", timestampStr);
            return LocalDateTime.MIN;
        }
    }

    private Long parseNewsId(String newsIdStr) {
        if (newsIdStr == null || newsIdStr.isEmpty()) {
            return 0L;
        }
        try {
            return Long.parseLong(newsIdStr);
        } catch (NumberFormatException e) {
            log.warn("Failed to parse news_id: {}", newsIdStr);
            return 0L;
        }
    }
}
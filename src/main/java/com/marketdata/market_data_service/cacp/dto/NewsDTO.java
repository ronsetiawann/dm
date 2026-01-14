package com.marketdata.market_data_service.cacp.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * DTO for news data from ticmi_cacp.news table
 * Response format matches IQPNews structure for consistency
 */
@Data
public class NewsDTO {
    private String newsId;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dateTime;
    private String category;
    private Integer source;
    private String country;
    private String companyId;
    private String headline;
    private String story;
    private Integer sectorId;
    private String link;
    private Integer status;
    private Long intNewsId;
}
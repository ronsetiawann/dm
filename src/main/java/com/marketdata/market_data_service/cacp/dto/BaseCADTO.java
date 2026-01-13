package com.marketdata.market_data_service.cacp.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class BaseCADTO {
    
    @JsonProperty("ID")
    private Long id;
    
    @JsonProperty("Type")
    private String type;
    
    @JsonProperty("StockGroup")
    private String stockGroup;
    
    @JsonProperty("DateKey")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dateKey;
}

package com.marketdata.market_data_service.cacp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CASplitDTO extends BaseCADTO {
    
    @JsonProperty("Code")
    private String code;
    
    @JsonProperty("PrevPrice")
    private String prevPrice;
    
    @JsonProperty("NewPrice")
    private String newPrice;
    
    @JsonProperty("Ratio")
    private String ratio;
    
    @JsonProperty("CumDate")
    private LocalDateTime cumDate;
    
    @JsonProperty("ExDate")
    private LocalDateTime exDate;
    
    @JsonProperty("DistDate")
    private LocalDateTime distDate;
    
    @JsonProperty("RecDate")
    private LocalDateTime recDate;
}

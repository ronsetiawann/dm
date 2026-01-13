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
public class CADividenDTO extends BaseCADTO {
    
    @JsonProperty("Code")
    private String code;
    
    @JsonProperty("Status")
    private String status;
    
    @JsonProperty("PaymentType")
    private String paymentType;
    
    @JsonProperty("DividenPerShare")
    private String dividenPerShare;
    
    @JsonProperty("TotalDividen")
    private String totalDividen;
    
    @JsonProperty("CumDate")
    private LocalDateTime cumDate;
    
    @JsonProperty("ExDate")
    private LocalDateTime exDate;
    
    @JsonProperty("RecDate")
    private LocalDateTime recDate;
    
    @JsonProperty("DistDate")
    private LocalDateTime distDate;
}

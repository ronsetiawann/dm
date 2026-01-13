package com.marketdata.market_data_service.cacp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CAWarrantDTO extends BaseCADTO {
    @JsonProperty("Code")
    private String code;
    @JsonProperty("Series")
    private String series;
    @JsonProperty("Total")
    private String total;
    @JsonProperty("ExcPrice")
    private String excPrice;
    @JsonProperty("ExcPeriodStr")
    private String excPeriodStr;
    @JsonProperty("TradingPeriodStr")
    private String tradingPeriodStr;
    @JsonProperty("ExcPeriodStart")
    private LocalDateTime excPeriodStart;
    @JsonProperty("ExcPeriodEnd")
    private LocalDateTime excPeriodEnd;
    @JsonProperty("TradingPeriodStart")
    private LocalDateTime tradingPeriodStart;
    @JsonProperty("TradingPeriodEnd")
    private LocalDateTime tradingPeriodEnd;
}

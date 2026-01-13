package com.marketdata.market_data_service.cacp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CARightDTO extends BaseCADTO {
    @JsonProperty("Code")
    private String code;
    @JsonProperty("Ratio")
    private String ratio;
    @JsonProperty("Price")
    private String price;
    @JsonProperty("Shares")
    private String shares;
    @JsonProperty("Indicator")
    private String indicator;
    @JsonProperty("TradingPeriodStr")
    private String tradingPeriodStr;
    @JsonProperty("CumDate")
    private LocalDateTime cumDate;
    @JsonProperty("ExDate")
    private LocalDateTime exDate;
    @JsonProperty("DpsDate")
    private LocalDateTime dpsDate;
    @JsonProperty("TradingPeriodStart")
    private LocalDateTime tradingPeriodStart;
    @JsonProperty("TradingPeriodEnd")
    private LocalDateTime tradingPeriodEnd;
}

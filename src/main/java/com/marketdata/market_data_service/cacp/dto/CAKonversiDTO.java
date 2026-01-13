package com.marketdata.market_data_service.cacp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CAKonversiDTO extends BaseCADTO {
    @JsonProperty("Code")
    private String code;
    @JsonProperty("Shares")
    private String shares;
    @JsonProperty("ShareFrom")
    private String shareFrom;
    @JsonProperty("TotalShare")
    private String totalShare;
    @JsonProperty("Remark")
    private String remark;
    @JsonProperty("RecDate")
    private LocalDateTime recDate;
    @JsonProperty("TradeDate")
    private LocalDateTime tradeDate;
}

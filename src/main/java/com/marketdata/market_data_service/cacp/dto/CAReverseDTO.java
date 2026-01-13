package com.marketdata.market_data_service.cacp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CAReverseDTO extends BaseCADTO {
    @JsonProperty("Code")
    private String code;
    @JsonProperty("Ratio")
    private String ratio;
    @JsonProperty("NewParValue")
    private String newParValue;
    @JsonProperty("Share")
    private String share;
    @JsonProperty("Remark")
    private String remark;
    @JsonProperty("TradeDate")
    private LocalDateTime tradeDate;
    @JsonProperty("RecDate")
    private LocalDateTime recDate;
}

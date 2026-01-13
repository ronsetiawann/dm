package com.marketdata.market_data_service.cacp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CAIpoDTO extends BaseCADTO {
    @JsonProperty("Company")
    private String company;
    @JsonProperty("Business")
    private String business;
    @JsonProperty("Underwriter")
    private String underwriter;
    @JsonProperty("OfferPeriodStr")
    private String offerPeriodStr;
    @JsonProperty("TotalShare")
    private String totalShare;
    @JsonProperty("NominalPrice")
    private String nominalPrice;
    @JsonProperty("Price")
    private String price;
    @JsonProperty("RecDate")
    private LocalDateTime recDate;
    @JsonProperty("OfferPeriodStart")
    private LocalDateTime offerPeriodStart;
    @JsonProperty("OfferPeriodEnd")
    private LocalDateTime offerPeriodEnd;
}

package com.marketdata.market_data_service.cacp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CAPublicExposeDTO extends BaseCADTO {
    @JsonProperty("Code")
    private String code;
    @JsonProperty("Day")
    private String day;
    @JsonProperty("Venue")
    private String venue;
    @JsonProperty("DateStr")
    private String dateStr;
    @JsonProperty("TimeStr")
    private String timeStr;
    @JsonProperty("Datetime")
    private LocalDateTime datetime;
}

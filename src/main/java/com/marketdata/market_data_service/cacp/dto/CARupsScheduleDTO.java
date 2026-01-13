package com.marketdata.market_data_service.cacp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CARupsScheduleDTO extends BaseCADTO {
    @JsonProperty("Code")
    private String code;
    @JsonProperty("TimeStr")
    private String timeStr;
    @JsonProperty("DateStr")
    private String dateStr;
    @JsonProperty("Venue")
    private String venue;
    @JsonProperty("Remark")
    private String remark;
    @JsonProperty("Result")
    private String result;
    @JsonProperty("Date")
    private LocalDateTime date;
    @JsonProperty("RecDate")
    private LocalDateTime recDate;
}

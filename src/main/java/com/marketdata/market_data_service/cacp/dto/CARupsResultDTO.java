package com.marketdata.market_data_service.cacp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CARupsResultDTO extends BaseCADTO {
    @JsonProperty("Code")
    private String code;
    @JsonProperty("Result")
    private String result;
    @JsonProperty("DateStr")
    private String dateStr;
    @JsonProperty("Date")
    private LocalDateTime date;
}

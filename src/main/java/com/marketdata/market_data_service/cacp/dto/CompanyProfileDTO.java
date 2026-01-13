package com.marketdata.market_data_service.cacp.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.Builder;
import lombok.Data;

/**
 * DTO for Company Profile response
 * Matches existing .NET API format exactly
 */
@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CompanyProfileDTO {

    @JsonProperty("StockID")
    private String stockID;

    @JsonProperty("Address")
    private JsonNode address;

    @JsonProperty("background")
    private String background;

    @JsonProperty("business")
    private String business;

    @JsonProperty("subsidiary")
    private JsonNode subsidiary;

    @JsonProperty("commissioners")
    private JsonNode commissioners;

    @JsonProperty("directors")
    private JsonNode directors;

    @JsonProperty("underwriter")
    private JsonNode underwriter;

    @JsonProperty("shareRegistrar")
    private JsonNode shareRegistrar;

    @JsonProperty("shareHolder")
    private JsonNode shareHolder;

    @JsonProperty("shareHolderInfo")
    private JsonNode shareHolderInfo;

    @JsonProperty("shareHolderHistory")
    private JsonNode shareHolderHistory;

    @JsonProperty("historyStocks")
    private JsonNode historyStocks;

    @JsonProperty("otherInfos")
    private String otherInfos;
}
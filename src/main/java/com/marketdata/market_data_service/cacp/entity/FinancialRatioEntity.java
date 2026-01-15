package com.marketdata.market_data_service.cacp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Entity for financial_ratio table
 * Stores financial ratios calculated from financial statements
 */
@Entity
@Table(name = "financial_ratio", schema = "ticmi_cacp")
@IdClass(FinancialRatioEntity.FinancialRatioId.class)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FinancialRatioEntity {

    @Id
    @Column(name = "stock_id", length = 10)
    private String stockId;

    @Id
    @Column(name = "year")
    private Integer year;

    @Id
    @Column(name = "quartal")
    private Integer quartal;

    @Id
    @Column(name = "ratio_name", length = 255)
    private String ratioName;

    @Id
    @Column(name = "ratio_type", length = 20)
    private String ratioType;

    @Column(name = "value", precision = 30, scale = 8)
    private BigDecimal value;

    @Column(name = "ratio_version", length = 20)
    private String ratioVersion;

    @Column(name = "source", length = 20)
    private String source;

    @Column(name = "timci_updated_at")
    private LocalDateTime timciUpdatedAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class FinancialRatioId implements Serializable {
        private String stockId;
        private Integer year;
        private Integer quartal;
        private String ratioName;
        private String ratioType;
    }
}

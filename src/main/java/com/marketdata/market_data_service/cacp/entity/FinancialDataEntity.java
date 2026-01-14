package com.marketdata.market_data_service.cacp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "financial_report", schema = "ticmi_cacp")
@IdClass(FinancialDataEntity.FinancialDataId.class)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FinancialDataEntity {

    @Id
    @Column(name = "year")
    private Integer year;

    @Id
    @Column(name = "quartal")
    private Integer quartal;

    @Id
    @Column(name = "stock_id", length = 10)
    private String stockId;

    @Id
    @Column(name = "field_name", length = 1000)
    private String fieldName;

    @Column(name = "value", precision = 30, scale = 8)
    private BigDecimal value;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    // Composite Key Class
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class FinancialDataId implements Serializable {
        private Integer year;
        private Integer quartal;
        private String stockId;
        private String fieldName;
    }
}
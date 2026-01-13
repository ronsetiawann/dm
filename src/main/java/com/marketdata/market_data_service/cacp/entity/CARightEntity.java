package com.marketdata.market_data_service.cacp.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "ca_right")
public class CARightEntity extends BaseCAEntity {
    
    @Column(name = "right_code")
    private String code;
    
    @Column(name = "right_ratio")
    private String ratio;
    
    @Column(name = "right_price")
    private String price;
    
    @Column(name = "right_shares")
    private String shares;
    
    @Column(name = "right_indicator")
    private String indicator;
    
    @Column(name = "right_trading_period")
    private String tradingPeriodStr;
    
    @Column(name = "right_cum_dt")
    private LocalDateTime cumDate;
    
    @Column(name = "right_ex_dt")
    private LocalDateTime exDate;
    
    @Column(name = "right_dps_dt")
    private LocalDateTime dpsDate;
    
    @Override
    protected void updateDerivedFields() {
        setStockGroup(code);
        setDateKey(cumDate);
    }
    
    @Override
    public String getCAType() {
        return "Right";
    }
}

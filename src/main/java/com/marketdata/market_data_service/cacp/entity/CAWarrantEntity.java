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
@Table(name = "ca_wrant")
public class CAWarrantEntity extends BaseCAEntity {
    
    @Column(name = "wrant_code")
    private String code;
    
    @Column(name = "wrant_serie")
    private String series;
    
    @Column(name = "wrant_total")
    private String total;
    
    @Column(name = "wrant_exc_price")
    private String excPrice;
    
    @Column(name = "wrant_exc_period")
    private String excPeriodStr;
    
    @Column(name = "wrant_trading_period")
    private String tradingPeriodStr;
    
    @Override
    protected void updateDerivedFields() {
        setStockGroup(code);
        // DateKey will need to be calculated from period strings
        // For now, set to current time or MIN
        if (getDateKey() == null) {
            setDateKey(LocalDateTime.MIN);
        }
    }
    
    @Override
    public String getCAType() {
        return "Warrant";
    }
}

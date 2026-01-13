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
@Table(name = "ca_kvers")
public class CAKonversiEntity extends BaseCAEntity {
    
    @Column(name = "kvers_code")
    private String code;
    
    @Column(name = "kvers_share")
    private String shares;
    
    @Column(name = "kvers_share_from")
    private String shareFrom;
    
    @Column(name = "kvers_total_share")
    private String totalShare;
    
    @Column(name = "kvers_remark")
    private String remark;
    
    @Column(name = "kvers_record_dt")
    private LocalDateTime recDate;
    
    @Column(name = "kvers_trade_dt")
    private LocalDateTime tradeDate;
    
    @Override
    protected void updateDerivedFields() {
        setStockGroup(code);
        setDateKey(recDate);
    }
    
    @Override
    public String getCAType() {
        return "Convertion";
    }
}

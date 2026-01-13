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
@Table(name = "ca_rvers")
public class CAReverseEntity extends BaseCAEntity {
    
    @Column(name = "rvers_code")
    private String code;
    
    @Column(name = "rvers_old_ratio_share")
    private String oldRatioShare;
    
    @Column(name = "rvers_new_ratio_share")
    private String newRatioShare;
    
    @Column(name = "rvers_new_par_value")
    private String newParValue;
    
    @Column(name = "rvers_share")
    private String share;
    
    @Column(name = "rvers_remark")
    private String remark;
    
    @Column(name = "rvers_trade_dt")
    private LocalDateTime tradeDate;
    
    @Column(name = "rvers_record_dt")
    private LocalDateTime recDate;
    
    @Transient
    public String getRatio() {
        return oldRatioShare + ":" + newRatioShare;
    }
    
    @Override
    protected void updateDerivedFields() {
        setStockGroup(code);
        setDateKey(recDate);
    }
    
    @Override
    public String getCAType() {
        return "Reverse";
    }
}

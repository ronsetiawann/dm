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
@Table(name = "ca_bonus", schema = "s21_cacp")
public class CABonusEntity extends BaseCAEntity {
    
    @Column(name = "code")
    private String code;
    
    @Column(name = "ratio")
    private String ratio;
    
    @Column(name = "cum_bonus_dt")
    private LocalDateTime cumDate;
    
    @Column(name = "ex_bonus_dt")
    private LocalDateTime exDate;
    
    @Column(name = "recording_dt")
    private LocalDateTime recDate;
    
    @Column(name = "distribution_dt")
    private LocalDateTime distDate;
    
    @Override
    protected void updateDerivedFields() {
        if (code != null) {
            String[] parts = code.split("-");
            setStockGroup(parts[0]);
        }
        setDateKey(recDate);
    }
    
    @Override
    public String getCAType() {
        return "Bonus";
    }
}

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
@Table(name = "ca_rupsj")
public class CARupsScheduleEntity extends BaseCAEntity {
    
    @Column(name = "rupsj_code")
    private String code;
    
    @Column(name = "rupsj_time")
    private String timeStr;
    
    @Column(name = "rupsj_date")
    private String dateStr;
    
    @Column(name = "rupsj_place")
    private String venue;
    
    @Column(name = "rupsj_remark")
    private String remark;
    
    @Column(name = "rupsj_result")
    private String result;
    
    @Column(name = "rupsj_rec_dt")
    private LocalDateTime recDate;
    
    @Transient
    private LocalDateTime date;
    
    @Override
    protected void updateDerivedFields() {
        setStockGroup(code);
        this.date = recDate;
        setDateKey(date);
    }
    
    @Override
    public String getCAType() {
        return "RUPSSchedule";
    }
}

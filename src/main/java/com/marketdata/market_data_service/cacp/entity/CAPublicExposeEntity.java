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
@Table(name = "ca_puexp")
public class CAPublicExposeEntity extends BaseCAEntity {
    
    @Column(name = "puexp_code")
    private String code;
    
    @Column(name = "puexp_day")
    private String day;
    
    @Column(name = "puexp_venue")
    private String venue;
    
    @Column(name = "puexp_date")
    private String dateStr;
    
    @Column(name = "puexp_time")
    private String timeStr;
    
    @Column(name = "puexp_datetime")
    private LocalDateTime datetime;
    
    @Override
    protected void updateDerivedFields() {
        setStockGroup(code);
        setDateKey(datetime);
    }
    
    @Override
    public String getCAType() {
        return "PublicExpose";
    }
}

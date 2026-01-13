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
@Table(name = "ca_dvden")
public class CADividenEntity extends BaseCAEntity {
    
    @Column(name = "dvden_code")
    private String code;
    
    @Column(name = "dvden_status")
    private String status;
    
    @Column(name = "dvden_payment_type")
    private String paymentType;
    
    @Column(name = "dvden_per_share")
    private String dividenPerShare;
    
    @Column(name = "total_dvden")
    private String totalDividen;
    
    @Column(name = "cum_dvden_dt")
    private LocalDateTime cumDate;
    
    @Column(name = "ex_dvden_dt")
    private LocalDateTime exDate;
    
    @Column(name = "dvden_record_dt")
    private LocalDateTime recDate;
    
    @Column(name = "dvden_dist_dt")
    private LocalDateTime distDate;
    
    @Override
    protected void updateDerivedFields() {
        setStockGroup(code);
        setDateKey(recDate);
    }
    
    @Override
    public String getCAType() {
        return "Dividen";
    }
}

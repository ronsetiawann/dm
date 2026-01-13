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
@Table(name = "ca_jdipo")
public class CAIpoEntity extends BaseCAEntity {
    
    @Column(name = "jdipo_comp")
    private String company;
    
    @Column(name = "jdipo_business")
    private String business;
    
    @Column(name = "jdipo_underwriter")
    private String underwriter;
    
    @Column(name = "jdipo_offer_dt")
    private String offerPeriodStr;
    
    @Column(name = "jdipo_total_share")
    private String totalShare;
    
    @Column(name = "jdipo_nom_share")
    private String nominalPrice;
    
    @Column(name = "jdipo_val_share")
    private String price;
    
    @Column(name = "jdipo_record_dt")
    private LocalDateTime recDate;
    
    @Override
    protected void updateDerivedFields() {
        // IPO might not have a stock code, use company name or leave empty
        setStockGroup(company != null ? company : "");
        setDateKey(recDate);
    }
    
    @Override
    public String getCAType() {
        return "IPO";
    }
}

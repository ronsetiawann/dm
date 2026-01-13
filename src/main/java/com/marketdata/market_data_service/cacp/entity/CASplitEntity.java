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
@Table(name = "ca_split")
public class CASplitEntity extends BaseCAEntity {
    
    @Column(name = "split_code")
    private String code;
    
    @Column(name = "split_prev_price")
    private String prevPrice;
    
    @Column(name = "split_curr_price")
    private String newPrice;
    
    @Column(name = "split_ratio")
    private String ratio;
    
    @Column(name = "split_cum_dt")
    private LocalDateTime cumDate;
    
    @Column(name = "split_ex_dt")
    private LocalDateTime exDate;
    
    @Column(name = "split_dist_dt")
    private LocalDateTime distDate;
    
    @Column(name = "split_rec_dt")
    private LocalDateTime recDate;
    
    @Override
    protected void updateDerivedFields() {
        setStockGroup(code);
        setDateKey(recDate);
    }
    
    @Override
    public String getCAType() {
        return "Split";
    }
}

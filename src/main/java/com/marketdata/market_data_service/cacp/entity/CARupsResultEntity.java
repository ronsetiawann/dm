package com.marketdata.market_data_service.cacp.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "ca_rupsh")
public class CARupsResultEntity extends BaseCAEntity {

    @Column(name = "rupsh_code")
    private String code;

    @Column(name = "rupsh_result")
    private String result;

    @Column(name = "rupsh_date")
    private String dateStr;

    @Transient
    private LocalDateTime date;

    @Override
    protected void updateDerivedFields() {
        setStockGroup(code);
        //parse from "Kamis , 27 Des 2018"
        if (dateStr != null && !dateStr.trim().isEmpty()) {
            try {
                DateTimeFormatter formatter = DateTimeFormatter
                        .ofPattern("EEEE , dd MMM yyyy", new Locale("id", "ID"));
                LocalDate localDate = LocalDate.parse(dateStr.trim(), formatter);
                this.date = localDate.atStartOfDay();

            } catch (Exception e) {
                this.date = null;
            }
        }
        setDateKey(this.date != null ? this.date : LocalDateTime.MIN);
    }

    @Override
    public String getCAType() {
        return "RUPSResult";
    }
}
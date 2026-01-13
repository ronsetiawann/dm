package com.marketdata.market_data_service.cacp.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

/**
 * Entity for Company Profile data
 * Maps to cp_data table
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cp_data")
public class CompanyProfileEntity {

    @Id
    @Column(name = "code", length = 21)
    private String code;

    @Column(name = "title", length = 150)
    private String title;

    @Column(name = "address", columnDefinition = "LONGTEXT")
    private String address;

    @Column(name = "background", columnDefinition = "LONGTEXT")
    private String background;

    @Column(name = "business_cp_product", columnDefinition = "LONGTEXT")
    private String businessCpProduct;

    @Column(name = "subsidiary_affiliation", columnDefinition = "LONGTEXT")
    private String subsidiaryAffiliation;

    @Column(name = "commissioners", columnDefinition = "LONGTEXT")
    private String commissioners;

    @Column(name = "directors", columnDefinition = "LONGTEXT")
    private String directors;

    @Column(name = "underwriter", columnDefinition = "LONGTEXT")
    private String underwriter;

    @Column(name = "share_registrar", columnDefinition = "LONGTEXT")
    private String shareRegistrar;

    @Column(name = "share_holders", columnDefinition = "LONGTEXT")
    private String shareHolders;

    @Column(name = "history_stocks", columnDefinition = "LONGTEXT")
    private String historyStocks;

    @Column(name = "other_infos", columnDefinition = "LONGTEXT")
    private String otherInfos;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @Column(name = "revised_date")
    private LocalDateTime revisedDate;
}
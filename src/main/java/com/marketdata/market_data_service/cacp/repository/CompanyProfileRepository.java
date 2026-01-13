package com.marketdata.market_data_service.cacp.repository;

import com.marketdata.market_data_service.cacp.entity.CompanyProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository for Company Profile data
 */
@Repository
public interface CompanyProfileRepository extends JpaRepository<CompanyProfileEntity, String> {
    List<CompanyProfileEntity> findByCodeIn(List<String> codes);
}
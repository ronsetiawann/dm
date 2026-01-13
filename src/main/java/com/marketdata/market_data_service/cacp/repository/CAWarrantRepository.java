package com.marketdata.market_data_service.cacp.repository;

import com.marketdata.market_data_service.cacp.entity.CAWarrantEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface CAWarrantRepository extends BaseCARepository<CAWarrantEntity> {
    // Inherits all common query methods from BaseCARepository
    // Add entity-specific query methods here if needed
}

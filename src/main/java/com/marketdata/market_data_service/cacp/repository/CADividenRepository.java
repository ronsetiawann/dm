package com.marketdata.market_data_service.cacp.repository;

import com.marketdata.market_data_service.cacp.entity.CADividenEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface CADividenRepository extends BaseCARepository<CADividenEntity> {
    // Inherits all common query methods from BaseCARepository
    // Add entity-specific query methods here if needed
}

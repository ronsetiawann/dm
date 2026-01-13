package com.marketdata.market_data_service.cacp.repository;

import com.marketdata.market_data_service.cacp.entity.CARupsResultEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface CARupsResultRepository extends BaseCARepository<CARupsResultEntity> {
    // Inherits all common query methods from BaseCARepository
    // Add entity-specific query methods here if needed
}

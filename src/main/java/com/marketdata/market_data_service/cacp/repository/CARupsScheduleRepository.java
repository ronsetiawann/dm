package com.marketdata.market_data_service.cacp.repository;

import com.marketdata.market_data_service.cacp.entity.CARupsScheduleEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface CARupsScheduleRepository extends BaseCARepository<CARupsScheduleEntity> {
    // Inherits all common query methods from BaseCARepository
    // Add entity-specific query methods here if needed
}

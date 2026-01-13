package com.marketdata.market_data_service.cacp.repository;

import com.marketdata.market_data_service.cacp.entity.BaseCAEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Base repository interface for all Corporate Action entities
 * Provides common query methods
 */
@NoRepositoryBean
public interface BaseCARepository<T extends BaseCAEntity> 
        extends JpaRepository<T, Long>, JpaSpecificationExecutor<T> {
    
    //List<T> findByStockGroupOrderByDateKeyDesc(String stockGroup);
    
//    List<T> findByDateKeyBetweenOrderByDateKeyDesc(
//        LocalDateTime startDate,
//        LocalDateTime endDate
//    );
//
//    List<T> findByStockGroupAndDateKeyBetweenOrderByDateKeyDesc(
//        String stockGroup,
//        LocalDateTime startDate,
//        LocalDateTime endDate
//    );
    
    //List<T> findAllByOrderByDateKeyDesc();
}

package com.marketdata.market_data_service.cacp.repository;

import com.marketdata.market_data_service.cacp.entity.FinancialDataEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FinancialDataRepository extends JpaRepository<FinancialDataEntity, FinancialDataEntity.FinancialDataId> {

    /**
     * Find all financial data for specific stock, year, and quarter
     */
    List<FinancialDataEntity> findByStockIdAndYearAndQuartal(String stockId, Integer year, Integer quartal);

    /**
     * Find all financial data for specific stock and year (all quarters)
     */
    List<FinancialDataEntity> findByStockIdAndYear(String stockId, Integer year);

    /**
     * Find all financial data for specific stock (all years and quarters)
     */
    List<FinancialDataEntity> findByStockId(String stockId);

    /**
     * Find all financial data (for fdAll endpoint)
     */
    @Query("SELECT fd FROM FinancialDataEntity fd ORDER BY fd.year DESC, fd.quartal DESC")
    List<FinancialDataEntity> findAllOrderByYearAndQuartalDesc();

    /**
     * Find distinct stock IDs
     */
    @Query("SELECT DISTINCT fd.stockId FROM FinancialDataEntity fd ORDER BY fd.stockId")
    List<String> findDistinctStockIds();

    /**
     * Find data for specific stock within year range (for year=0 logic: last 10 years)
     */
    @Query("SELECT fd FROM FinancialDataEntity fd WHERE fd.stockId = :stockId " +
            "AND fd.year BETWEEN :startYear AND :endYear AND fd.quartal = :quartal " +
            "ORDER BY fd.year DESC, fd.quartal DESC")
    List<FinancialDataEntity> findByStockIdAndYearRangeAndQuartal(
            @Param("stockId") String stockId,
            @Param("startYear") Integer startYear,
            @Param("endYear") Integer endYear,
            @Param("quartal") Integer quartal
    );

    /**
     * Get latest year and quarter for a stock
     */
    @Query("SELECT MAX(fd.year * 4 + fd.quartal) FROM FinancialDataEntity fd WHERE fd.stockId = :stockId")
    Integer findLatestYearQuartalIndex(@Param("stockId") String stockId);
}

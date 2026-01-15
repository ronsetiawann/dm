package com.marketdata.market_data_service.cacp.repository;

import com.marketdata.market_data_service.cacp.entity.FinancialRatioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository for accessing financial_ratio table
 * Handles queries for financial ratios (quarterly and TTM)
 */
@Repository
public interface FinancialRatioRepository extends JpaRepository<FinancialRatioEntity, FinancialRatioEntity.FinancialRatioId> {
    List<FinancialRatioEntity> findByStockIdAndYearAndQuartalAndRatioType(
            String stockId,
            Integer year,
            Integer quartal,
            String ratioType
    );

    List<FinancialRatioEntity> findByStockIdAndYearAndQuartal(
            String stockId,
            Integer year,
            Integer quartal
    );

    @Query("SELECT fr FROM FinancialRatioEntity fr WHERE fr.ratioType = :ratioType ORDER BY fr.year DESC, fr.quartal DESC")
    List<FinancialRatioEntity> findAllByRatioTypeOrderByYearAndQuartalDesc(@Param("ratioType") String ratioType);

    @Query("SELECT fr FROM FinancialRatioEntity fr WHERE fr.stockId = :stockId " +
            "AND fr.year BETWEEN :startYear AND :endYear AND fr.quartal = :quartal " +
            "AND fr.ratioType = :ratioType " +
            "ORDER BY fr.year DESC, fr.quartal DESC")
    List<FinancialRatioEntity> findByStockIdAndYearRangeAndQuartalAndRatioType(
            @Param("stockId") String stockId,
            @Param("startYear") Integer startYear,
            @Param("endYear") Integer endYear,
            @Param("quartal") Integer quartal,
            @Param("ratioType") String ratioType
    );
    @Query("SELECT fr FROM FinancialRatioEntity fr WHERE fr.stockId = :stockId " +
            "AND fr.year = :year AND fr.ratioType = :ratioType " +
            "ORDER BY fr.quartal DESC")
    List<FinancialRatioEntity> findByStockIdAndYearAndRatioType(
            @Param("stockId") String stockId,
            @Param("year") Integer year,
            @Param("ratioType") String ratioType
    );
}
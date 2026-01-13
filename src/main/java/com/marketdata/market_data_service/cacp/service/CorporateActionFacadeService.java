package com.marketdata.market_data_service.cacp.service;

import com.marketdata.market_data_service.cacp.repository.*;
import com.marketdata.market_data_service.common.mapper.CAMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;

/**
 * Facade service that provides all corporate action endpoints
 * Uses GenericCACorporateActionService to eliminate code duplication
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class CorporateActionFacadeService {

    private final GenericCACorporateActionService genericService;
    private final CAMapper mapper;

    // Inject all repositories
    private final CABonusRepository bonusRepository;
    private final CADividenRepository dividenRepository;
    private final CASplitRepository splitRepository;
    private final CAIpoRepository ipoRepository;
    private final CAKonversiRepository konversiRepository;
    private final CAPublicExposeRepository publicExposeRepository;
    private final CAReverseRepository reverseRepository;
    private final CARightRepository rightRepository;
    private final CARupsResultRepository rupsResultRepository;
    private final CARupsScheduleRepository rupsScheduleRepository;
    private final CAWarrantRepository warrantRepository;

    @CacheEvict(value = "corporateActionCache", allEntries = true)
    public void removeCachedData() {
        log.info("Clearing all corporate action cache");
    }

    @Async
    public CompletableFuture<ResponseEntity<String>> getBonus(
            String code, String stockID, boolean isJSON, int p, int n,
            LocalDateTime startDate, LocalDateTime endDate) {

        return genericService.getCorporateAction(
                "Bonus",
                bonusRepository,
                mapper::toDTO,
                new GenericCACorporateActionService.CAFilterParams(code, stockID, p, n, startDate, endDate),
                isJSON
        );
    }

    @Async
    public CompletableFuture<ResponseEntity<String>> getDividen(
            String code, String stockID, boolean isJSON, int p, int n,
            LocalDateTime startDate, LocalDateTime endDate) {

        return genericService.getCorporateAction(
                "Dividen",
                dividenRepository,
                mapper::toDTO,
                new GenericCACorporateActionService.CAFilterParams(code, stockID, p, n, startDate, endDate),
                isJSON
        );
    }

    @Async
    public CompletableFuture<ResponseEntity<String>> getSplit(
            String code, String stockID, boolean isJSON, int p, int n,
            LocalDateTime startDate, LocalDateTime endDate) {

        return genericService.getCorporateAction(
                "Split",
                splitRepository,
                mapper::toDTO,
                new GenericCACorporateActionService.CAFilterParams(code, stockID, p, n, startDate, endDate),
                isJSON
        );
    }

    @Async
    public CompletableFuture<ResponseEntity<String>> getIPO(
            String code, String stockID, boolean isJSON, int p, int n,
            LocalDateTime startDate, LocalDateTime endDate) {

        return genericService.getCorporateAction(
                "IPO",
                ipoRepository,
                mapper::toDTO,
                new GenericCACorporateActionService.CAFilterParams(code, stockID, p, n, startDate, endDate),
                isJSON
        );
    }

    @Async
    public CompletableFuture<ResponseEntity<String>> getKonversi(
            String code, String stockID, boolean isJSON, int p, int n,
            LocalDateTime startDate, LocalDateTime endDate) {

        return genericService.getCorporateAction(
                "Konversi",
                konversiRepository,
                mapper::toDTO,
                new GenericCACorporateActionService.CAFilterParams(code, stockID, p, n, startDate, endDate),
                isJSON
        );
    }

    @Async
    public CompletableFuture<ResponseEntity<String>> getPublicExpose(
            String code, String stockID, boolean isJSON, int p, int n,
            LocalDateTime startDate, LocalDateTime endDate) {

        return genericService.getCorporateAction(
                "PublicExpose",
                publicExposeRepository,
                mapper::toDTO,
                new GenericCACorporateActionService.CAFilterParams(code, stockID, p, n, startDate, endDate),
                isJSON
        );
    }

    @Async
    public CompletableFuture<ResponseEntity<String>> getReverse(
            String code, String stockID, boolean isJSON, int p, int n,
            LocalDateTime startDate, LocalDateTime endDate) {

        return genericService.getCorporateAction(
                "Reverse",
                reverseRepository,
                mapper::toDTO,
                new GenericCACorporateActionService.CAFilterParams(code, stockID, p, n, startDate, endDate),
                isJSON
        );
    }

    @Async
    public CompletableFuture<ResponseEntity<String>> getRight(
            String code, String stockID, boolean isJSON, int p, int n,
            LocalDateTime startDate, LocalDateTime endDate) {

        return genericService.getCorporateAction(
                "Right",
                rightRepository,
                mapper::toDTO,
                new GenericCACorporateActionService.CAFilterParams(code, stockID, p, n, startDate, endDate),
                isJSON
        );
    }

    @Async
    public CompletableFuture<ResponseEntity<String>> getRupsResult(
            String code, String stockID, boolean isJSON, int p, int n,
            LocalDateTime startDate, LocalDateTime endDate) {

        return genericService.getCorporateAction(
                "RupsResult",
                rupsResultRepository,
                mapper::toDTO,
                new GenericCACorporateActionService.CAFilterParams(code, stockID, p, n, startDate, endDate),
                isJSON
        );
    }

    @Async
    public CompletableFuture<ResponseEntity<String>> getRupsSchedule(
            String code, String stockID, boolean isJSON, int p, int n,
            LocalDateTime startDate, LocalDateTime endDate) {

        return genericService.getCorporateAction(
                "RupsSchedule",
                rupsScheduleRepository,
                mapper::toDTO,
                new GenericCACorporateActionService.CAFilterParams(code, stockID, p, n, startDate, endDate),
                isJSON
        );
    }

    @Async
    @Cacheable(value = "corporateActionCache",
            key = "'warrant_' + #code + '_' + #stockID + '_' + #p + '_' + #n + '_' + #startDate + '_' + #endDate")
    public CompletableFuture<ResponseEntity<String>> getWarrant(
            String code, String stockID, boolean isJSON, int p, int n,
            LocalDateTime startDate, LocalDateTime endDate) {

        return genericService.getCorporateAction(
                "Warrant",
                warrantRepository,
                mapper::toDTO,
                new GenericCACorporateActionService.CAFilterParams(code, stockID, p, n, startDate, endDate),
                isJSON
        );
    }
}

package com.marketdata.market_data_service.common.mapper;

import com.marketdata.market_data_service.cacp.dto.*;
import com.marketdata.market_data_service.cacp.entity.*;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

/**
 * Centralized mapper for all Corporate Action entities to DTOs
 * Eliminates code duplication and ensures consistent mapping
 */
@Component
public class CAMapper {

    public CABonusDTO toDTO(CABonusEntity entity) {
        CABonusDTO dto = CABonusDTO.builder()
                .code(entity.getCode())
                .ratio(entity.getRatio())
                .cumDate(entity.getCumDate())
                .exDate(entity.getExDate())
                .recDate(entity.getRecDate())
                .distDate(entity.getDistDate())
                .build();
        mapBaseFields(entity, dto);
        dto.setType("Bonus");
        return dto;
    }

    public CADividenDTO toDTO(CADividenEntity entity) {
        CADividenDTO dto = CADividenDTO.builder()
                .code(entity.getCode())
                .status(entity.getStatus())
                .paymentType(entity.getPaymentType())
                .dividenPerShare(entity.getDividenPerShare())
                .totalDividen(entity.getTotalDividen())
                .cumDate(entity.getCumDate())
                .exDate(entity.getExDate())
                .recDate(entity.getRecDate())
                .distDate(entity.getDistDate())
                .build();
        mapBaseFields(entity, dto);
        dto.setType("Dividen");
        return dto;
    }

    public CASplitDTO toDTO(CASplitEntity entity) {
        CASplitDTO dto = CASplitDTO.builder()
                .code(entity.getCode())
                .prevPrice(entity.getPrevPrice())
                .newPrice(entity.getNewPrice())
                .ratio(entity.getRatio())
                .cumDate(entity.getCumDate())
                .exDate(entity.getExDate())
                .distDate(entity.getDistDate())
                .recDate(entity.getRecDate())
                .build();
        mapBaseFields(entity, dto);
        dto.setType("Split");
        return dto;
    }

    public CAIpoDTO toDTO(CAIpoEntity entity) {
        List<LocalDate> dates = parsePeriodDates(entity.getOfferPeriodStr());

        CAIpoDTO dto = CAIpoDTO.builder()
                .company(entity.getCompany())
                .business(entity.getBusiness())
                .underwriter(entity.getUnderwriter())
                .offerPeriodStr(entity.getOfferPeriodStr())
                .totalShare(entity.getTotalShare())
                .nominalPrice(entity.getNominalPrice())
                .price(entity.getPrice())
                .recDate(entity.getRecDate())
                .offerPeriodStart(dates.get(0).atStartOfDay())
                .offerPeriodEnd(dates.get(1).atTime(LocalTime.MAX))
                .build();
        mapBaseFields(entity, dto);
        dto.setType("IPO");
        return dto;
    }

    public CAKonversiDTO toDTO(CAKonversiEntity entity) {
        CAKonversiDTO dto = CAKonversiDTO.builder()
                .code(entity.getCode())
                .shares(entity.getShares())
                .shareFrom(entity.getShareFrom())
                .totalShare(entity.getTotalShare())
                .remark(entity.getRemark())
                .recDate(entity.getRecDate())
                .tradeDate(entity.getTradeDate())
                .build();
        mapBaseFields(entity, dto);
        dto.setType("Convertion");
        return dto;
    }

    public CAPublicExposeDTO toDTO(CAPublicExposeEntity entity) {
        CAPublicExposeDTO dto = CAPublicExposeDTO.builder()
                .code(entity.getCode())
                .day(entity.getDay())
                .venue(entity.getVenue())
                .dateStr(entity.getDateStr())
                .timeStr(entity.getTimeStr())
                .datetime(entity.getDatetime())
                .build();
        mapBaseFields(entity, dto);
        dto.setType("PublicExpose");
        return dto;
    }

    public CAReverseDTO toDTO(CAReverseEntity entity) {
        CAReverseDTO dto = CAReverseDTO.builder()
                .code(entity.getCode())
                .ratio(entity.getRatio())
                .newParValue(entity.getNewParValue())
                .share(entity.getShare())
                .remark(entity.getRemark())
                .tradeDate(entity.getTradeDate())
                .recDate(entity.getRecDate())
                .build();
        mapBaseFields(entity, dto);
        dto.setType("Reverse");
        return dto;
    }

    public CARightDTO toDTO(CARightEntity entity) {
        CARightDTO dto = CARightDTO.builder()
                .code(entity.getCode())
                .ratio(entity.getRatio())
                .price(entity.getPrice())
                .shares(entity.getShares())
                .indicator(entity.getIndicator())
                .tradingPeriodStr(entity.getTradingPeriodStr())
                .cumDate(entity.getCumDate())
                .exDate(entity.getExDate())
                .dpsDate(entity.getDpsDate())
                .build();
        mapBaseFields(entity, dto);
        dto.setType("Right");

        // Parse trading period - simplified version
        List<LocalDate> dates = parsePeriodDates(entity.getTradingPeriodStr());
        dto.setTradingPeriodStart(dates.get(0).atStartOfDay());
        dto.setTradingPeriodEnd(dates.get(1).atStartOfDay());

        return dto;
    }

    public CARupsResultDTO toDTO(CARupsResultEntity entity) {
        CARupsResultDTO dto = CARupsResultDTO.builder()
                .code(entity.getCode())
                .result(entity.getResult())
                .dateStr(entity.getDateStr())
                .date(entity.getDate())
                .build();
        mapBaseFields(entity, dto);
        dto.setType("RUPSResult");
        return dto;
    }

    public CARupsScheduleDTO toDTO(CARupsScheduleEntity entity) {
        CARupsScheduleDTO dto = CARupsScheduleDTO.builder()
                .code(entity.getCode())
                .timeStr(entity.getTimeStr())
                .dateStr(entity.getDateStr())
                .venue(entity.getVenue())
                .remark(entity.getRemark())
                .result(entity.getResult())
                .date(entity.getRecDate())
                .recDate(entity.getRecDate())
                .build();
        mapBaseFields(entity, dto);
        dto.setType("RUPSSchedule");
        return dto;
    }

    public CAWarrantDTO toDTO(CAWarrantEntity entity) {
        CAWarrantDTO dto = CAWarrantDTO.builder()
                .code(entity.getCode())
                .series(entity.getSeries())
                .total(entity.getTotal())
                .excPrice(entity.getExcPrice())
                .excPeriodStr(entity.getExcPeriodStr())
                .tradingPeriodStr(entity.getTradingPeriodStr())
                .build();
        mapBaseFields(entity, dto);
        dto.setType("Warrant");

        // Parse periods - simplified version
        List<LocalDate> excDates = parsePeriodDates(entity.getExcPeriodStr());
        dto.setExcPeriodStart(excDates.get(0).atStartOfDay());
        dto.setExcPeriodEnd(excDates.get(1).atStartOfDay());

        List<LocalDate> tradeDates = parsePeriodDates(entity.getTradingPeriodStr());
        dto.setTradingPeriodStart(tradeDates.get(0).atStartOfDay());
        dto.setTradingPeriodEnd(tradeDates.get(1).atStartOfDay());

        return dto;
    }

    private void mapBaseFields(BaseCAEntity entity, BaseCADTO dto) {
        dto.setId(entity.getId());
        dto.setStockGroup(entity.getStockGroup());
        dto.setDateKey(entity.getDateKey());
    }

    /**
     * Simplified period date parser
     * Format: "YYYY-MM-DD - YYYY-MM-DD"
     */
    private List<LocalDate> parsePeriodDates(String periodStr) {
        if (periodStr == null || periodStr.isEmpty()) {
            return Arrays.asList(LocalDate.MIN, LocalDate.MIN);
        }

        String[] parts = periodStr.split("-");
        LocalDate start = LocalDate.MIN;
        LocalDate end = LocalDate.MIN;

        try {
            if (parts.length > 0) {
                start = LocalDate.parse(parts[0].trim());
            }
            if (parts.length > 1) {
                end = LocalDate.parse(parts[1].trim());
            } else {
                end = start;
            }
        } catch (Exception e) {
            // Return MIN dates if parsing fails
        }

        return Arrays.asList(start, end);
    }
}


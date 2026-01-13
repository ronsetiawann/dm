package com.marketdata.market_data_service.cacp.service;

import com.marketdata.market_data_service.cacp.dto.FundamentalDTO;
import com.marketdata.market_data_service.cacp.dto.FundamentalIndDTO;
import com.marketdata.market_data_service.cacp.entity.FinancialDataEntity;
import com.marketdata.market_data_service.cacp.repository.FinancialDataRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Year;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class FundamentalService {

    private final FinancialDataRepository repository;

    /**
     * Get fundamental data in English format
     */
    @Cacheable(value = "fundamentals-en", key = "#stockId + '-' + #year + '-' + #quarter")
    public FundamentalDTO getFundamentalEnglish(String stockId, int year, int quarter) {
        //log.debug("Getting fundamental data for {}-{}-{}", stockId, year, quarter);

        List<FinancialDataEntity> rawData = repository.findByStockIdAndYearAndQuartal(
                stockId.toUpperCase(), year, quarter
        );

        if (rawData.isEmpty()) {
            return null;
        }
        return mapToEnglishDTO(rawData, stockId.toUpperCase(), year, quarter);
    }

    /**
     * Get fundamental data in Indonesian format
     */
    @Cacheable(value = "fundamentals-id", key = "#stockId + '-' + #year + '-' + #quarter")
    public FundamentalIndDTO getFundamentalIndonesian(String stockId, int year, int quarter) {
        //log.debug("Getting fundamental data (ID) for {}-{}-{}", stockId, year, quarter);
        List<FinancialDataEntity> rawData = repository.findByStockIdAndYearAndQuartal(
                stockId.toUpperCase(), year, quarter
        );
        if (rawData.isEmpty()) {
            return null;
        }
        return mapToIndonesianDTO(rawData, stockId.toUpperCase(), year, quarter);
    }

    /**
     * Get fundamentals for last 10 years (when year = 0)
     */
    public List<FundamentalDTO> getFundamentalsLast10YearsEnglish(String stockId, int quarter) {
        int currentYear = Year.now().getValue();
        List<FundamentalDTO> results = new ArrayList<>();

        for (int y = currentYear - 10; y <= currentYear; y++) {
            FundamentalDTO dto = getFundamentalEnglish(stockId, y, quarter);
            if (dto != null) {
                results.add(dto);
            }
        }

        return results;
    }

    public List<FundamentalIndDTO> getFundamentalsLast10YearsIndonesian(String stockId, int quarter) {
        int currentYear = Year.now().getValue();
        List<FundamentalIndDTO> results = new ArrayList<>();

        for (int y = currentYear - 10; y <= currentYear; y++) {
            FundamentalIndDTO dto = getFundamentalIndonesian(stockId, y, quarter);
            if (dto != null) {
                results.add(dto);
            }
        }

        return results;
    }
    /**
     * Get all quarters for a year (when quarter = 0)
     */
    public List<FundamentalDTO> getFundamentalsAllQuartersEnglish(String stockId, int year) {
        List<FundamentalDTO> results = new ArrayList<>();

        for (int q = 1; q <= 4; q++) {
            FundamentalDTO dto = getFundamentalEnglish(stockId, year, q);
            if (dto != null) {
                results.add(dto);
            }
        }
        return results;
    }

    public List<FundamentalIndDTO> getFundamentalsAllQuartersIndonesian(String stockId, int year) {
        List<FundamentalIndDTO> results = new ArrayList<>();

        for (int q = 1; q <= 4; q++) {
            FundamentalIndDTO dto = getFundamentalIndonesian(stockId, year, q);
            if (dto != null) {
                results.add(dto);
            }
        }

        return results;
    }

    /**
     * Get all fundamental data (English)
     */
    @Cacheable(value = "fundamentals-all-en")
    public List<FundamentalDTO> getAllFundamentalsEnglish() {
        //log.debug("Getting all fundamental data (English)");

        List<FinancialDataEntity> allData = repository.findAllOrderByYearAndQuartalDesc();

        // Group by stock_id + year + quarter
        Map<String, List<FinancialDataEntity>> grouped = allData.stream()
                .collect(Collectors.groupingBy(
                        entity -> entity.getStockId() + "-" + entity.getYear() + "-" + entity.getQuartal()
                ));

        return grouped.entrySet().stream()
                .map(entry -> {
                    List<FinancialDataEntity> entities = entry.getValue();
                    if (!entities.isEmpty()) {
                        FinancialDataEntity first = entities.get(0);
                        return mapToEnglishDTO(entities, first.getStockId(), first.getYear(), first.getQuartal());
                    }
                    return null;
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    /**
     * Get all fundamental data (Indonesian)
     */
    @Cacheable(value = "fundamentals-all-id")
    public List<FundamentalIndDTO> getAllFundamentalsIndonesian() {
        //log.debug("Getting all fundamental data (Indonesian)");

        List<FinancialDataEntity> allData = repository.findAllOrderByYearAndQuartalDesc();

        // Group by stock_id + year + quarter
        Map<String, List<FinancialDataEntity>> grouped = allData.stream()
                .collect(Collectors.groupingBy(
                        entity -> entity.getStockId() + "-" + entity.getYear() + "-" + entity.getQuartal()
                ));

        return grouped.entrySet().stream()
                .map(entry -> {
                    List<FinancialDataEntity> entities = entry.getValue();
                    if (!entities.isEmpty()) {
                        FinancialDataEntity first = entities.get(0);
                        return mapToIndonesianDTO(entities, first.getStockId(), first.getYear(), first.getQuartal());
                    }
                    return null;
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    /**
     * Pivot EAV data to Map<field_name, value>
     */
    private Map<String, Double> pivotEAVToMap(List<FinancialDataEntity> entities) {
        return entities.stream()
                .collect(Collectors.toMap(
                        FinancialDataEntity::getFieldName,
                        entity -> entity.getValue() != null ? entity.getValue().doubleValue() : 0.0,
                        (v1, v2) -> v1 // In case of duplicates, keep first
                ));
    }

    /**
     * Map to English DTO
     */
    private FundamentalDTO mapToEnglishDTO(List<FinancialDataEntity> entities, String stockId, int year, int quarter) {
        Map<String, Double> fieldMap = pivotEAVToMap(entities);

        FundamentalDTO dto = new FundamentalDTO();
        dto.setStockId(stockId);
        dto.setYear(year);
        dto.setQuarter(quarter);
        dto.setDate(LocalDate.of(year, quarter * 3, 1)); // Approximate date

        // Map Indonesian field names to English properties
        dto.setGrossProfit(fieldMap.get("labaBruto"));
        dto.setNetIncome(fieldMap.get("labaRugi"));
        dto.setTotalSales(fieldMap.get("penjualanDanPendapatanUsaha"));
        dto.setOperatingProfit(fieldMap.get("labaOperasional"));
        dto.setCostGoodSold(Math.abs(fieldMap.getOrDefault("bebanPokokPenjualanDanPendapatan", 0.0)));
        dto.setEarningBeforeTax(fieldMap.get("labaRugiSebelumPajakPenghasilan"));
        dto.setTax(fieldMap.get("pendapatanBebanPajak"));
        dto.setOtherIncome(fieldMap.get("pendapatanLainnya"));

        // Expenses
        dto.setInterestExpense(Math.abs(fieldMap.getOrDefault("bebanBungaDanKeuangan", 0.0)));
        dto.setSalesExpense(Math.abs(fieldMap.getOrDefault("bebanPenjualan", 0.0)));
        dto.setGeneralAdminExpense(Math.abs(fieldMap.getOrDefault("bebanUmumDanAdministrasi", 0.0)));
        dto.setOtherExpense(Math.abs(fieldMap.getOrDefault("bebanLainnya", 0.0)));
        dto.setDepreciationAmortization(Math.abs(fieldMap.getOrDefault("bebanPenyusutanDanAmortisasi", 0.0)));
        dto.setEmployeeBenefits(Math.abs(fieldMap.getOrDefault("bebanManfaatKaryawan", 0.0)));

        // Income
        dto.setFinancialIncome(fieldMap.get("pendapatanKeuangan"));
        dto.setInterestIncome(fieldMap.get("pendapatanBunga"));
        dto.setDividendIncome(fieldMap.get("pendapatanDividen"));
        dto.setCommissionIncome(fieldMap.get("pendapatanKomisi"));
        dto.setInvestmentIncome(fieldMap.get("pendapatanInvestasi"));

        // Comprehensive Income
        dto.setComprehensiveIncome(fieldMap.get("labaRugiKomprehensif"));
        dto.setOtherComprehensiveIncome(fieldMap.get("pendapatanKomprehensifLainnyaSetelahPajak"));
        dto.setTaxOnOtherComprehensiveIncome(fieldMap.get("pajakAtasPendapatanKomprehensifLainnya"));

        // Forex & Derivatives
        dto.setForexGainLoss(fieldMap.get("keuntunganKerugianSelisihKursMataUangAsing"));
        dto.setDerivativeGainLoss(fieldMap.get("keuntunganKerugianAtasInstrumenKeuanganDerivatif"));

        // Equity Method
        dto.setShareOfAssociatesProfit(fieldMap.get("bagianAtasLabaRugiEntitasAsosiasiYangDicatatDenganMenggunakanMetodeEkuitas"));
        dto.setShareOfJointVenturesProfit(fieldMap.get("bagianAtasLabaRugiEntitasVenturaBersamaYangDicatatMenggunakanMetodeEkuitas"));

        return dto;
    }

    /**
     * Map to Indonesian DTO
     */
    private FundamentalIndDTO mapToIndonesianDTO(List<FinancialDataEntity> entities, String stockId, int year, int quarter) {
        Map<String, Double> fieldMap = pivotEAVToMap(entities);

        FundamentalIndDTO dto = new FundamentalIndDTO();
        dto.setStockId(stockId);
        dto.setTahun(year);
        dto.setKuartal(quarter);
        dto.setTanggal(LocalDate.of(year, quarter * 3, 1));

        // Direct mapping - field names sama dengan database
        dto.setLabaBruto(fieldMap.get("labaBruto"));
        dto.setLabaRugi(fieldMap.get("labaRugi"));
        dto.setPenjualanDanPendapatanUsaha(fieldMap.get("penjualanDanPendapatanUsaha"));
        dto.setLabaOperasional(fieldMap.get("labaOperasional"));
        dto.setBebanPokokPenjualanDanPendapatan(fieldMap.get("bebanPokokPenjualanDanPendapatan"));
        dto.setLabaRugiSebelumPajakPenghasilan(fieldMap.get("labaRugiSebelumPajakPenghasilan"));
        dto.setPendapatanBebanPajak(fieldMap.get("pendapatanBebanPajak"));
        dto.setPendapatanLainnya(fieldMap.get("pendapatanLainnya"));

        // Beban
        dto.setBebanBungaDanKeuangan(fieldMap.get("bebanBungaDanKeuangan"));
        dto.setBebanPenjualan(fieldMap.get("bebanPenjualan"));
        dto.setBebanUmumDanAdministrasi(fieldMap.get("bebanUmumDanAdministrasi"));
        dto.setBebanLainnya(fieldMap.get("bebanLainnya"));
        dto.setBebanPenyusutanDanAmortisasi(fieldMap.get("bebanPenyusutanDanAmortisasi"));
        dto.setBebanManfaatKaryawan(fieldMap.get("bebanManfaatKaryawan"));
        dto.setBebanPemeliharaanDanPerbaikan(fieldMap.get("bebanPemeliharaanDanPerbaikan"));
        dto.setBebanOperasionalJaringan(fieldMap.get("bebanOperasionalJaringan"));
        dto.setBebanOperasionalTransportasi(fieldMap.get("bebanOperasionalTransportasi"));
        dto.setBebanInterkoneksi(fieldMap.get("bebanInterkoneksi"));
        dto.setBebanKonstruksi(fieldMap.get("bebanKonstruksi"));
        dto.setBebanTiketPenjualanDanPromosi(fieldMap.get("bebanTiketPenjualanDanPromosi"));
        dto.setBebanKerjasamaOperasi(fieldMap.get("bebanKerjasamaOperasi"));
        dto.setBebanPajakFinal(fieldMap.get("bebanPajakFinal"));

        // Beban Asuransi
        dto.setBebanKlaim(fieldMap.get("bebanKlaim"));
        dto.setBebanKomisi(fieldMap.get("bebanKomisi"));
        dto.setBebanAkuisisiDariKontrakAsuransi(fieldMap.get("bebanAkuisisiDariKontrakAsuransi"));
        dto.setBebanUnderwritingLainnya(fieldMap.get("bebanUnderwritingLainnya"));
        dto.setKlaimReasuransi(fieldMap.get("klaimReasuransi"));
        dto.setKlaimRetrosesi(fieldMap.get("klaimRetrosesi"));

        // Pendapatan
        dto.setPendapatanKeuangan(fieldMap.get("pendapatanKeuangan"));
        dto.setPendapatanBunga(fieldMap.get("pendapatanBunga"));
        dto.setPendapatanDividen(fieldMap.get("pendapatanDividen"));
        dto.setPendapatanKomisi(fieldMap.get("pendapatanKomisi"));
        dto.setPendapatanInvestasi(fieldMap.get("pendapatanInvestasi"));
        dto.setPendapatanBersihInvestasi(fieldMap.get("pendapatanBersihInvestasi"));

        // Pendapatan Asuransi
        dto.setPendapatanDariPremiAsuransi(fieldMap.get("pendapatanDariPremiAsuransi"));
        dto.setPremiReasuransi(fieldMap.get("premiReasuransi"));
        dto.setPremiRetrosesi(fieldMap.get("premiRetrosesi"));
        dto.setPenerimaanUjrah(fieldMap.get("penerimaanUjrah"));
        dto.setUjrahDibayar(fieldMap.get("ujrahDibayar"));
        dto.setPenghasilanUnderwritingLainnya(fieldMap.get("penghasilanUnderwritingLainnya"));

        // Comprehensive Income
        dto.setLabaRugiKomprehensif(fieldMap.get("labaRugiKomprehensif"));
        dto.setPendapatanKomprehensifLainnyaSetelahPajak(fieldMap.get("pendapatanKomprehensifLainnyaSetelahPajak"));
        dto.setPajakAtasPendapatanKomprehensifLainnya(fieldMap.get("pajakAtasPendapatanKomprehensifLainnya"));

        // Keuntungan/Kerugian
        dto.setKeuntunganKerugianSelisihKursMataUangAsing(fieldMap.get("keuntunganKerugianSelisihKursMataUangAsing"));
        dto.setKeuntunganKerugianAtasInstrumenKeuanganDerivatif(fieldMap.get("keuntunganKerugianAtasInstrumenKeuanganDerivatif"));
        dto.setKeuntunganKerugianDariTransaksiPerdaganganEfekYangTelahDirealisasi(fieldMap.get("keuntunganKerugianDariTransaksiPerdaganganEfekYangTelahDirealisasi"));
        dto.setKeuntunganKerugianPerubahanNilaiWajarEfek(fieldMap.get("keuntunganKerugianPerubahanNilaiWajarEfek"));
        dto.setKeuntunganKerugianLainnya(fieldMap.get("keuntunganKerugianLainnya"));
        dto.setLabaRugiPenjualanInvestasiPadaEfekDanReksadanaYangDiukurPadaNilaiWajarMelaluiLabaRugi(fieldMap.get("labaRugiPenjualanInvestasiPadaEfekDanReksadanaYangDiukurPadaNilaiWajarMelaluiLabaRugi"));
        dto.setLabaRugiYangBelumDirealisasiDariEfekDanReksadanaPadaNilaiWajarMelaluiLabaRugi(fieldMap.get("labaRugiYangBelumDirealisasiDariEfekDanReksadanaPadaNilaiWajarMelaluiLabaRugi"));
        dto.setLabaRugiPenjualanPadaPenyertaanSaham(fieldMap.get("labaRugiPenjualanPadaPenyertaanSaham"));

        // Metode Ekuitas
        dto.setBagianAtasLabaRugiEntitasAsosiasiYangDicatatDenganMenggunakanMetodeEkuitas(fieldMap.get("bagianAtasLabaRugiEntitasAsosiasiYangDicatatDenganMenggunakanMetodeEkuitas"));
        dto.setBagianAtasLabaRugiEntitasVenturaBersamaYangDicatatMenggunakanMetodeEkuitas(fieldMap.get("bagianAtasLabaRugiEntitasVenturaBersamaYangDicatatMenggunakanMetodeEkuitas"));

        // Perubahan Liabilitas
        dto.setKenaikanPenurunanEstimasiLiabilitasKlaim(fieldMap.get("kenaikanPenurunanEstimasiLiabilitasKlaim"));
        dto.setKenaikanPenurunanLiabilitasAsuransiYangDisesikanKepadaReasuradur(fieldMap.get("kenaikanPenurunanLiabilitasAsuransiYangDisesikanKepadaReasuradur"));
        dto.setKenaikanPenurunanLiabilitasManfaatPolisMasaDepan(fieldMap.get("kenaikanPenurunanLiabilitasManfaatPolisMasaDepan"));
        dto.setKenaikanPenurunanLiabilitasPemegangPolisPadaKontrakUnitLinked(fieldMap.get("kenaikanPenurunanLiabilitasPemegangPolisPadaKontrakUnitLinked"));
        dto.setKenaikanPenurunanProvisiYangTimbulDariTesKecukupanLiabilitas(fieldMap.get("kenaikanPenurunanProvisiYangTimbulDariTesKecukupanLiabilitas"));
        dto.setPenurunanKenaikanPendapatanPremiDisesikanKepadaReasuradur(fieldMap.get("penurunanKenaikanPendapatanPremiDisesikanKepadaReasuradur"));
        dto.setPenurunanKenaikanPremiYangBelumMerupakanPendapatan(fieldMap.get("penurunanKenaikanPremiYangBelumMerupakanPendapatan"));

        // Persediaan
        dto.setBahanBakuDanBarangHabisPakai(fieldMap.get("bahanBakuDanBarangHabisPakai"));
        dto.setKenaikanPenurunanPersediaanBarangJadiDanPekerjaanDalamProses(fieldMap.get("kenaikanPenurunanPersediaanBarangJadiDanPekerjaanDalamProses"));

        // Penurunan Nilai
        dto.setPembentukanPembalikanKerugianPenurunanNilaiYangDiakuiDalamLabaRugi(fieldMap.get("pembentukanPembalikanKerugianPenurunanNilaiYangDiakuiDalamLabaRugi"));

        // Operasi yang Dihentikan
        dto.setLabaRugiDariOperasiYangDihentikan(fieldMap.get("labaRugiDariOperasiYangDihentikan"));
        dto.setLabaRugiDariOperasiYangDilanjutkan(fieldMap.get("labaRugiDariOperasiYangDilanjutkan"));

        // Total & Abstrak
        dto.setTotal(fieldMap.get("Total"));
        dto.setBebanAbstrak(fieldMap.get("bebanAbstrak"));
        dto.setPendapatanAbstrak(fieldMap.get("pendapatanAbstrak"));
        dto.setPendapatanDanBebanBukanOperasionalAbstrak(fieldMap.get("pendapatanDanBebanBukanOperasionalAbstrak"));
        dto.setPendapatanDanBebanOperasionalAbstrak(fieldMap.get("pendapatanDanBebanOperasionalAbstrak"));
        dto.setLabaRugiPerSahamAbstrak(fieldMap.get("labaRugiPerSahamAbstrak"));
        dto.setLabaRugiYangDapatDiatribusikanAbstrak(fieldMap.get("labaRugiYangDapatDiatribusikanAbstrak"));
        dto.setLabaRugiKomprehensifYangDapatDiatribusikanAbstrak(fieldMap.get("labaRugiKomprehensifYangDapatDiatribusikanAbstrak"));
        dto.setPendapatanKomprehensifLainnyaSebelumPajakAbstrak(fieldMap.get("pendapatanKomprehensifLainnyaSebelumPajakAbstrak"));
        dto.setPendapatanKomprehensifLainnyaSetelahPajakAbstrak(fieldMap.get("pendapatanKomprehensifLainnyaSetelahPajakAbstrak"));

        return dto;
    }
}

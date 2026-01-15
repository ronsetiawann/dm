package com.marketdata.market_data_service.cacp.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDate;

/**
 * DTO for Fundamental Data (Indonesian) - Laporan Laba Rugi + Rasio Keuangan
 * Menggabungkan data dari tabel financial_report dan financial_ratio
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FundamentalIndDTO {

    // Informasi Dasar
    @JsonProperty("stockId")
    private String stockId;

    @JsonProperty("tahun")
    private Integer tahun;

    @JsonProperty("kuartal")
    private Integer kuartal;

    @JsonProperty("tanggal")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate tanggal;

    // ============================================
    // DATA LAPORAN LABA RUGI (dari financial_report)
    // ============================================

    // Pendapatan & Laba
    @JsonProperty("penjualanDanPendapatanUsaha")
    private Double penjualanDanPendapatanUsaha;

    @JsonProperty("labaBruto")
    private Double labaBruto;

    @JsonProperty("labaOperasional")
    private Double labaOperasional;

    @JsonProperty("labaRugiSebelumPajakPenghasilan")
    private Double labaRugiSebelumPajakPenghasilan;

    @JsonProperty("labaRugi")
    private Double labaRugi;

    // Beban Operasional
    @JsonProperty("bebanPokokPenjualanDanPendapatan")
    private Double bebanPokokPenjualanDanPendapatan;

    @JsonProperty("bebanBungaDanKeuangan")
    private Double bebanBungaDanKeuangan;

    @JsonProperty("bebanPenjualan")
    private Double bebanPenjualan;

    @JsonProperty("bebanUmumDanAdministrasi")
    private Double bebanUmumDanAdministrasi;

    @JsonProperty("bebanLainnya")
    private Double bebanLainnya;

    @JsonProperty("bebanPenyusutanDanAmortisasi")
    private Double bebanPenyusutanDanAmortisasi;

    @JsonProperty("bebanManfaatKaryawan")
    private Double bebanManfaatKaryawan;

    @JsonProperty("bebanPemeliharaanDanPerbaikan")
    private Double bebanPemeliharaanDanPerbaikan;

    @JsonProperty("bebanOperasionalJaringan")
    private Double bebanOperasionalJaringan;

    @JsonProperty("bebanOperasionalTransportasi")
    private Double bebanOperasionalTransportasi;

    @JsonProperty("bebanInterkoneksi")
    private Double bebanInterkoneksi;

    @JsonProperty("bebanKonstruksi")
    private Double bebanKonstruksi;

    @JsonProperty("bebanTiketPenjualanDanPromosi")
    private Double bebanTiketPenjualanDanPromosi;

    @JsonProperty("bebanKerjasamaOperasi")
    private Double bebanKerjasamaOperasi;

    @JsonProperty("bebanPajakFinal")
    private Double bebanPajakFinal;

    @JsonProperty("pendapatanBebanPajak")
    private Double pendapatanBebanPajak;

    // Beban Asuransi
    @JsonProperty("bebanKlaim")
    private Double bebanKlaim;

    @JsonProperty("bebanKomisi")
    private Double bebanKomisi;

    @JsonProperty("bebanAkuisisiDariKontrakAsuransi")
    private Double bebanAkuisisiDariKontrakAsuransi;

    @JsonProperty("bebanUnderwritingLainnya")
    private Double bebanUnderwritingLainnya;

    @JsonProperty("klaimReasuransi")
    private Double klaimReasuransi;

    @JsonProperty("klaimRetrosesi")
    private Double klaimRetrosesi;

    // Pendapatan
    @JsonProperty("pendapatanKeuangan")
    private Double pendapatanKeuangan;

    @JsonProperty("pendapatanBunga")
    private Double pendapatanBunga;

    @JsonProperty("pendapatanDividen")
    private Double pendapatanDividen;

    @JsonProperty("pendapatanKomisi")
    private Double pendapatanKomisi;

    @JsonProperty("pendapatanInvestasi")
    private Double pendapatanInvestasi;

    @JsonProperty("pendapatanBersihInvestasi")
    private Double pendapatanBersihInvestasi;

    @JsonProperty("pendapatanLainnya")
    private Double pendapatanLainnya;

    // Pendapatan Asuransi
    @JsonProperty("pendapatanDariPremiAsuransi")
    private Double pendapatanDariPremiAsuransi;

    @JsonProperty("premiReasuransi")
    private Double premiReasuransi;

    @JsonProperty("premiRetrosesi")
    private Double premiRetrosesi;

    @JsonProperty("penerimaanUjrah")
    private Double penerimaanUjrah;

    @JsonProperty("ujrahDibayar")
    private Double ujrahDibayar;

    @JsonProperty("penghasilanUnderwritingLainnya")
    private Double penghasilanUnderwritingLainnya;

    // Comprehensive Income
    @JsonProperty("labaRugiKomprehensif")
    private Double labaRugiKomprehensif;

    @JsonProperty("pendapatanKomprehensifLainnyaSetelahPajak")
    private Double pendapatanKomprehensifLainnyaSetelahPajak;

    @JsonProperty("pajakAtasPendapatanKomprehensifLainnya")
    private Double pajakAtasPendapatanKomprehensifLainnya;

    // Keuntungan/Kerugian
    @JsonProperty("keuntunganKerugianSelisihKursMataUangAsing")
    private Double keuntunganKerugianSelisihKursMataUangAsing;

    @JsonProperty("keuntunganKerugianAtasInstrumenKeuanganDerivatif")
    private Double keuntunganKerugianAtasInstrumenKeuanganDerivatif;

    @JsonProperty("keuntunganKerugianDariTransaksiPerdaganganEfekYangTelahDirealisasi")
    private Double keuntunganKerugianDariTransaksiPerdaganganEfekYangTelahDirealisasi;

    @JsonProperty("keuntunganKerugianPerubahanNilaiWajarEfek")
    private Double keuntunganKerugianPerubahanNilaiWajarEfek;

    @JsonProperty("keuntunganKerugianLainnya")
    private Double keuntunganKerugianLainnya;

    @JsonProperty("labaRugiPenjualanInvestasiPadaEfekDanReksadanaYangDiukurPadaNilaiWajarMelaluiLabaRugi")
    private Double labaRugiPenjualanInvestasiPadaEfekDanReksadanaYangDiukurPadaNilaiWajarMelaluiLabaRugi;

    @JsonProperty("labaRugiYangBelumDirealisasiDariEfekDanReksadanaPadaNilaiWajarMelaluiLabaRugi")
    private Double labaRugiYangBelumDirealisasiDariEfekDanReksadanaPadaNilaiWajarMelaluiLabaRugi;

    @JsonProperty("labaRugiPenjualanPadaPenyertaanSaham")
    private Double labaRugiPenjualanPadaPenyertaanSaham;

    // Metode Ekuitas
    @JsonProperty("bagianAtasLabaRugiEntitasAsosiasiYangDicatatDenganMenggunakanMetodeEkuitas")
    private Double bagianAtasLabaRugiEntitasAsosiasiYangDicatatDenganMenggunakanMetodeEkuitas;

    @JsonProperty("bagianAtasLabaRugiEntitasVenturaBersamaYangDicatatMenggunakanMetodeEkuitas")
    private Double bagianAtasLabaRugiEntitasVenturaBersamaYangDicatatMenggunakanMetodeEkuitas;

    // Perubahan Liabilitas
    @JsonProperty("kenaikanPenurunanEstimasiLiabilitasKlaim")
    private Double kenaikanPenurunanEstimasiLiabilitasKlaim;

    @JsonProperty("kenaikanPenurunanLiabilitasAsuransiYangDisesikanKepadaReasuradur")
    private Double kenaikanPenurunanLiabilitasAsuransiYangDisesikanKepadaReasuradur;

    @JsonProperty("kenaikanPenurunanLiabilitasManfaatPolisMasaDepan")
    private Double kenaikanPenurunanLiabilitasManfaatPolisMasaDepan;

    @JsonProperty("kenaikanPenurunanLiabilitasPemegangPolisPadaKontrakUnitLinked")
    private Double kenaikanPenurunanLiabilitasPemegangPolisPadaKontrakUnitLinked;

    @JsonProperty("kenaikanPenurunanProvisiYangTimbulDariTesKecukupanLiabilitas")
    private Double kenaikanPenurunanProvisiYangTimbulDariTesKecukupanLiabilitas;

    @JsonProperty("penurunanKenaikanPendapatanPremiDisesikanKepadaReasuradur")
    private Double penurunanKenaikanPendapatanPremiDisesikanKepadaReasuradur;

    @JsonProperty("penurunanKenaikanPremiYangBelumMerupakanPendapatan")
    private Double penurunanKenaikanPremiYangBelumMerupakanPendapatan;

    // Persediaan
    @JsonProperty("bahanBakuDanBarangHabisPakai")
    private Double bahanBakuDanBarangHabisPakai;

    @JsonProperty("kenaikanPenurunanPersediaanBarangJadiDanPekerjaanDalamProses")
    private Double kenaikanPenurunanPersediaanBarangJadiDanPekerjaanDalamProses;

    // Penurunan Nilai
    @JsonProperty("pembentukanPembalikanKerugianPenurunanNilaiYangDiakuiDalamLabaRugi")
    private Double pembentukanPembalikanKerugianPenurunanNilaiYangDiakuiDalamLabaRugi;

    // Operasi yang Dihentikan
    @JsonProperty("labaRugiDariOperasiYangDihentikan")
    private Double labaRugiDariOperasiYangDihentikan;

    @JsonProperty("labaRugiDariOperasiYangDilanjutkan")
    private Double labaRugiDariOperasiYangDilanjutkan;

    // Total & Abstrak
    @JsonProperty("Total")
    private Double total;

    @JsonProperty("bebanAbstrak")
    private Double bebanAbstrak;

    @JsonProperty("pendapatanAbstrak")
    private Double pendapatanAbstrak;

    @JsonProperty("pendapatanDanBebanBukanOperasionalAbstrak")
    private Double pendapatanDanBebanBukanOperasionalAbstrak;

    @JsonProperty("pendapatanDanBebanOperasionalAbstrak")
    private Double pendapatanDanBebanOperasionalAbstrak;

    @JsonProperty("labaRugiPerSahamAbstrak")
    private Double labaRugiPerSahamAbstrak;

    @JsonProperty("labaRugiYangDapatDiatribusikanAbstrak")
    private Double labaRugiYangDapatDiatribusikanAbstrak;

    @JsonProperty("labaRugiKomprehensifYangDapatDiatribusikanAbstrak")
    private Double labaRugiKomprehensifYangDapatDiatribusikanAbstrak;

    @JsonProperty("pendapatanKomprehensifLainnyaSebelumPajakAbstrak")
    private Double pendapatanKomprehensifLainnyaSebelumPajakAbstrak;

    @JsonProperty("pendapatanKomprehensifLainnyaSetelahPajakAbstrak")
    private Double pendapatanKomprehensifLainnyaSetelahPajakAbstrak;

    // ============================================
    // RASIO KEUANGAN (dari financial_ratio)
    // ============================================

    // Rasio Profitabilitas
    @JsonProperty("gpm")
    private Double gpm;  // Marjin Laba Kotor

    @JsonProperty("opm")
    private Double opm;  // Marjin Laba Operasional

    @JsonProperty("npm")
    private Double npm;  // Marjin Laba Bersih

    @JsonProperty("roe")
    private Double roe;  // Return on Equity

    @JsonProperty("roa")
    private Double roa;  // Return on Assets

    @JsonProperty("ebitMargin")
    private Double ebitMargin;  // Marjin EBIT

    // Rasio Likuiditas
    @JsonProperty("currentRatio")
    private Double currentRatio;  // Rasio Lancar

    @JsonProperty("quickRatio")
    private Double quickRatio;  // Rasio Cepat

    @JsonProperty("cashRatio")
    private Double cashRatio;  // Rasio Kas

    // Rasio Leverage/Solvabilitas
    @JsonProperty("dar")
    private Double dar;  // Rasio Utang terhadap Aset

    @JsonProperty("der")
    private Double der;  // Rasio Utang terhadap Ekuitas

    @JsonProperty("longTermDebtToEquity")
    private Double longTermDebtToEquity;  // Utang Jangka Panjang terhadap Ekuitas

    @JsonProperty("liabilitiesToAssets")
    private Double liabilitiesToAssets;  // Liabilitas terhadap Aset

    @JsonProperty("liabilitiesToEquity")
    private Double liabilitiesToEquity;  // Liabilitas terhadap Ekuitas

    @JsonProperty("financialLeverage")
    private Double financialLeverage;  // Leverage Keuangan

    // Rasio Efisiensi
    @JsonProperty("ato")
    private Double ato;  // Asset Turnover (Perputaran Aset)

    // Rasio Valuasi
    @JsonProperty("per")
    private Double per;  // Price to Earnings Ratio

    @JsonProperty("pbv")
    private Double pbv;  // Price to Book Value

    // Metrik Per Saham
    @JsonProperty("eps")
    private Double eps;  // Laba per Saham

    @JsonProperty("bvps")
    private Double bvps;  // Nilai Buku per Saham

    @JsonProperty("revenuePerShares")
    private Double revenuePerShares;  // Pendapatan per Saham

    @JsonProperty("cashPerShare")
    private Double cashPerShare;  // Kas per Saham

    @JsonProperty("cashflowPerShare")
    private Double cashflowPerShare;  // Arus Kas per Saham

    @JsonProperty("freeCashflowPerShare")
    private Double freeCashflowPerShare;  // Arus Kas Bebas per Saham

    @JsonProperty("netAssetsPerShare")
    private Double netAssetsPerShare;  // Aset Bersih per Saham
}
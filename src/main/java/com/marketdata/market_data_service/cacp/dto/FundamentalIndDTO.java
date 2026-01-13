package com.marketdata.market_data_service.cacp.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * Indonesian version DTO - Native field names from financial_data table
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FundamentalIndDTO {

    private String stockId;
    private Integer tahun;
    private Integer kuartal;
    private LocalDate tanggal;
    private String tahunFiskal;
    private Integer bulanCakupan;

    // ===== LAPORAN LABA RUGI =====
    private Double labaBruto;
    private Double labaRugi;
    private Double penjualanDanPendapatanUsaha;
    private Double labaOperasional;
    private Double bebanPokokPenjualanDanPendapatan;
    private Double labaRugiSebelumPajakPenghasilan;
    private Double pendapatanBebanPajak;
    private Double pendapatanLainnya;

    // ===== BEBAN =====
    private Double bebanBungaDanKeuangan;
    private Double bebanPenjualan;
    private Double bebanUmumDanAdministrasi;
    private Double bebanLainnya;
    private Double bebanPenyusutanDanAmortisasi;
    private Double bebanManfaatKaryawan;
    private Double bebanPemeliharaanDanPerbaikan;
    private Double bebanOperasionalJaringan;
    private Double bebanOperasionalTransportasi;
    private Double bebanInterkoneksi;
    private Double bebanKonstruksi;
    private Double bebanTiketPenjualanDanPromosi;
    private Double bebanKerjasamaOperasi;
    private Double bebanPajakFinal;

    // ===== BEBAN ASURANSI =====
    private Double bebanKlaim;
    private Double bebanKomisi;
    private Double bebanAkuisisiDariKontrakAsuransi;
    private Double bebanUnderwritingLainnya;
    private Double klaimReasuransi;
    private Double klaimRetrosesi;

    // ===== PENDAPATAN =====
    private Double pendapatanKeuangan;
    private Double pendapatanBunga;
    private Double pendapatanDividen;
    private Double pendapatanKomisi;
    private Double pendapatanInvestasi;
    private Double pendapatanBersihInvestasi;

    // ===== PENDAPATAN ASURANSI =====
    private Double pendapatanDariPremiAsuransi;
    private Double premiReasuransi;
    private Double premiRetrosesi;
    private Double penerimaanUjrah;
    private Double ujrahDibayar;
    private Double penghasilanUnderwritingLainnya;

    // ===== LABA RUGI KOMPREHENSIF =====
    private Double labaRugiKomprehensif;
    private Double pendapatanKomprehensifLainnyaSetelahPajak;
    private Double pajakAtasPendapatanKomprehensifLainnya;

    // ===== KEUNTUNGAN/KERUGIAN =====
    private Double keuntunganKerugianSelisihKursMataUangAsing;
    private Double keuntunganKerugianAtasInstrumenKeuanganDerivatif;
    private Double keuntunganKerugianDariTransaksiPerdaganganEfekYangTelahDirealisasi;
    private Double keuntunganKerugianPerubahanNilaiWajarEfek;
    private Double keuntunganKerugianLainnya;
    private Double labaRugiPenjualanInvestasiPadaEfekDanReksadanaYangDiukurPadaNilaiWajarMelaluiLabaRugi;
    private Double labaRugiYangBelumDirealisasiDariEfekDanReksadanaPadaNilaiWajarMelaluiLabaRugi;
    private Double labaRugiPenjualanPadaPenyertaanSaham;

    // ===== METODE EKUITAS =====
    private Double bagianAtasLabaRugiEntitasAsosiasiYangDicatatDenganMenggunakanMetodeEkuitas;
    private Double bagianAtasLabaRugiEntitasVenturaBersamaYangDicatatMenggunakanMetodeEkuitas;

    // ===== PERUBAHAN LIABILITAS =====
    private Double kenaikanPenurunanEstimasiLiabilitasKlaim;
    private Double kenaikanPenurunanLiabilitasAsuransiYangDisesikanKepadaReasuradur;
    private Double kenaikanPenurunanLiabilitasManfaatPolisMasaDepan;
    private Double kenaikanPenurunanLiabilitasPemegangPolisPadaKontrakUnitLinked;
    private Double kenaikanPenurunanProvisiYangTimbulDariTesKecukupanLiabilitas;
    private Double penurunanKenaikanPendapatanPremiDisesikanKepadaReasuradur;
    private Double penurunanKenaikanPremiYangBelumMerupakanPendapatan;

    // ===== PERSEDIAAN =====
    private Double bahanBakuDanBarangHabisPakai;
    private Double kenaikanPenurunanPersediaanBarangJadiDanPekerjaanDalamProses;

    // ===== PENURUNAN NILAI =====
    private Double pembentukanPembalikanKerugianPenurunanNilaiYangDiakuiDalamLabaRugi;

    // ===== OPERASI YANG DIHENTIKAN =====
    private Double labaRugiDariOperasiYangDihentikan;
    private Double labaRugiDariOperasiYangDilanjutkan;

    // ===== TOTAL & ABSTRAK =====
    private Double total;
    private Double bebanAbstrak;
    private Double pendapatanAbstrak;
    private Double pendapatanDanBebanBukanOperasionalAbstrak;
    private Double pendapatanDanBebanOperasionalAbstrak;
    private Double labaRugiPerSahamAbstrak;
    private Double labaRugiYangDapatDiatribusikanAbstrak;
    private Double labaRugiKomprehensifYangDapatDiatribusikanAbstrak;
    private Double pendapatanKomprehensifLainnyaSebelumPajakAbstrak;
    private Double pendapatanKomprehensifLainnyaSetelahPajakAbstrak;
}
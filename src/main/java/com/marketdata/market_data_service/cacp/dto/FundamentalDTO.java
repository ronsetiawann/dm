package com.marketdata.market_data_service.cacp.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * English version DTO - Backward compatible with C# API format
 * Fields that are not available in financial_data table will be null
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonAutoDetect(
        fieldVisibility = JsonAutoDetect.Visibility.NONE,
        getterVisibility = JsonAutoDetect.Visibility.NONE,
        setterVisibility = JsonAutoDetect.Visibility.NONE,
        isGetterVisibility = JsonAutoDetect.Visibility.NONE,
        creatorVisibility = JsonAutoDetect.Visibility.NONE
)
public class FundamentalDTO {

    @JsonProperty("StockID")
    private String stockId;

    @JsonProperty("Year")
    private Integer year;

    @JsonProperty("Quarter")
    private Integer quarter;

    @JsonProperty("Date")
    private LocalDate date;

    @JsonProperty("FiscalYear")
    private String fiscalYear;

    @JsonProperty("MonthCover")
    private Integer monthCover;

    // ===== INCOME STATEMENT (Available in financial_data) =====

    @JsonProperty("GrossProfit")
    private Double grossProfit;  // labaBruto

    @JsonProperty("NetIncome")
    private Double netIncome;  // labaRugi

    @JsonProperty("TotalSales")
    private Double totalSales;  // penjualanDanPendapatanUsaha

    @JsonProperty("OperatingProfit")
    private Double operatingProfit;  // labaOperasional

    @JsonProperty("CostGoodSold")
    private Double costGoodSold;  // bebanPokokPenjualanDanPendapatan

    @JsonProperty("EarningBeforeTax")
    private Double earningBeforeTax;  // labaRugiSebelumPajakPenghasilan

    @JsonProperty("Tax")
    private Double tax;  // pendapatanBebanPajak (negative value)

    @JsonProperty("OtherIncome")
    private Double otherIncome;  // pendapatanLainnya

    // ===== EXPENSES =====

    @JsonProperty("InterestExpense")
    private Double interestExpense;  // bebanBungaDanKeuangan

    @JsonProperty("SalesExpense")
    private Double salesExpense;  // bebanPenjualan

    @JsonProperty("GeneralAdminExpense")
    private Double generalAdminExpense;  // bebanUmumDanAdministrasi

    @JsonProperty("OtherExpense")
    private Double otherExpense;  // bebanLainnya

    @JsonProperty("DepreciationAmortization")
    private Double depreciationAmortization;  // bebanPenyusutanDanAmortisasi

    @JsonProperty("EmployeeBenefits")
    private Double employeeBenefits;  // bebanManfaatKaryawan

    // ===== INCOME =====

    @JsonProperty("FinancialIncome")
    private Double financialIncome;  // pendapatanKeuangan

    @JsonProperty("InterestIncome")
    private Double interestIncome;  // pendapatanBunga

    @JsonProperty("DividendIncome")
    private Double dividendIncome;  // pendapatanDividen

    @JsonProperty("CommissionIncome")
    private Double commissionIncome;  // pendapatanKomisi

    @JsonProperty("InvestmentIncome")
    private Double investmentIncome;  // pendapatanInvestasi

    // ===== COMPREHENSIVE INCOME =====

    @JsonProperty("ComprehensiveIncome")
    private Double comprehensiveIncome;  // labaRugiKomprehensif

    @JsonProperty("OtherComprehensiveIncome")
    private Double otherComprehensiveIncome;  // pendapatanKomprehensifLainnyaSetelahPajak

    @JsonProperty("TaxOnOtherComprehensiveIncome")
    private Double taxOnOtherComprehensiveIncome;  // pajakAtasPendapatanKomprehensifLainnya

    // ===== FOREX & DERIVATIVES =====

    @JsonProperty("ForexGainLoss")
    private Double forexGainLoss;  // keuntunganKerugianSelisihKursMataUangAsing

    @JsonProperty("DerivativeGainLoss")
    private Double derivativeGainLoss;  // keuntunganKerugianAtasInstrumenKeuanganDerivatif

    // ===== EQUITY METHOD =====

    @JsonProperty("ShareOfAssociatesProfit")
    private Double shareOfAssociatesProfit;  // bagianAtasLabaRugiEntitasAsosiasiYangDicatatDenganMenggunakanMetodeEkuitas

    @JsonProperty("ShareOfJointVenturesProfit")
    private Double shareOfJointVenturesProfit;  // bagianAtasLabaRugiEntitasVenturaBersamaYangDicatatMenggunakanMetodeEkuitas

    // ===== FIELDS NOT AVAILABLE IN financial_data (Legacy compatibility) =====
    // These will be null as they don't exist in the new data source

    @JsonProperty("ParValueA")
    private Integer parValueA = null;

    @JsonProperty("ParValueB")
    private Integer parValueB = null;

    @JsonProperty("ParValueC")
    private Integer parValueC = null;

    @JsonProperty("ParValueD")
    private Integer parValueD = null;

    @JsonProperty("ClosePrice")
    private Double closePrice = null;

    @JsonProperty("Receivables")
    private Double receivables = null;

    @JsonProperty("Inventories")
    private Double inventories = null;

    @JsonProperty("CurrentAssets")
    private Double currentAssets = null;

    @JsonProperty("FixedAssets")
    private Double fixedAssets = null;

    @JsonProperty("OtherAssets")
    private Double otherAssets = null;

    @JsonProperty("TotalAssets")
    private Double totalAssets = null;

    @JsonProperty("CurrentLiabilities")
    private Double currentLiabilities = null;

    @JsonProperty("LongTermLiabilities")
    private Double longTermLiabilities = null;

    @JsonProperty("TotalLiabilities")
    private Double totalLiabilities = null;

    @JsonProperty("Authorized")
    private Double authorized = null;

    @JsonProperty("PaidupCap")
    private Double paidupCap = null;

    @JsonProperty("PaidupCapShares")
    private Double paidupCapShares = null;

    @JsonProperty("RetainedEarn")
    private Double retainedEarn = null;

    @JsonProperty("TotalEquity")
    private Double totalEquity = null;

    @JsonProperty("MinInterest")
    private Double minInterest = null;

    @JsonProperty("EPS")
    private Double eps = null;

    @JsonProperty("BookValue")
    private Double bookValue = null;

    @JsonProperty("PriceEarningRatio")
    private Double priceEarningRatio = null;

    @JsonProperty("PriceBookValue")
    private Double priceBookValue = null;

    @JsonProperty("DebtEquityRatio")
    private Double debtEquityRatio = null;

    @JsonProperty("ROAPercent")
    private Double roaPercent = null;

    @JsonProperty("ROEPercent")
    private Double roePercent = null;

    @JsonProperty("NPMPercent")
    private Double npmPercent = null;

    @JsonProperty("OPMPercent")
    private Double opmPercent = null;

    @JsonProperty("CFOperateActs")
    private Double cfOperateActs = null;

    @JsonProperty("CFInvestActs")
    private Double cfInvestActs = null;

    @JsonProperty("CFFinActs")
    private Double cfFinActs = null;

    @JsonProperty("NetIncreaseCashAndCashEquivalent")
    private Double netIncreaseCashAndCashEquivalent = null;

    @JsonProperty("CashAndCashEquivalentBeginYear")
    private Double cashAndCashEquivalentBeginYear = null;

    @JsonProperty("CashAndCashEquivalentEndYear")
    private Double cashAndCashEquivalentEndYear = null;

    @JsonProperty("CashAndCashEquivalent")
    private Double cashAndCashEquivalent = null;

    @JsonProperty("NonCurrentAssets")
    private Double nonCurrentAssets = null;

    @JsonProperty("GrossProfitMarginPercent")
    private Double grossProfitMarginPercent = null;

    @JsonProperty("TotalAssetsTurnover")
    private Double totalAssetsTurnover = null;
}

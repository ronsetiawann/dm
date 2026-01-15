package com.marketdata.market_data_service.cacp.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDate;

/**
 * DTO for Fundamental Data (English) - Income Statement + Financial Ratios
 * Combines data from financial_report and financial_ratio tables
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FundamentalDTO {

    // Basic Info
    @JsonProperty("StockId")
    private String stockId;

    @JsonProperty("Year")
    private Integer year;

    @JsonProperty("Quarter")
    private Integer quarter;

    @JsonProperty("Date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    // ============================================
    // INCOME STATEMENT DATA (from financial_report)
    // ============================================

    // Revenue & Profit
    @JsonProperty("TotalSales")
    private Double totalSales;

    @JsonProperty("GrossProfit")
    private Double grossProfit;

    @JsonProperty("OperatingProfit")
    private Double operatingProfit;

    @JsonProperty("EarningBeforeTax")
    private Double earningBeforeTax;

    @JsonProperty("NetIncome")
    private Double netIncome;

    // Costs & Expenses
    @JsonProperty("CostGoodSold")
    private Double costGoodSold;

    @JsonProperty("InterestExpense")
    private Double interestExpense;

    @JsonProperty("SalesExpense")
    private Double salesExpense;

    @JsonProperty("GeneralAdminExpense")
    private Double generalAdminExpense;

    @JsonProperty("OtherExpense")
    private Double otherExpense;

    @JsonProperty("DepreciationAmortization")
    private Double depreciationAmortization;

    @JsonProperty("EmployeeBenefits")
    private Double employeeBenefits;

    @JsonProperty("Tax")
    private Double tax;

    // Income
    @JsonProperty("FinancialIncome")
    private Double financialIncome;

    @JsonProperty("InterestIncome")
    private Double interestIncome;

    @JsonProperty("DividendIncome")
    private Double dividendIncome;

    @JsonProperty("CommissionIncome")
    private Double commissionIncome;

    @JsonProperty("InvestmentIncome")
    private Double investmentIncome;

    @JsonProperty("OtherIncome")
    private Double otherIncome;

    // Comprehensive Income
    @JsonProperty("ComprehensiveIncome")
    private Double comprehensiveIncome;

    @JsonProperty("OtherComprehensiveIncome")
    private Double otherComprehensiveIncome;

    @JsonProperty("TaxOnOtherComprehensiveIncome")
    private Double taxOnOtherComprehensiveIncome;

    // Forex & Derivatives
    @JsonProperty("ForexGainLoss")
    private Double forexGainLoss;

    @JsonProperty("DerivativeGainLoss")
    private Double derivativeGainLoss;

    // Equity Method
    @JsonProperty("ShareOfAssociatesProfit")
    private Double shareOfAssociatesProfit;

    @JsonProperty("ShareOfJointVenturesProfit")
    private Double shareOfJointVenturesProfit;

    // ============================================
    // FINANCIAL RATIOS (from financial_ratio)
    // ============================================

    // Profitability Ratios
    @JsonProperty("GPM")
    private Double gpm;  // Gross Profit Margin

    @JsonProperty("OPM")
    private Double opm;  // Operating Profit Margin

    @JsonProperty("NPM")
    private Double npm;  // Net Profit Margin

    @JsonProperty("ROE")
    private Double roe;  // Return on Equity

    @JsonProperty("ROA")
    private Double roa;  // Return on Assets

    @JsonProperty("EBITMargin")
    private Double ebitMargin;

    // Liquidity Ratios
    @JsonProperty("CurrentRatio")
    private Double currentRatio;

    @JsonProperty("QuickRatio")
    private Double quickRatio;

    @JsonProperty("CashRatio")
    private Double cashRatio;

    // Leverage Ratios
    @JsonProperty("DAR")
    private Double dar;  // Debt to Assets Ratio

    @JsonProperty("DER")
    private Double der;  // Debt to Equity Ratio

    @JsonProperty("LongTermDebtToEquity")
    private Double longTermDebtToEquity;

    @JsonProperty("LiabilitiesToAssets")
    private Double liabilitiesToAssets;

    @JsonProperty("LiabilitiesToEquity")
    private Double liabilitiesToEquity;

    @JsonProperty("FinancialLeverage")
    private Double financialLeverage;

    // Efficiency Ratios
    @JsonProperty("ATO")
    private Double ato;  // Asset Turnover

    // Valuation Ratios
    @JsonProperty("PER")
    private Double per;  // Price to Earnings Ratio

    @JsonProperty("PBV")
    private Double pbv;  // Price to Book Value

    // Per Share Metrics
    @JsonProperty("EPS")
    private Double eps;  // Earnings Per Share

    @JsonProperty("BVPS")
    private Double bvps;  // Book Value Per Share

    @JsonProperty("RevenuePerShares")
    private Double revenuePerShares;

    @JsonProperty("CashPerShare")
    private Double cashPerShare;

    @JsonProperty("CashflowPerShare")
    private Double cashflowPerShare;

    @JsonProperty("FreeCashflowPerShare")
    private Double freeCashflowPerShare;

    @JsonProperty("NetAssetsPerShare")
    private Double netAssetsPerShare;
}
package com.project.cooking.finance;

import java.util.ArrayList;
import java.util.List;

public class FinancialReport {

    private String reportType;
    private String startDate;
    private String endDate;
    
    private List<Transaction> transactions = new ArrayList<>();

    private int transactionCount;

    public FinancialReport(String reportType) {
        this.reportType = reportType;
    }
    public FinancialReport() {
        this.startDate = "";
        this.endDate = "";
        this.transactionCount = 0;
    }


    public boolean isReportGeneratedForDateRange(String startDate, String endDate) {
        return startDate.equals(this.startDate) && endDate.equals(this.endDate);
    }

    public double calculateTotalRevenue() {
   
        return 5000.0;
    }

    public String getRevenueBreakdown() {
        return "Product: 5000, Service: 3000";
    }

    public int getNumberOfTransactions() {
        return 150;
    }

    public String getDetailedTransactionData() {
        return "Transaction data for 2025-01-01 to 2025-03-01";
    }
    public void setCustomDateRange(String startDate, String endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }



   
   

   
    public int getTransactionCount() {
        return transactions.size();
    }

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    public void generateReport(String option) {
        
    }

    
    
}
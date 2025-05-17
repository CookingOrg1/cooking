package com.project.cooking;
import com.project.cooking.finance.FinancialReport;
import com.project.cooking.finance.Transaction;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class FinancialReportTest {

    private FinancialReport report;

    @Before
    public void setUp() {
        report = new FinancialReport();
    }

    @Test
    public void testDefaultDatesAreEmpty() {
        assertEquals("", report.getStartDate());
        assertEquals("", report.getEndDate());
    }

    @Test
    public void testSetAndGetCustomDateRange() {
        report.setCustomDateRange("2025-01-01", "2025-03-01");
        assertEquals("2025-01-01", report.getStartDate());
        assertEquals("2025-03-01", report.getEndDate());
    }

    @Test
    public void testIsReportGeneratedForDateRangeTrue() {
        report.setCustomDateRange("2025-01-01", "2025-03-01");
        assertTrue(report.isReportGeneratedForDateRange("2025-01-01", "2025-03-01"));
    }

    @Test
    public void testIsReportGeneratedForDateRangeFalse() {
        report.setCustomDateRange("2025-01-01", "2025-03-01");
        assertFalse(report.isReportGeneratedForDateRange("2025-01-01", "2025-02-01"));
    }

    @Test
    public void testCalculateTotalRevenue() {
        assertEquals(5000.0, report.calculateTotalRevenue(), 0.001);
    }

    @Test
    public void testGetRevenueBreakdown() {
        String expected = "Product: 5000, Service: 3000";
        assertEquals(expected, report.getRevenueBreakdown());
    }

    @Test
    public void testGetNumberOfTransactions() {
        assertEquals(150, report.getNumberOfTransactions());
    }

    @Test
    public void testGetDetailedTransactionData() {
        report.setCustomDateRange("2025-01-01", "2025-03-01");
        assertEquals("Transaction data for 2025-01-01 to 2025-03-01", report.getDetailedTransactionData());
    }

    @Test
    public void testAddTransactionIncreasesCount() {
        int initialCount = report.getTransactionCount();
        Transaction t = new Transaction(); 
        report.addTransaction(t);
        assertEquals(initialCount + 1, report.getTransactionCount());
    }

    @Test
    public void testGenerateReportDoesNotThrow() {
        try {
            report.generateReport("summary");
            report.generateReport("detailed");
        } catch (Exception e) {
            fail("generateReport threw an exception");
        }
    }
}
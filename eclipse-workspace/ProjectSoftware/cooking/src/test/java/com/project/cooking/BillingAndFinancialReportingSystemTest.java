package com.project.cooking;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import com.project.cooking.finance.*;

import static org.junit.Assert.*;

public class BillingAndFinancialReportingSystemTest {

    private FinancialReport report;
    private Invoice invoice;

    @Given("the customer has completed a purchase")
    public void theCustomerHasCompletedAPurchase() {
        String invoiceNumber = "12345"; 
        String customerName = "John Doe";
        int itemQuantity = 3;
        double itemPrice = 30.0;
        double taxes = 3.0;
        invoice = new Invoice(invoiceNumber, customerName, itemQuantity, itemPrice, taxes);
    }

    @When("the customer requests an invoice")
    public void theCustomerRequestsAnInvoice() {
       BillingService billingService = new BillingService();

        String invoiceNumber = "12345";
        String customerName = "John Doe";
        int itemQuantity = 3; 
        double itemPrice = 30.0; 
        double taxes = 3.0; 

        
         invoice = billingService.generateInvoice(invoiceNumber, customerName, itemQuantity, itemPrice, taxes);
    }

    @Then("the system should include a valid invoice number")
    public void theSystemShouldIncludeAValidInvoiceNumber() {
        assertNotNull(invoice.getInvoiceNumber());
        assertTrue(invoice.getInvoiceNumber().matches("\\d+"));
    }

    @Then("the invoice should show the correct price and quantity of each item purchased")
    public void theInvoiceShouldShowTheCorrectPriceAndQuantityOfEachItemPurchased() {
        assertEquals(30.0, invoice.getItemPrice(), 0.01);
        assertEquals(3, invoice.getItemQuantity());
    }

    @Then("the invoice should display the total amount with applicable taxes")
    public void theInvoiceShouldDisplayTheTotalAmountWithApplicableTaxes() {
        double expectedTotal = (3 * 30.0) + 3.0;  
        assertEquals(expectedTotal, invoice.getTotalAmount(), 0.01);
    }

    @Then("the invoice should include the customer's name, items purchased, total amount, and date of transaction")
    public void theInvoiceShouldIncludeTheCustomerSNameItemsPurchasedTotalAmountAndDateOfTransaction() {
        assertEquals("John Doe", invoice.getCustomerName());
        assertEquals("12345", invoice.getInvoiceNumber());
        assertNotNull(invoice.getTransactionDate());
    }

    @Given("the system administrator has logged into the system")
    public void theSystemAdministratorHasLoggedIntoTheSystem() {
        report = new FinancialReport();
    }
    @When("the system administrator selects the {string} option")
    public void theSystemAdministratorSelectsTheOption(String option) {
   
        report.generateReport(option);
    }

    @Then("the system should display the total revenue for the selected time period")
    public void theSystemShouldDisplayTheTotalRevenueForTheSelectedTimePeriod() {
        double totalRevenue = report.calculateTotalRevenue();
        assertTrue(totalRevenue > 0);
    }

    @Then("the report should break down revenue by product or service")
    public void theReportShouldBreakDownRevenueByProductOrService() {
        String breakdown = report.getRevenueBreakdown();
        assertNotNull(breakdown);
        assertTrue(breakdown.contains("Product"));
    }

    @Then("the report should include the number of transactions in the period")
    public void theReportShouldIncludeTheNumberOfTransactionsInThePeriod() {
        int transactionCount = report.getNumberOfTransactions();
        assertTrue(transactionCount > 0);
    }
    @When("the system administrator selects a custom date range for the report")
    public void theSystemAdministratorSelectsACustomDateRangeForTheReport() {
        report = new FinancialReport(); 

        report.addTransaction(new Transaction("2025-01-15", 100.0));
        report.addTransaction(new Transaction("2025-02-01", 200.0));
        report.addTransaction(new Transaction("2025-02-15", 300.0));
        report.addTransaction(new Transaction("2025-03-01", 400.0));
        report.addTransaction(new Transaction("2025-03-01", 500.0));

        report.setCustomDateRange("2025-01-01", "2025-03-01");
    }

    @Then("the system should generate a report covering the selected date range")
    public void theSystemShouldGenerateAReportCoveringTheSelectedDateRange() {
        assertEquals("2025-01-01", report.getStartDate());
        assertEquals("2025-03-01", report.getEndDate());
    }
    @Then("the report should show detailed transaction data within that period")
    public void theReportShouldShowDetailedTransactionDataWithinThatPeriod() {
        assertEquals(5, report.getTransactionCount());
    }
}
Feature: Billing System

  Scenario: Customer receives an invoice with valid details
    Given the customer has completed a purchase
    When the customer requests an invoice
    Then the system should include a valid invoice number
    And the invoice should show the correct price and quantity of each item purchased
    And the invoice should display the total amount with applicable taxes
    And the invoice should include the customer's name, items purchased, total amount, and date of transaction

  Scenario: System administrator generates financial report for revenue analysis
    Given the system administrator has logged into the system
    When the system administrator selects the "Generate Financial Report" option
    Then the system should display the total revenue for the selected time period
    And the report should break down revenue by product or service
    And the report should include the number of transactions in the period

  Scenario: System administrator generates a financial report with date range
    Given the system administrator has logged into the system
    When the system administrator selects a custom date range for the report
    Then the system should generate a report covering the selected date range
    And the report should show detailed transaction data within that period

package com.project.cooking.finance;

import java.util.Date;

/**
 * The Invoice class represents an invoice for a customer purchase.
 * It includes details about the invoice number, customer name, items purchased,
 * price, taxes, and the transaction date.
 * This class also calculates the total amount of the invoice.
 * 
 * @author abood
 */
public class Invoice {

    private String invoiceNumber;
    private String customerName;
    private int itemQuantity;
    private double itemPrice;
    private double taxes;
    private Date transactionDate;

    /**
     * Constructor to create a new Invoice object.
     * 
     * @param invoiceNumber The unique invoice number.
     * @param customerName The name of the customer for the invoice.
     * @param itemQuantity The quantity of items purchased.
     * @param itemPrice The price of a single item.
     * @param taxes The applicable taxes on the purchase.
     * @author abood
     */
    public Invoice(String invoiceNumber, String customerName, int itemQuantity, double itemPrice, double taxes) {
        this.invoiceNumber = invoiceNumber;
        this.customerName = customerName;
        this.itemQuantity = itemQuantity;
        this.itemPrice = itemPrice;
        this.taxes = taxes;
        this.transactionDate = new Date(); // Sets the transaction date to the current date and time
    }

    /**
     * Retrieves the invoice number.
     * 
     * @return String - the invoice number.
     * @author abood
     */
    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    /**
     * Retrieves the name of the customer.
     * 
     * @return String - the customer's name.
     * @author abood
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     * Retrieves the quantity of items purchased.
     * 
     * @return int - the number of items purchased.
     * @author abood
     */
    public int getItemQuantity() {
        return itemQuantity;
    }

    /**
     * Retrieves the price of a single item.
     * 
     * @return double - the price of a single item.
     * @author abood
     */
    public double getItemPrice() {
        return itemPrice;
    }

    /**
     * Retrieves the taxes applied to the purchase.
     * 
     * @return double - the total taxes.
     * @author abood
     */
    public double getTaxes() {
        return taxes;
    }

    /**
     * Retrieves the transaction date of the invoice.
     * 
     * @return Date - the date the invoice was generated.
     * @author abood
     */
    public Date getTransactionDate() {
        return transactionDate;
    }

    /**
     * Calculates the total amount of the invoice, including taxes.
     * 
     * @return double - the total amount (itemQuantity * itemPrice + taxes).
     * @author abood
     */
    public double getTotalAmount() {
        return (itemQuantity * itemPrice) + taxes;
    }
}
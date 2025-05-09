package com.project.cooking.finance;
import java.util.Date;

public class Invoice {

    private String invoiceNumber;
    private String customerName;
    private int itemQuantity;
    private double itemPrice;
    private double taxes;
    private Date transactionDate;
    
   

    public Invoice(String invoiceNumber, String customerName, int itemQuantity, double itemPrice, double taxes) {
        this.invoiceNumber = invoiceNumber;
        this.customerName = customerName;
        this.itemQuantity = itemQuantity;
        this.itemPrice = itemPrice;
        this.taxes = taxes;
        this.transactionDate = new Date();
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public int getItemQuantity() {
        return itemQuantity;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public double getTaxes() {
        return taxes;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    
   

    public double getTotalAmount() {
        return (itemQuantity * itemPrice) + taxes;
    }
}
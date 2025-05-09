package com.project.cooking.finance;

public class BillingService {

    public Invoice generateInvoice(String invoiceNumber, String customerName, int itemQuantity, double itemPrice, double taxes) {
        return new Invoice(invoiceNumber, customerName, itemQuantity, itemPrice, taxes);
    }
}
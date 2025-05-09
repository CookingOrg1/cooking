package com.project.cooking.finance;

/**
 * The BillingService class provides functionality for generating invoices. 
 * It is used to calculate the details of an invoice such as the invoice number, customer name, 
 * item quantity, item price, and applicable taxes.
 * 
 * @author abood
 */
public class BillingService {

    /**
     * Generates an invoice based on the provided details.
     * This method calculates and creates an Invoice object using the provided 
     * invoice number, customer name, item quantity, item price, and taxes.
     * 
     * @param invoiceNumber The unique number for the invoice.
     * @param customerName The name of the customer.
     * @param itemQuantity The quantity of items in the order.
     * @param itemPrice The price of a single item.
     * @param taxes The taxes applied to the invoice.
     * @return Invoice A newly created Invoice object with the provided details.
     * @author abood
     */
    public Invoice generateInvoice(String invoiceNumber, String customerName, int itemQuantity, double itemPrice, double taxes) {
        return new Invoice(invoiceNumber, customerName, itemQuantity, itemPrice, taxes);
    }
}
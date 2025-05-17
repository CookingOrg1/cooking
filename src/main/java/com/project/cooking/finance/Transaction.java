package com.project.cooking.finance;

/**
 * The Transaction class represents a financial transaction with a specific date and amount.
 * It includes details about the transaction date and amount.
 * 
 * @author abood
 */
public class Transaction {
    
    private String date;
    private double amount;

    /**
     * Constructor to create a new Transaction object.
     * 
     * @param date The date of the transaction.
     * @param amount The amount of money involved in the transaction.
     * @author abood
     */
    public Transaction(String date, double amount) {
        this.date = date;
        this.amount = amount;
    }

    public Transaction() {
	}

	/**
     * Retrieves the date of the transaction.
     * 
     * @return String - the date of the transaction.
     * @author abood
     */
    public String getDate() {
        return date;
    }

    /**
     * Retrieves the amount of the transaction.
     * 
     * @return double - the amount involved in the transaction.
     * @author abood
     */
    public double getAmount() {
        return amount;
    }
}
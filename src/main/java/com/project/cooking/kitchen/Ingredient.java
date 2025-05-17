package com.project.cooking.kitchen;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents an ingredient used in cooking operations.
 * Includes stock levels, expiry date, thresholds, and availability.
 * 
 * @author abood
 */
public class Ingredient {
    private String name;
    private int threshold;
    private int stockLevel;
    private boolean isAvailable = true;
    public LocalDate expiryDate;
    private int stock;
    public int reorderThreshold;
    private boolean isAllowed;
    int quantity;
    private String unit;
    private boolean restockingSuggested ;
    private boolean urgentRestockingSuggested ;

    /**
     * Constructor with name and threshold.
     * @param name Name of the ingredient.
     * @param threshold Restocking threshold level.
     * @author abood
     */
    public Ingredient(String name, int threshold) {
        this.name = name;
        this.threshold = threshold;
        this.restockingSuggested = false;
        this.urgentRestockingSuggested = false;
    }

    /**
     * Default constructor for Ingredient.
     * Initializes an empty ingredient instance.
     * @author abood
     */
    public Ingredient() {
    }

    /**
     * Constructor with name, stock, and reorder threshold.
     * @param name Ingredient name.
     * @param stock Current stock amount.
     * @param reorderThreshold Threshold to reorder stock.
     * @author abood
     */
    public Ingredient(String name, int stock, int reorderThreshold) {
        this.name = name;
        this.stock = stock;
        this.reorderThreshold = reorderThreshold;
    }

    /**
     * Constructor with name, stock, reorder threshold, and expiry date.
     * @param name Ingredient name.
     * @param stock Current stock amount.
     * @param reorderThreshold Reorder threshold value.
     * @param expiryDate Expiry date in ISO format (yyyy-MM-dd).
     * @author abood
     */
    public Ingredient(String name, int stock, int reorderThreshold, String expiryDate) {
        this.name = name;
        this.stock = stock;
        this.reorderThreshold = reorderThreshold;
        this.expiryDate = LocalDate.parse(expiryDate, DateTimeFormatter.ISO_DATE);
    }

    /**
     * Constructor with only the name.
     * @param name Name of the ingredient.
     * @author abood
     */
    public Ingredient(String name) {
        this.name = name;
    }

    /**
     * Constructor with name and permission flag.
     * @param name Name of the ingredient.
     * @param isAllowed Flag indicating if ingredient is allowed.
     * @author abood
     */
    public Ingredient(String name, boolean isAllowed) {
        this.name = name;
        this.isAllowed = isAllowed;
    }

    /**
     * Constructor with quantity, threshold, and unit.
     * @param quantity Quantity available.
     * @param threshold Restocking threshold.
     * @param unit Unit of measurement (e.g. kg, L).
     * @author abood
     */
    public Ingredient(int quantity, int threshold, String unit) {
        this.quantity = quantity;
        this.threshold = threshold;
        this.unit = unit;
    }

    /**
     * Gets the name of the ingredient.
     * @return Ingredient name.
     * @author abood
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the ingredient.
     * @param name Ingredient name.
     * @author abood
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the threshold level.
     * @return Threshold value for restocking.
     * @author abood
     */
    public int getThreshold() {
        return threshold;
    }

    /**
     * Sets the threshold level.
     * @param threshold New threshold value.
     * @author abood
     */
    public void setThreshold(int threshold) {
        this.threshold = threshold;
    }

    /**
     * Gets the current stock level.
     * @return Stock level value.
     * @author abood
     */
    public int getStockLevel() {
        return stockLevel;
    }

    /**
     * Sets the current stock level.
     * @param stockLevel New stock level.
     * @author abood
     */
    public void setStockLevel(int stockLevel) {
        this.stockLevel = stockLevel;
    }

    /**
     * Checks if restocking is suggested.
     * @return True if restocking is suggested, else false.
     * @author abood
     */
    public boolean isRestockingSuggested() {
        return restockingSuggested;
    }

    /**
     * Sets whether restocking is suggested.
     * @param restockingSuggested Suggestion flag.
     * @author abood
     */
    public void setRestockingSuggested(boolean restockingSuggested) {
        this.restockingSuggested = restockingSuggested;
    }

    /**
     * Checks if urgent restocking is needed.
     * @return True if urgent restocking is suggested, else false.
     * @author abood
     */
    public boolean isUrgentRestockingSuggested() {
        return urgentRestockingSuggested;
    }

    /**
     * Sets urgent restocking suggestion status.
     * @param urgentRestockingSuggested Urgency flag.
     * @author abood
     */
    public void setUrgentRestockingSuggested(boolean urgentRestockingSuggested) {
        this.urgentRestockingSuggested = urgentRestockingSuggested;
    }

    /**
     * Checks if the ingredient is available.
     * @return True if available, false otherwise.
     * @author abood
     */
    public boolean isAvailable() {
        return isAvailable;
    }

    /**
     * Sets the availability status.
     * @param available New availability status.
     * @author abood
     */
    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    /**
     * Gets the expiry date of the ingredient.
     * @return Expiry date as LocalDate.
     * @author abood
     */
    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    /**
     * Gets the current stock.
     * @return Stock quantity.
     * @author abood
     */
    public int getStock() {
        return stock;
    }

    /**
     * Sets the stock quantity.
     * @param stock New stock value.
     * @author abood
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /**
     * Gets the reorder threshold value.
     * @return Reorder threshold value.
     * @author abood
     */
    public int getReorderThreshold() {
        return reorderThreshold;
    }

    /**
     * Checks if the ingredient is allowed.
     * @return True if allowed, false otherwise.
     * @author abood
     */
    public boolean isAllowed() {
        return isAllowed;
    }

    /**
     * Sets whether the ingredient is allowed.
     * @param allowed New allowance status.
     * @author abood
     */
    public void setAllowed(boolean allowed) {
        isAllowed = allowed;
    }

    /**
     * Gets the quantity of the ingredient.
     * @return Quantity value.
     * @author abood
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Sets the ingredient quantity.
     * @param quantity New quantity.
     * @author abood
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Gets the unit of measurement.
     * @return Unit string (e.g., "kg", "g", "L").
     * @author abood
     */
    public String getUnit() {
        return unit;
    }

    /**
     * Sets the unit of measurement.
     * @param unit Measurement unit string.
     * @author abood
     */
    public void setUnit(String unit) {
        this.unit = unit;
    }
    
}
package com.project.cooking.kitchen;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Ingredient {
    private String name;
    private int threshold;
    private int stockLevel;
    private boolean restockingSuggested;
    private boolean urgentRestockingSuggested;
    private boolean isAvailable = true;
    private LocalDate expiryDate;
    private int stock;
    private int reorderThreshold;
    private boolean isAllowed;

    public Ingredient(String name, int threshold) {
        this.name = name;
        this.threshold = threshold;
        this.restockingSuggested = false;
        this.urgentRestockingSuggested = false;
    }
    public Ingredient(String name, int stock, int reorderThreshold) {
        this.name = name;
        this.stock = stock;
        this.reorderThreshold = reorderThreshold;
    }

    public Ingredient(String name, int stock, int reorderThreshold, String expiryDate) {
    	  this.name = name;
          this.stock = stock;
          this.reorderThreshold = reorderThreshold;
          this.expiryDate = LocalDate.parse(expiryDate, DateTimeFormatter.ISO_DATE);
    }
    public String getName() {
        return name;
    }

    public int getThreshold() {
        return threshold;
    }

    public int getStockLevel() {
        return stockLevel;
    }

    public void setStockLevel(int stockLevel) {
        this.stockLevel = stockLevel;
    }

    public boolean isRestockingSuggested() {
        return restockingSuggested;
    }

    public void setRestockingSuggested(boolean restockingSuggested) {
        this.restockingSuggested = restockingSuggested;
    }

    public boolean isUrgentRestockingSuggested() {
        return urgentRestockingSuggested;
    }

    public void setUrgentRestockingSuggested(boolean urgentRestockingSuggested) {
        this.urgentRestockingSuggested = urgentRestockingSuggested;
    }
    public Ingredient(String name) {
        this.name = name;
    }
    public void setAvailable(boolean available) {
        isAvailable = available;
   }

    public boolean isAvailable() {
       return isAvailable;
   }
    public LocalDate  getExpiryDate() {
        return expiryDate;
    }
    public int getStock() {
        return stock;
    }
    public int getReorderThreshold() {
        return reorderThreshold;
    }
    public void setStock(int stock) {
        this.stock = stock;
    }
    
    
    
    
    
    
    
    public Ingredient(String name, boolean isAllowed) {
        this.name = name;
        this.isAllowed = isAllowed;
    }

    

    public void setName(String name) {
        this.name = name;
    }

    public boolean isAllowed() {
        return isAllowed;
    }

    public void setAllowed(boolean allowed) {
        isAllowed = allowed;
    }

}
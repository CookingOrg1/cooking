package com.project.cooking.cart;


public class CustomerProfile {
    @SuppressWarnings("unused")
	private String customerId;
    private String dietaryPreferences;

    
    public CustomerProfile(String customerId) {
        this.customerId = customerId;
        this.dietaryPreferences = "Vegetarian"; 
    }

    /**
     * Retrieves the dietary preferences of the customer.
     * This method returns the dietary preferences that were set for the customer profile.
     *
     * @author abood
     * @param none
     * @return String - the dietary preferences of the customer.
     */
    public String getDietaryPreferences() {
        return dietaryPreferences;
    }
}
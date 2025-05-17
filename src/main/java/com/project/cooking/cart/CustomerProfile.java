package com.project.cooking.cart;

public class CustomerProfile {
    
    @SuppressWarnings("unused")
    private String customerId;
    private String dietaryPreferences;

    /**
     * Constructs a CustomerProfile with a given customer ID.
     * Initializes the dietary preferences to "Vegetarian" by default.
     * 
     * @param customerId The unique identifier for the customer.
     * @author abood
     */
    public CustomerProfile(String customerId) {
        this.customerId = customerId;
        this.dietaryPreferences = "Vegetarian"; 
    }

    public CustomerProfile() {
		// TODO Auto-generated constructor stub
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

	public void setDietaryPreferences(String string) {
this.dietaryPreferences=string;

	}
}
package com.customer.mealrecommendation.actors;

public class Customer {
    private String dietaryPreferences;
    private String allergies;

    /**
     * Retrieves the dietary preferences of the customer.
     * This method returns the dietary preferences set for the customer, 
     * which can be used to customize meals accordingly.
     * 
     * @author abood
     * @param none
     * @return String - the dietary preferences of the customer.
     */
    public String getDietaryPreferences() {
        return dietaryPreferences;
    }

    /**
     * Sets the dietary preferences of the customer.
     * This method allows setting the dietary preferences for the customer, 
     * which helps in meal customization based on their needs.
     * 
     * @author abood
     * @param dietaryPreferences - the dietary preferences to be set.
     * @return void
     */
    public void setDietaryPreferences(String dietaryPreferences) {
        this.dietaryPreferences = dietaryPreferences;
    }

    /**
     * Retrieves the allergies of the customer.
     * This method returns any allergies recorded for the customer, 
     * which helps in avoiding ingredients that may cause allergic reactions.
     * 
     * @author abood
     * @param none
     * @return String - the allergies of the customer.
     */
    public String getAllergies() {
        return allergies;
    }

    /**
     * Sets the allergies of the customer.
     * This method allows setting the allergy details of the customer, 
     * ensuring that meals can be prepared safely without allergic ingredients.
     * 
     * @author abood
     * @param allergies - the allergies to be set.
     * @return void
     */
    public void setAllergies(String allergies) {
        this.allergies = allergies;
    }
}
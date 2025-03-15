package com.customer.mealrecommendation.actors;

import com.customer.mealrecommendation.CustomerProfile;

public class Chef {
    
    private String name;

   
    public Chef(String name1) {
        name = name1;
    }

    /**
     * Customizes a meal based on the customer's dietary preferences.
     * This method takes a customer profile, retrieves their dietary preferences, 
     * and creates a meal accordingly.
     *
     * @author abood
     * @param customerProfile - the profile containing the customer's dietary preferences.
     * @return Meal - a meal customized based on the customer's dietary preferences.
     */
    public Meal customizeMeal(CustomerProfile customerProfile) {
        String dietaryPreferences = customerProfile.getDietaryPreferences();
        return new Meal(dietaryPreferences + " Meal");
    }
}

package com.customer.mealrecommendation.actors;


public class Meal {
    private String mealName;

    
    public Meal(String mealName) {
        this.mealName = mealName;
    }

    /**
     * Retrieves the name of the meal.
     * This method returns the name of the meal that was set during the meal's creation.
     * 
     * @author abood
     * @param none
     * @return String - the name of the meal.
     */
    public String getMealName() {
        return mealName;
    }
}

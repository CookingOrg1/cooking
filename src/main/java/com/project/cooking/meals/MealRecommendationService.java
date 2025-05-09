package com.project.cooking.meals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.project.cooking.actors.Customer;

public class MealRecommendationService {

    /**
     * Recommends meals based on the customer's dietary preferences and allergies.
     * This method checks the customer's dietary preferences and allergies, then 
     * suggests suitable meal options that meet their needs.
     * 
     * @author omar
     * @param customer - the customer whose dietary preferences and allergies 
     *                  will be used to recommend meals.
     * @return List<String> - a list of meal recommendations based on the 
     *                         customer's dietary preferences and allergies.
     */
    public List<String> recommendMeals(Customer customer) {
        List<String> recommendedMeals = new ArrayList<>();

        if (customer.getDietaryPreferences() != null && customer.getAllergies() != null) {
            String preferences = customer.getDietaryPreferences().toLowerCase().trim();
            String[] allergyList = customer.getAllergies().toLowerCase().split(",");

            for (int i = 0; i < allergyList.length; i++) {
                allergyList[i] = allergyList[i].trim();
            }

            if (isVegetarian(preferences) && isGlutenFree(allergyList) && isPeanutFree(allergyList)) {
                recommendedMeals.add("Vegetarian Gluten-Free Peanut-Free Meal");
            }
            if (isVegan(preferences) && isDairyFree(allergyList)) {
                recommendedMeals.add("Vegan Dairy-Free Meal");
            }
            if (isKeto(preferences) && isNutFree(allergyList)) {
                recommendedMeals.add("Keto Nut-Free Meal");
            }
            if (isPaleo(preferences) && isShellfishFree(allergyList)) {
                recommendedMeals.add("Paleo Shellfish-Free Meal");
            }
        }

        if (recommendedMeals.isEmpty()) {
            return new ArrayList<>();
        }

        return recommendedMeals;
    }

    /**
     * Checks if the customer has a vegetarian dietary preference.
     * This method checks whether the dietary preference of the customer 
     * contains "vegetarian".
     * 
     * @author oamr
     * @param preferences - the dietary preferences of the customer.
     * @return boolean - true if the customer is vegetarian, false otherwise.
     */
    private boolean isVegetarian(String preferences) {
        return preferences.contains("vegetarian");
    }

    /**
     * Checks if the customer has a vegan dietary preference.
     * This method checks whether the dietary preference of the customer 
     * contains "vegan".
     * 
     * @author omar
     * @param preferences - the dietary preferences of the customer.
     * @return boolean - true if the customer is vegan, false otherwise.
     */
    private boolean isVegan(String preferences) {
        return preferences.contains("vegan");
    }

    /**
     * Checks if the customer has a keto dietary preference.
     * This method checks whether the dietary preference of the customer 
     * contains "keto".
     * 
     * @author oamr
     * @param preferences - the dietary preferences of the customer.
     * @return boolean - true if the customer is keto, false otherwise.
     */
    private boolean isKeto(String preferences) {
        return preferences.contains("keto");
    }

    /**
     * Checks if the customer has a paleo dietary preference.
     * This method checks whether the dietary preference of the customer 
     * contains "paleo".
     * 
     * @author omar
     * @param preferences - the dietary preferences of the customer.
     * @return boolean - true if the customer is paleo, false otherwise.
     */
    private boolean isPaleo(String preferences) {
        return preferences.contains("paleo");
    }

    /**
     * Checks if the customer is gluten-free.
     * This method checks whether the allergies list of the customer contains "gluten-free".
     * 
     * @author oamr
     * @param allergyList - the list of allergies of the customer.
     * @return boolean - true if the customer is gluten-free, false otherwise.
     */
    private boolean isGlutenFree(String[] allergyList) {
        return Arrays.asList(allergyList).contains("gluten-free");
    }

    /**
     * Checks if the customer is peanut-free.
     * This method checks whether the allergies list of the customer contains "peanut-free".
     * 
     * @author omar
     * @param allergyList - the list of allergies of the customer.
     * @return boolean - true if the customer is peanut-free, false otherwise.
     */
    private boolean isPeanutFree(String[] allergyList) {
        return Arrays.asList(allergyList).contains("peanut-free");
    }

    /**
     * Checks if the customer is dairy-free.
     * This method checks whether the allergies list of the customer contains "dairy-free".
     * 
     * @author omar
     * @param allergyList - the list of allergies of the customer.
     * @return boolean - true if the customer is dairy-free, false otherwise.
     */
    private boolean isDairyFree(String[] allergyList) {
        return Arrays.asList(allergyList).contains("dairy-free");
    }

    /**
     * Checks if the customer is nut-free.
     * This method checks whether the allergies list of the customer contains "nut-free".
     * 
     * @author omar
     * @param allergyList - the list of allergies of the customer.
     * @return boolean - true if the customer is nut-free, false otherwise.
     */
    private boolean isNutFree(String[] allergyList) {
        return Arrays.asList(allergyList).contains("nut-free");
    }

    /**
     * Checks if the customer is shellfish-free.
     * This method checks whether the allergies list of the customer contains "shellfish-free".
     * 
     * @author oamr
     * @param allergyList - the list of allergies of the customer.
     * @return boolean - true if the customer is shellfish-free, false otherwise.
     */
    private boolean isShellfishFree(String[] allergyList) {
        return Arrays.asList(allergyList).contains("shellfish-free");
    }
}
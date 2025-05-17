package com.project.cooking.meals;

import java.util.ArrayList;
import java.util.List;

import com.project.cooking.actors.Customer;

public class MealRecommendationService {

    /**
     * Recommends meals based on the customer's dietary preferences and allergies.
     *
     * @param customer - the customer whose dietary preferences and allergies
     *                 will be used to recommend meals.
     * @return List<String> - a list of meal recommendations.
     */
    public List<String> recommendMeals(Customer customer) {
        if (customer.getDietaryPreferences() == null || customer.getAllergies() == null) {
            return new ArrayList<>();
        }

        String preferences = customer.getDietaryPreferences().toLowerCase().trim();
        List<String> allergyList = normalizeAllergies(customer.getAllergies());

        List<String> recommendedMeals = new ArrayList<>();

        checkAndAdd(preferences, allergyList, recommendedMeals,
                "vegetarian", new String[]{"gluten-free", "peanut-free"},
                "Vegetarian Gluten-Free Peanut-Free Meal");

        checkAndAdd(preferences, allergyList, recommendedMeals,
                "vegan", new String[]{"dairy-free"},
                "Vegan Dairy-Free Meal");

        checkAndAdd(preferences, allergyList, recommendedMeals,
                "keto", new String[]{"nut-free"},
                "Keto Nut-Free Meal");

        checkAndAdd(preferences, allergyList, recommendedMeals,
                "paleo", new String[]{"shellfish-free"},
                "Paleo Shellfish-Free Meal");
        return recommendedMeals;
    }

    private void checkAndAdd(String preferences, List<String> allergies, List<String> meals,
                             String dietKeyword, String[] requiredAllergies, String mealName) {
        if (hasDietPreference(preferences, dietKeyword) && hasAllergies(allergies, requiredAllergies)) {
            meals.add(mealName);
        }
    }

    private boolean hasDietPreference(String preferences, String keyword) {
        return preferences.contains(keyword);
    }

    private boolean hasAllergies(List<String> allergies, String[] requiredAllergies) {
        for (String allergy : requiredAllergies) {
            if (!allergies.contains(allergy)) {
                return false;
            }
        }
        return true;
    }

    private List<String> normalizeAllergies(String allergiesRaw) {
        String[] allergyArray = allergiesRaw.toLowerCase().split(",");
        List<String> allergyList = new ArrayList<>();
        for (String allergy : allergyArray) {
            allergyList.add(allergy.trim());
        }
        return allergyList;
    }
}
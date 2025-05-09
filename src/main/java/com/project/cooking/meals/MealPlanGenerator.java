package com.project.cooking.meals;

import java.util.List;
import com.project.cooking.cart.CustomerProfile;
import com.project.cooking.orders.OrderHistory;
import java.util.ArrayList;

/**
 * This class generates personalized and general meal plans based on customer profiles and order history.
 * Author: Omar
 */
public class MealPlanGenerator {

    /**
     * Generates a personalized meal plan based on the customer's order history and profile.
     * This method checks if the customer has ordered vegan meals before and includes a premium vegan plate
     * in the meal plan if applicable. It also adds a seasonal vegetarian special if the customer's dietary
     * preferences indicate vegetarianism.
     *
     * @param history the order history of the customer (OrderHistory)
     * @param profile the profile of the customer (CustomerProfile)
     * @return a list of personalized meal suggestions (List<String>)
     * @author Omar
     */
    public static List<String> generatePersonalizedPlan(OrderHistory history, CustomerProfile profile) {
        List<String> meals = new ArrayList<>();
        if (!history.isEmpty()) {
            history.getOrders().forEach(order -> {
                if (order.getMealName().contains("Vegan")) {
                    meals.add("Premium Vegan Plate");
                }
            });
        }
        if (profile.getDietaryPreferences().equals("Vegetarian")) {
            meals.add("Seasonal Vegetarian Special");
        }
        return meals;
    }

    /**
     * Generates a general meal plan based on the provided dietary preference.
     * This method suggests meals based on the dietary preference provided, specifically for vegetarians.
     *
     * @param dietaryPreference the dietary preference of the customer (String)
     * @return a list of meal suggestions based on the dietary preference (List<String>)
     * @author Omar
     */
    public static List<String> generateGeneralPlan(String dietaryPreference) {
        List<String> meals = new ArrayList<>();
        if (dietaryPreference.equals("Vegetarian")) {
            meals.add("Vegetarian Chef's Choice");
            meals.add("Garden Fresh Salad Bowl");
        }
        return meals;
    }
}
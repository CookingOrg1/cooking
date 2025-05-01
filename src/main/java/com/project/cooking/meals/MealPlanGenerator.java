package com.project.cooking.meals;
import java.util.List;

import com.project.cooking.cart.CustomerProfile;
import com.project.cooking.orders.OrderHistory;

import java.util.ArrayList;

   public class MealPlanGenerator {
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

    public static List<String> generateGeneralPlan(String dietaryPreference) {
        List<String> meals = new ArrayList<>();
        if (dietaryPreference.equals("Vegetarian")) {
            meals.add("Vegetarian Chef's Choice");
            meals.add("Garden Fresh Salad Bowl");
        }
        return meals;
    }
}
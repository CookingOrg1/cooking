package com.project.cooking;

import com.project.cooking.cart.CustomerProfile;
import com.project.cooking.meals.MealPlanGenerator;
import com.project.cooking.orders.Order;
import com.project.cooking.orders.OrderHistory;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class MealPlanGeneratorTest {

    private CustomerProfile vegetarianProfile;
    private CustomerProfile regularProfile;

    private Order veganOrder;
    private Order nonVeganOrder;

    private OrderHistory historyWithVegan;
    private OrderHistory emptyHistory;

    @Before
    public void setUp() {
        vegetarianProfile = new CustomerProfile();
        vegetarianProfile.setDietaryPreferences("Vegetarian");

        regularProfile = new CustomerProfile();
        regularProfile.setDietaryPreferences("None");

        veganOrder = new Order();
        veganOrder.setMealName("Vegan Delight");

        nonVeganOrder = new Order();
        nonVeganOrder.setMealName("Chicken Sandwich");

        historyWithVegan = new OrderHistory();
        historyWithVegan.setOrders(Arrays.asList(veganOrder, nonVeganOrder));

        emptyHistory = new OrderHistory();
        emptyHistory.setOrders(Collections.emptyList());
    }

    @Test
    public void testGeneratePersonalizedPlan_WithVeganHistoryAndVegetarianProfile() {
        List<String> result = MealPlanGenerator.generatePersonalizedPlan(historyWithVegan, vegetarianProfile);

        assertTrue(result.contains("Premium Vegan Plate"));
        assertTrue(result.contains("Seasonal Vegetarian Special"));
        assertEquals(2, result.size());
    }

    @Test
    public void testGeneratePersonalizedPlan_WithEmptyHistoryAndVegetarianProfile() {
        List<String> result = MealPlanGenerator.generatePersonalizedPlan(emptyHistory, vegetarianProfile);

        assertFalse(result.contains("Premium Vegan Plate"));
        assertTrue(result.contains("Seasonal Vegetarian Special"));
        assertEquals(1, result.size());
    }

    @Test
    public void testGeneratePersonalizedPlan_WithVeganHistoryAndRegularProfile() {
        List<String> result = MealPlanGenerator.generatePersonalizedPlan(historyWithVegan, regularProfile);

        assertTrue(result.contains("Premium Vegan Plate"));
        assertFalse(result.contains("Seasonal Vegetarian Special"));
        assertEquals(1, result.size());
    }

    @Test
    public void testGenerateGeneralPlan_WithVegetarianPreference() {
        List<String> result = MealPlanGenerator.generateGeneralPlan("Vegetarian");

        assertTrue(result.contains("Vegetarian Chef's Choice"));
        assertTrue(result.contains("Garden Fresh Salad Bowl"));
        assertEquals(2, result.size());
    }

    @Test
    public void testGenerateGeneralPlan_WithNonVegetarianPreference() {
        List<String> result = MealPlanGenerator.generateGeneralPlan("None");

        assertTrue(result.isEmpty());
    }
}
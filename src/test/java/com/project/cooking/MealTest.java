package com.project.cooking;
import com.project.cooking.meals.Meal;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.project.cooking.kitchen.Ingredient;

public class MealTest {

    private Meal meal;

    @Before
    public void setUp() {
        meal = new Meal("Pasta");
    }

    @Test
    public void testConstructorWithName() {
        assertEquals("Pasta", meal.getMealName());
        assertEquals("Pasta", meal.getName());
    }

    @Test
    public void testConstructorWithNamePriceIngredients() {
        List<String> ingredNames = Arrays.asList("Tomato", "Basil");
        Meal m = new Meal("Salad", 10.5, ingredNames);
        assertEquals("Salad", m.getMealName());
        assertEquals(10.5, m.getPrice(), 0.0001);
        assertEquals(ingredNames, m.getIngredientss());
        assertEquals(ingredNames, m.getingredienT());
    }

    @Test
    public void testSetName() {
        meal.setName("Pizza");
        assertEquals("Pizza", meal.getName());
    }

    @Test
    public void testSetPriceAndGetPrice() {
        meal.setPrice(15.99);
        assertEquals(15.99, meal.getPrice(), 0.0001);
    }

    @Test
    public void testAddAndRemoveIngredient() {
        Ingredient i1 = new Ingredient("Cheese");
        Ingredient i2 = new Ingredient("Tomato");
        meal.addIngredient(i1);
        meal.addIngredient(i2);

        List<Ingredient> ingredients = meal.getIngredients();
        assertTrue(ingredients.contains(i1));
        assertTrue(ingredients.contains(i2));

        meal.removeIngredient(new Ingredient("Cheese"));  
        ingredients = meal.getIngredients();
        assertFalse(ingredients.stream().anyMatch(i -> i.getName().equals("Cheese")));
        assertTrue(ingredients.stream().anyMatch(i -> i.getName().equals("Tomato")));
    }

    @Test
    public void testHasIngredient() {
        Ingredient i1 = new Ingredient("Pepper");
        meal.addIngredient(i1);

        assertTrue(meal.hasIngredient("Pepper"));
        assertFalse(meal.hasIngredient("Salt"));
    }

    @Test
    public void testGetIngredientsReturnsCopy() {
        Ingredient i1 = new Ingredient("Olive Oil");
        meal.addIngredient(i1);

        List<Ingredient> ingList = meal.getIngredients();
        int originalSize = ingList.size();

        ingList.clear();
        List<Ingredient> ingListAfter = meal.getIngredients();
        assertEquals(originalSize, ingListAfter.size());
    }

    @Test
    public void testSetAndGetIngredientNames() {
        List<String> names = Arrays.asList("Onion", "Garlic");
        meal.setIngredients(names);
        assertEquals(names, meal.getIngredientss());
        assertEquals(names, meal.getingredienT());
    }

    @Test
    public void testAddIngredientNullListInitialization() {
      
        try {
            java.lang.reflect.Field field = Meal.class.getDeclaredField("ingredients");
            field.setAccessible(true);
            field.set(meal, null);
        } catch (Exception e) {
            fail("Reflection failed");
        }

        Ingredient i = new Ingredient("Mushroom");
        meal.addIngredient(i);

        List<Ingredient> ingredients = meal.getIngredients();
        assertTrue(ingredients.stream().anyMatch(ing -> ing.getName().equals("Mushroom")));
    }
}

package com.project.cooking;
import  com.project.cooking.kitchen.Ingredient;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class IngredientTest {

    private Ingredient ingredient;

    @Before
    public void setUp() {
        ingredient = new Ingredient();
    }

    @After
    public void tearDown() {
        ingredient = null;
    }

    @Test
    public void testConstructorWithNameAndThreshold() {
        Ingredient ingr = new Ingredient("Salt", 10);
        assertEquals("Salt", ingr.getName());
        assertEquals(10, ingr.getThreshold());
        assertFalse(ingr.isRestockingSuggested());
        assertFalse(ingr.isUrgentRestockingSuggested());
    }

    @Test
    public void testDefaultConstructor() {
        assertNull(ingredient.getName());
        assertEquals(0, ingredient.getThreshold());
        assertFalse(ingredient.isRestockingSuggested());
        assertFalse(ingredient.isUrgentRestockingSuggested());
        assertTrue(ingredient.isAvailable());
        assertNull(ingredient.getUnit());
    }

    @Test
    public void testConstructorWithNameStockAndReorderThreshold() {
        Ingredient ingr = new Ingredient("Sugar", 50, 20);
        assertEquals("Sugar", ingr.getName());
        assertEquals(50, ingr.getStock());
        assertEquals(20, ingr.getReorderThreshold());
    }

    @Test
    public void testConstructorWithNameStockReorderThresholdAndExpiryDate() {
        String expiryStr = "2025-12-31";
        Ingredient ingr = new Ingredient("Flour", 100, 50, expiryStr);
        assertEquals("Flour", ingr.getName());
        assertEquals(100, ingr.getStock());
        assertEquals(50, ingr.getReorderThreshold());
        assertEquals(LocalDate.parse(expiryStr), ingr.getExpiryDate());
    }

    @Test
    public void testConstructorWithNameOnly() {
        Ingredient ingr = new Ingredient("Pepper");
        assertEquals("Pepper", ingr.getName());
    }

    @Test
    public void testConstructorWithNameAndIsAllowed() {
        Ingredient ingr1 = new Ingredient("Oil", true);
        assertEquals("Oil", ingr1.getName());
        assertTrue(ingr1.isAllowed());

        Ingredient ingr2 = new Ingredient("Butter", false);
        assertEquals("Butter", ingr2.getName());
        assertFalse(ingr2.isAllowed());
    }

    @Test
    public void testConstructorWithQuantityThresholdAndUnit() {
        Ingredient ingr = new Ingredient(5, 10, "kg");
        assertEquals(5, ingr.getQuantity());
        assertEquals(10, ingr.getThreshold());
        assertEquals("kg", ingr.getUnit());
    }

    @Test
    public void testGettersAndSetters() {
        ingredient.setName("Salt");
        assertEquals("Salt", ingredient.getName());

        ingredient.setThreshold(15);
        assertEquals(15, ingredient.getThreshold());

        ingredient.setStockLevel(30);
        assertEquals(30, ingredient.getStockLevel());

        ingredient.setRestockingSuggested(true);
        assertTrue(ingredient.isRestockingSuggested());

        ingredient.setUrgentRestockingSuggested(true);
        assertTrue(ingredient.isUrgentRestockingSuggested());

        ingredient.setAvailable(false);
        assertFalse(ingredient.isAvailable());

        LocalDate expiry = LocalDate.of(2024, 5, 17);
        ingredient.expiryDate = expiry; 
        assertEquals(expiry, ingredient.getExpiryDate());

        ingredient.setStock(40);
        assertEquals(40, ingredient.getStock());

        ingredient.reorderThreshold = 10;
        assertEquals(10, ingredient.getReorderThreshold());

        ingredient.setAllowed(true);
        assertTrue(ingredient.isAllowed());

        ingredient.setQuantity(12);
        assertEquals(12, ingredient.getQuantity());

        ingredient.setUnit("L");
        assertEquals("L", ingredient.getUnit());
        
    }
}
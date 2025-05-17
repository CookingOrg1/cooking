package com.project.cooking;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import com.project.cooking.actors.Chef;
import com.project.cooking.kitchen.Ingredient;
import com.project.cooking.kitchen.Kitchen;

import java.util.List;

public class KitchenTest {

    private Kitchen kitchen;
    private Ingredient lowStockIngredient;
    private Ingredient outOfStockIngredient;
    private Ingredient sufficientStockIngredient;

    @Before
    public void setUp() {
        kitchen = new Kitchen();

        lowStockIngredient = new Ingredient();
        lowStockIngredient.setName("Tomato");
        lowStockIngredient.setStockLevel(3);
        lowStockIngredient.setThreshold(5);

        outOfStockIngredient = new Ingredient();
        outOfStockIngredient.setName("Onion");
        outOfStockIngredient.setStockLevel(0);
        outOfStockIngredient.setThreshold(5);

        sufficientStockIngredient = new Ingredient();
        sufficientStockIngredient.setName("Salt");
        sufficientStockIngredient.setStockLevel(10);
        sufficientStockIngredient.setThreshold(5);

        Kitchen.clearChefs();
    }

    @After
    public void tearDown() {
        Kitchen.clearChefs();
    }

    @Test
    public void testAddChefAndGetAllChefs() {
        Chef chef1 = new Chef("Gordon Ramsay");
        Chef chef2 = new Chef("Jamie Oliver");

        Kitchen.addChef(chef1);
        Kitchen.addChef(chef2);

        List<Chef> chefs = Kitchen.getAllChefs();
        assertEquals(2, chefs.size());
        assertTrue(chefs.contains(chef1));
        assertTrue(chefs.contains(chef2));
    }

    @Test
    public void testClearChefs() {
        Kitchen.addChef(new Chef("Gordon Ramsay"));
        Kitchen.clearChefs();
        assertTrue(Kitchen.getAllChefs().isEmpty());
    }

    @Test
    public void testCheckAndNotify_LowStock() {
        kitchen.checkAndNotify(lowStockIngredient);

        assertTrue(kitchen.isNotified());
        assertFalse(kitchen.isNotifiedImmediately());
        assertTrue(lowStockIngredient.isRestockingSuggested());
        assertFalse(lowStockIngredient.isUrgentRestockingSuggested());
    }

    @Test
    public void testCheckAndNotify_OutOfStock() {
        kitchen.checkAndNotify(outOfStockIngredient);

        assertFalse(kitchen.isNotified());
        assertTrue(kitchen.isNotifiedImmediately());
        assertFalse(outOfStockIngredient.isRestockingSuggested());
        assertTrue(outOfStockIngredient.isUrgentRestockingSuggested());
    }

    @Test
    public void testCheckAndNotify_SufficientStock() {
        kitchen.checkAndNotify(sufficientStockIngredient);

        assertFalse(kitchen.isNotified());
        assertFalse(kitchen.isNotifiedImmediately());
        assertFalse(sufficientStockIngredient.isRestockingSuggested());
        assertFalse(sufficientStockIngredient.isUrgentRestockingSuggested());
    }

    @Test
    public void testReceivedNotificationFor_ReturnsGeneralNotifiedFlag() {
        kitchen.checkAndNotify(lowStockIngredient);
        assertTrue(kitchen.receivedNotificationFor("Tomato"));

        kitchen.checkAndNotify(sufficientStockIngredient);
        assertFalse(kitchen.receivedNotificationFor("Salt"));
    }
}
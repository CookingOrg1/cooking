package com.project.cooking;
import com.project.cooking.actors.Chef;
import com.project.cooking.kitchen.Ingredient;
import com.project.cooking.kitchen.Kitchen;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class KitchenTest {

    private Kitchen kitchen;

    @Before
    public void setUp() {
        kitchen = new Kitchen();
        Kitchen.clearChefs();
    }

    @Test
    public void testAddChefAndGetAllChefs() {
        Chef chef1 = new Chef("John");
        Chef chef2 = new Chef("Alice");

        Kitchen.addChef(chef1);
        Kitchen.addChef(chef2);

        List<Chef> chefs = Kitchen.getAllChefs();
        assertEquals(2, chefs.size());
        assertTrue(chefs.contains(chef1));
        assertTrue(chefs.contains(chef2));
    }

    @Test
    public void testClearChefs() {
        Chef chef = new Chef("John");
        Kitchen.addChef(chef);
        assertFalse(Kitchen.getAllChefs().isEmpty());

        Kitchen.clearChefs();
        assertTrue(Kitchen.getAllChefs().isEmpty());
    }

    @Test
    public void testCheckAndNotify_GeneralRestocking() {
        Ingredient ingredient = new Ingredient("Tomato", 5, 10);
        kitchen.checkAndNotify(ingredient);

        assertTrue(ingredient.isRestockingSuggested());
        assertFalse(ingredient.isUrgentRestockingSuggested());
        assertTrue(kitchen.isNotified());
        assertFalse(kitchen.isNotifiedImmediately());
    }

    @Test
    public void testCheckAndNotify_UrgentRestocking() {
        Ingredient ingredient = new Ingredient("Onion", 0, 10); 
        kitchen.checkAndNotify(ingredient);

        assertFalse(ingredient.isRestockingSuggested());
        assertTrue(ingredient.isUrgentRestockingSuggested());
        assertFalse(kitchen.isNotified());
        assertTrue(kitchen.isNotifiedImmediately());
    }

    @Test
    public void testCheckAndNotify_NoNotification() {
        Ingredient ingredient = new Ingredient("Pepper", 15, 10); 
        kitchen.checkAndNotify(ingredient);

        assertFalse(ingredient.isRestockingSuggested());
        assertFalse(ingredient.isUrgentRestockingSuggested());
        assertFalse(kitchen.isNotified());
        assertFalse(kitchen.isNotifiedImmediately());
    }

    @Test
    public void testReceivedNotificationFor() {
        Ingredient ingredient = new Ingredient("Salt", 5, 10);
        kitchen.checkAndNotify(ingredient);

        assertTrue(kitchen.receivedNotificationFor("Salt"));
        assertTrue(kitchen.receivedNotificationFor("Sugar"));
    }

    @Test
    public void testReceivedNotificationFor_NoNotification() {
        assertFalse(kitchen.receivedNotificationFor("Salt"));
    }
}
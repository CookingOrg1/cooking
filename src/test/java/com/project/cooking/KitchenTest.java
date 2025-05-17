package com.project.cooking;

import com.project.cooking.kitchen.Ingredient;
import com.project.cooking.kitchen.Kitchen;
import com.project.cooking.actors.Chef;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class KitchenTest {

    private Kitchen kitchen;

    @Before
    public void setup() {
        kitchen = new Kitchen();
        Kitchen.clearChefs();
    }

    @Test
    public void testAddChef() {
        Chef chef = new Chef("Gordon");
        Kitchen.addChef(chef);
        List<Chef> chefList = Kitchen.getAllChefs();
        assertEquals(1, chefList.size());
        assertTrue(chefList.contains(chef));
    }

    @Test
    public void testGetAllChefsReturnsCopy() {
        Chef chef = new Chef("Jamie");
        Kitchen.addChef(chef);
        List<Chef> chefs1 = Kitchen.getAllChefs();
        List<Chef> chefs2 = Kitchen.getAllChefs();

        chefs1.clear();
        assertEquals(1, chefs2.size());
    }

    @Test
    public void testClearChefs() {
        Kitchen.addChef(new Chef("Alice"));
        Kitchen.clearChefs();
        assertTrue(Kitchen.getAllChefs().isEmpty());
    }

    @Test
    public void testCheckAndNotifyUrgentRestocking() {
        Ingredient ing = new Ingredient("Onion", 0, 5);  
        kitchen.checkAndNotify(ing);

        assertTrue(ing.isUrgentRestockingSuggested());
        assertFalse(ing.isRestockingSuggested());
        assertTrue(kitchen.isNotifiedImmediately());
        assertFalse(kitchen.isNotified());
    }

    @Test
    public void testCheckAndNotifyGeneralRestocking() {
        Ingredient ing = new Ingredient("Tomato", 3, 5);  
        kitchen.checkAndNotify(ing);

        assertTrue(ing.isRestockingSuggested());
        assertFalse(ing.isUrgentRestockingSuggested());
        assertTrue(kitchen.isNotified());
        assertFalse(kitchen.isNotifiedImmediately());
    }

    @Test
    public void testCheckAndNotifyNoRestocking() {
        Ingredient ing = new Ingredient("Pepper", 10, 5);  
        kitchen.checkAndNotify(ing);

        assertFalse(ing.isRestockingSuggested());
        assertFalse(ing.isUrgentRestockingSuggested());
        assertFalse(kitchen.isNotified());
        assertFalse(kitchen.isNotifiedImmediately());
    }

    @Test
    public void testReceivedNotificationForReflectsGeneralFlag() {
        Ingredient ing = new Ingredient("Salt", 1, 5);  
        kitchen.checkAndNotify(ing);
        assertTrue(kitchen.receivedNotificationFor("Salt"));  
    }

    @Test
    public void testReceivedNotificationForWhenNothingTriggered() {
        Ingredient ing = new Ingredient("Sugar", 10, 5); 
        kitchen.checkAndNotify(ing);
        assertFalse(kitchen.receivedNotificationFor("Sugar"));
    }
}

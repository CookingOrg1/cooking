package com.project.cooking;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.project.cooking.actors.Chef;
import com.project.cooking.actors.KitchenManager;

public class KitchenManagerTest2 {

    private KitchenManager manager;

    @Before
    public void setUp() {
        manager = new KitchenManager();
    }

    @Test
    public void testDefaultConstructorAndSettersGetters() {
        manager.setUsername("chefmaster");
        assertEquals("chefmaster", manager.getUsername());

        manager.setPassword("secret123");
        assertEquals("secret123", manager.getPassword());

        manager.setFirstName("John");
        assertEquals("John", manager.getFirstName());

        manager.setLastName("Doe");
        assertEquals("Doe", manager.getLastName());

        manager.setEmail("john.doe@example.com");
        assertEquals("john.doe@example.com", manager.getEmail());

        manager.setPhone("1234567890");
        assertEquals("1234567890", manager.getPhone());

        List<Chef> chefs = new ArrayList<>();
        manager.setManagedChefs(chefs);
        assertEquals(chefs, manager.getManagedChefs());
    }

    @Test
    public void testConstructorWithParameters() {
        List<Chef> chefs = Arrays.asList(new Chef(), new Chef());
        KitchenManager km = new KitchenManager("admin", "pass", "Alice", "Smith",
                "alice@example.com", "0987654321", chefs);

        assertEquals("admin", km.getUsername());
        assertEquals("pass", km.getPassword());
        assertEquals("Alice", km.getFirstName());
        assertEquals("Smith", km.getLastName());
        assertEquals("alice@example.com", km.getEmail());
        assertEquals("0987654321", km.getPhone());
        assertEquals(chefs, km.getManagedChefs());
    }

    @Test
    public void testAddAndGetAlerts() {
        assertTrue(manager.getAlerts().isEmpty());

        manager.addAlert("Alert 1");
        manager.addAlert("Alert 2");

        List<String> alerts = manager.getAlerts();
        assertEquals(2, alerts.size());
        assertTrue(alerts.contains("Alert 1"));
        assertTrue(alerts.contains("Alert 2"));
    }

    @Test
    public void testToStringIncludesManagedChefsCount() {
        KitchenManager km = new KitchenManager();
        assertTrue(km.toString().contains("managedChefsCount=0"));

        List<Chef> chefs = Arrays.asList(new Chef(), new Chef(), new Chef());
        km.setManagedChefs(chefs);

        String str = km.toString();
        assertTrue(str.contains("managedChefsCount=3"));
    }
}
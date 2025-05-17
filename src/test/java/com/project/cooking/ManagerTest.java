package com.project.cooking;
import  com.project.cooking.actors.KitchenManager;
import  com.project.cooking.actors.Chef;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

public class ManagerTest {

    private KitchenManager manager;
    private List<Chef> chefs;

    @Before
    public void setUp() {
        chefs = new ArrayList<>();
        chefs.add(new Chef("chef1"));  
        chefs.add(new Chef("chef2"));
        manager = new KitchenManager("user123", "pass123", "John", "Doe", 
                                     "john@example.com", "1234567890", chefs);
    }

    @Test
    public void testConstructorAndGetters() {
        assertEquals("user123", manager.getUsername());
        assertEquals("pass123", manager.getPassword());
        assertEquals("John", manager.getFirstName());
        assertEquals("Doe", manager.getLastName());
        assertEquals("john@example.com", manager.getEmail());
        assertEquals("1234567890", manager.getPhone());
        assertEquals(2, manager.getManagedChefs().size());
    }

    @Test
    public void testSetters() {
        manager.setUsername("newUser");
        manager.setPassword("newPass");
        manager.setFirstName("Jane");
        manager.setLastName("Smith");
        manager.setEmail("jane@example.com");
        manager.setPhone("0987654321");

        List<Chef> newChefs = new ArrayList<>();
        newChefs.add(new Chef("chef3"));
        manager.setManagedChefs(newChefs);

        assertEquals("newUser", manager.getUsername());
        assertEquals("newPass", manager.getPassword());
        assertEquals("Jane", manager.getFirstName());
        assertEquals("Smith", manager.getLastName());
        assertEquals("jane@example.com", manager.getEmail());
        assertEquals("0987654321", manager.getPhone());
        assertEquals(1, manager.getManagedChefs().size());
        assertEquals("chef3", manager.getManagedChefs().get(0).getName());
    }

    @Test
    public void testAlerts() {
        assertTrue(manager.getAlerts().isEmpty());

        manager.addAlert("Alert 1");
        manager.addAlert("Alert 2");

        List<String> alerts = manager.getAlerts();
        assertEquals(2, alerts.size());
        assertEquals("Alert 1", alerts.get(0));
        assertEquals("Alert 2", alerts.get(1));
    }

    @Test
    public void testToString() {
        String str = manager.toString();
        assertTrue(str.contains("user123"));
        assertTrue(str.contains("John"));
        assertTrue(str.contains("Doe"));
        assertTrue(str.contains("john@example.com"));
        assertTrue(str.contains("1234567890"));
        assertTrue(str.contains("managedChefsCount=2"));
    }

    @Test
    public void testDefaultConstructor() {
        KitchenManager km = new KitchenManager();
        assertNull(km.getUsername());
        assertNull(km.getPassword());
        assertNull(km.getFirstName());
        assertNull(km.getLastName());
        assertNull(km.getEmail());
        assertNull(km.getPhone());
        assertNull(km.getManagedChefs());
        assertTrue(km.getAlerts().isEmpty());
    }
}
package com.project.cooking;
import com.project.cooking.actors.Customer;
import com.project.cooking.meals.MealDelivery;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MealDeliveryTest {

    private Customer customer;
    private MealDelivery delivery;

    @Before
    public void setUp() {
        customer = new Customer("John Doe", "john@example.com", "123 Main St"); 
        delivery = new MealDelivery(customer, "18:00");
    }

    @Test
    public void testGetCustomer() {
        assertEquals(customer, delivery.getCustomer());
    }

    @Test
    public void testInitialDeliveryTime() {
        assertEquals("18:00", delivery.getDeliveryTime());
    }

    @Test
    public void testSetDeliveryTime() {
        delivery.setDeliveryTime("19:30");
        assertEquals("19:30", delivery.getDeliveryTime());
    }
}
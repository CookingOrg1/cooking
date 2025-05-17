package com.project.cooking;
import com.project.cooking.actors.Customer;


import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class CustomerTest {

    private Customer customer;

    @Before
    public void setUp() {
        customer = new Customer();
        customer.setFirstName("John");
        customer.setLastName("Doe");
        customer.setEmail("john.doe@example.com");
        customer.setPhone("1234567890");
        customer.setAddress("123 Main Street");
        customer.setDietaryPreferences("Vegetarian");
        customer.setAllergies("Peanuts");
        customer.setUsername("johndoe");
        customer.setPassword("securepassword");
        customer.setLastOrderDate("2025-05-01");
        customer.setPaymentMethod("Credit Card");
        customer.setOrderHistory(Arrays.asList("Order1", "Order2"));
    }

    @Test
    public void testFirstName() {
        assertEquals("John", customer.getFirstName());
    }

    @Test
    public void testLastName() {
        assertEquals("Doe", customer.getLastName());
    }

    @Test
    public void testEmail() {
        assertEquals("john.doe@example.com", customer.getEmail());
    }

    @Test
    public void testPhone() {
        assertEquals("1234567890", customer.getPhone());
    }

    @Test
    public void testAddress() {
        assertEquals("123 Main Street", customer.getAddress());
    }

    @Test
    public void testDietaryPreferences() {
        assertEquals("Vegetarian", customer.getDietaryPreferences());
    }

    @Test
    public void testAllergies() {
        assertEquals("Peanuts", customer.getAllergies());
    }

    @Test
    public void testUsername() {
        assertEquals("johndoe", customer.getUsername());
    }

    @Test
    public void testPassword() {
        assertEquals("securepassword", customer.getPassword());
    }

    @Test
    public void testLastOrderDate() {
        assertEquals("2025-05-01", customer.getLastOrderDate());
    }

    @Test
    public void testPaymentMethod() {
        assertEquals("Credit Card", customer.getPaymentMethod());
    }

    @Test
    public void testOrderHistory() {
        assertEquals(Arrays.asList("Order1", "Order2"), customer.getOrderHistory());
    }
}

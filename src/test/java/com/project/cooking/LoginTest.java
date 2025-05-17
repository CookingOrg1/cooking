package com.project.cooking;
import com.project.cooking.actors.Customer;
import com.project.cooking.actors.Login;
import com.project.cooking.actors.Chef;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class LoginTest {

    private Customer testCustomer;
    private Chef testChef;

    @Before
    public void setUp() {
        testCustomer = new Customer("cust1");
        testChef = new Chef("chef1");
        Login.logout();
    }

    @After
    public void tearDown() {
        Login.logout();  
    }

    @Test
    public void testLoginAsCustomer() {
        Login.loginAsCustomer(testCustomer);
        assertTrue(Login.isLoggedIn());
        assertTrue(Login.isCustomerLoggedIn());
        assertFalse(Login.isChefLoggedIn());
        assertEquals(testCustomer, Login.getLoggedInCustomer());
        assertNull(Login.getLoggedInChef());
        assertEquals("Customer", Login.getLoggedInUserRole());
    }

    @Test
    public void testLoginAsChef() {
        Login.loginAsChef(testChef);
        assertTrue(Login.isLoggedIn());
        assertFalse(Login.isCustomerLoggedIn());
        assertTrue(Login.isChefLoggedIn());
        assertNull(Login.getLoggedInCustomer());
        assertEquals(testChef, Login.getLoggedInChef());
        assertEquals("Chef", Login.getLoggedInUserRole());
    }

    @Test
    public void testSwitchLoginFromCustomerToChef() {
        Login.loginAsCustomer(testCustomer);
        Login.loginAsChef(testChef);

        assertFalse(Login.isCustomerLoggedIn());
        assertTrue(Login.isChefLoggedIn());
        assertNull(Login.getLoggedInCustomer());
        assertEquals(testChef, Login.getLoggedInChef());
        assertEquals("Chef", Login.getLoggedInUserRole());
    }

    @Test
    public void testSwitchLoginFromChefToCustomer() {
        Login.loginAsChef(testChef);
        Login.loginAsCustomer(testCustomer);

        assertTrue(Login.isCustomerLoggedIn());
        assertFalse(Login.isChefLoggedIn());
        assertEquals(testCustomer, Login.getLoggedInCustomer());
        assertNull(Login.getLoggedInChef());
        assertEquals("Customer", Login.getLoggedInUserRole());
    }

    @Test
    public void testLogout() {
        Login.loginAsCustomer(testCustomer);
        assertTrue(Login.isLoggedIn());

        Login.logout();
        assertFalse(Login.isLoggedIn());
        assertFalse(Login.isCustomerLoggedIn());
        assertFalse(Login.isChefLoggedIn());
        assertNull(Login.getLoggedInCustomer());
        assertNull(Login.getLoggedInChef());
        assertEquals("None", Login.getLoggedInUserRole());
    }

    @Test
    public void testNoUserLoggedIn() {
        assertFalse(Login.isLoggedIn());
        assertFalse(Login.isCustomerLoggedIn());
        assertFalse(Login.isChefLoggedIn());
        assertNull(Login.getLoggedInCustomer());
        assertNull(Login.getLoggedInChef());
        assertEquals("None", Login.getLoggedInUserRole());
    }
}

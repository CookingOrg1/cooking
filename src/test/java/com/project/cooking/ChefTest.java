package com.project.cooking;

import static org.junit.Assert.*;
import  com.project.cooking.actors.Chef;
import  com.project.cooking.actors.ChefTask;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.project.cooking.cart.CustomerProfile;
import com.project.cooking.cart.Notification;
import com.project.cooking.meals.Meal;

public class ChefTest {

    private Chef chef;

    @Before
    public void setUp() {
        chef = new Chef("John");
    }

    @Test
    public void testConstructorWithName() {
        assertEquals("John", chef.getName());
        assertEquals(0, chef.getWorkload());
    }

    @Test
    public void testNoArgConstructorAndSetName() {
        Chef c = new Chef();
        c.setName("Alice");
        assertEquals("Alice", c.getName());
    }

    @Test
    public void testGetSetRole() {
        assertEquals("HEAD_CHEF", chef.getRole());
        chef.setRole("Sous Chef");
        assertEquals("Sous Chef", chef.getRole());
    }

    @Test
    public void testGetSetExpertise() {
        chef.setExpertise("Italian");
        assertEquals("Italian", chef.getExpertise());
    }

    @Test
    public void testGetSetWorkload() {
        chef.setWorkload(5);
        assertEquals(5, chef.getWorkload());
    }

    @Test
    public void testGetSetTasksAddRemove() {
        List<ChefTask> tasks = new ArrayList<>();
        ChefTask task1 = new ChefTask("Prepare sauce");
        ChefTask task2 = new ChefTask("Chop vegetables");

        chef.setTasks(tasks);
        assertEquals(tasks, chef.getTasks());

        chef.addTask(task1);
        chef.addTask(task2);
        assertTrue(chef.getTasks().contains(task1));
        assertTrue(chef.getTasks().contains(task2));

        chef.removeTask(task1);
        assertFalse(chef.getTasks().contains(task1));
        assertTrue(chef.getTasks().contains(task2));
    }

    @Test
    public void testCustomizeMeal() {
        CustomerProfile profile = new CustomerProfile("Vegan");
        Meal meal = chef.customizeMeal(profile);
        assertNotNull(meal);
        assertEquals("Vegan Meal", meal.getName());
    }

    @Test
    public void testGetMyNotifications() {
        List<Notification> notifications = new ArrayList<>();
        notifications.add(new Notification("Test Notification"));
     
        List<Notification> notifs = chef.getMyNotifications();
        assertNotNull(notifs);
    }

    @Test
    public void testConstructorWithUserDetailsAndGetters() {
        List<String> assigned = new ArrayList<>(Arrays.asList("cust1", "cust2"));
        Chef c = new Chef("user1", "pass1", "First", "Last", "email@test.com", "1234567890", assigned);

        assertEquals("user1", c.getUsername());
        assertEquals("pass1", c.getPassword());
        assertEquals("First", c.getFirstName());
        assertEquals("Last", c.getLastName());
        assertEquals("email@test.com", c.getEmail());
        assertEquals("1234567890", c.getPhone());
        assertEquals(assigned, c.getAssignedCustomers());
    }

    @Test
    public void testSettersForUserDetails() {
        chef.setUsername("newUser");
        chef.setPassword("newPass");
        chef.setFirstName("NewFirst");
        chef.setLastName("NewLast");
        chef.setEmail("newemail@test.com");
        chef.setPhone("0987654321");

        List<String> newAssigned = new ArrayList<>();
        chef.setAssignedCustomers(newAssigned);

        assertEquals("newUser", chef.getUsername());
        assertEquals("newPass", chef.getPassword());
        assertEquals("NewFirst", chef.getFirstName());
        assertEquals("NewLast", chef.getLastName());
        assertEquals("newemail@test.com", chef.getEmail());
        assertEquals("0987654321", chef.getPhone());
        assertEquals(newAssigned, chef.getAssignedCustomers());
    }

    @Test
    public void testAddAssignedCustomer() {
        chef.setAssignedCustomers(new ArrayList<>());
        chef.addAssignedCustomer("customer1");
        assertTrue(chef.getAssignedCustomers().contains("customer1"));
    }

    @Test
    public void testSetGetSpecialty() {
        chef.setSpecialty("French");
 
    }
}


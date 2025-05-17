package com.project.cooking;
import  com.project.cooking.actors.Chef;
import com.project.cooking.actors.ChefTask;

import static org.junit.Assert.*;

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
        chef = new Chef();
    }
    
    @Test
    public void testConstructorWithName() {
        Chef c = new Chef("Gordon");
        assertEquals("Gordon", c.getName());
        assertEquals(0, c.getWorkload());
    }
    
    @Test
    public void testConstructorWithUserDetails() {
        List<String> customers = new ArrayList<>(Arrays.asList("Customer1", "Customer2"));
        Chef c = new Chef("user1", "pass123", "John", "Doe", "john.doe@example.com", "1234567890", customers);
        
        assertEquals("user1", c.getUsername());
        assertEquals("pass123", c.getPassword());
        assertEquals("John", c.getFirstName());
        assertEquals("Doe", c.getLastName());
        assertEquals("john.doe@example.com", c.getEmail());
        assertEquals("1234567890", c.getPhone());
        assertEquals(customers, c.getAssignedCustomers());
    }
    
    @Test
    public void testCustomizeMeal() {
        CustomerProfile profile = new CustomerProfile();
        profile.setDietaryPreferences("Vegan");
        
        Meal meal = chef.customizeMeal(profile);
        assertNotNull(meal);
        assertEquals("Vegan Meal", meal.getName());
    }
    
    @Test
    public void testTasksMethods() {
        ChefTask task1 = new ChefTask();
        ChefTask task2 = new ChefTask();
        
        assertTrue(chef.getTasks().isEmpty());
        
        chef.addTask(task1);
        assertEquals(1, chef.getTasks().size());
        assertTrue(chef.getTasks().contains(task1));
        
        chef.addTask(task2);
        assertEquals(2, chef.getTasks().size());
        
        chef.removeTask(task1);
        assertEquals(1, chef.getTasks().size());
        assertFalse(chef.getTasks().contains(task1));
        
        List<ChefTask> newTasks = new ArrayList<>();
        newTasks.add(task1);
        chef.setTasks(newTasks);
        assertEquals(newTasks, chef.getTasks());
    }
    
    @Test
    public void testRoleGetterSetter() {
        assertEquals("HEAD_CHEF", chef.getRole());
        chef.setRole("SOUS_CHEF");
        assertEquals("SOUS_CHEF", chef.getRole());
    }
    
    @Test
    public void testNameGetterSetter() {
        chef.setName("Jamie Oliver");
        assertEquals("Jamie Oliver", chef.getName());
    }
    
    @Test
    public void testExpertiseGetterSetter() {
        chef.setExpertise("Italian Cuisine");
        assertEquals("Italian Cuisine", chef.getExpertise());
    }
    
    @Test
    public void testWorkloadGetterSetter() {
        chef.setWorkload(5);
        assertEquals(5, chef.getWorkload());
    }
    
    @Test
    public void testUsernameGetterSetter() {
        chef.setUsername("chef123");
        assertEquals("chef123", chef.getUsername());
    }
    
    @Test
    public void testPasswordGetterSetter() {
        chef.setPassword("secret");
        assertEquals("secret", chef.getPassword());
    }
    
    @Test
    public void testFirstNameGetterSetter() {
        chef.setFirstName("Alice");
        assertEquals("Alice", chef.getFirstName());
    }
    
    @Test
    public void testLastNameGetterSetter() {
        chef.setLastName("Smith");
        assertEquals("Smith", chef.getLastName());
    }
    
    @Test
    public void testEmailGetterSetter() {
        chef.setEmail("alice@example.com");
        assertEquals("alice@example.com", chef.getEmail());
    }
    
    @Test
    public void testPhoneGetterSetter() {
        chef.setPhone("9876543210");
        assertEquals("9876543210", chef.getPhone());
    }
    
    @Test
    public void testAssignedCustomersGetterSetterAdd() {
        List<String> customers = new ArrayList<>();
        chef.setAssignedCustomers(customers);
        assertEquals(customers, chef.getAssignedCustomers());
        
        chef.addAssignedCustomer("CustomerA");
        assertTrue(chef.getAssignedCustomers().contains("CustomerA"));
    }
    
    @Test
    public void testSpecialtySetter() {
        chef.setSpecialty("French Cuisine");
        // specialty field has no getter, so we test by reflection or just assume setter works.
        // Here, we test indirectly by reflection:
        try {
            java.lang.reflect.Field field = Chef.class.getDeclaredField("specialty");
            field.setAccessible(true);
            assertEquals("French Cuisine", field.get(chef));
        } catch (Exception e) {
            fail("Exception accessing specialty field: " + e.getMessage());
        }
    }
    
    @Test
    public void testGetMyNotifications() {
        // Since NotificationCenter.getNotificationsForChef is static and external,
        // we can't test internal logic here without mocking.
        // But we can call and check it returns a non-null List (or mock NotificationCenter if using a framework).
        
        List<Notification> notifications = chef.getMyNotifications();
        assertNotNull(notifications);
    }
}
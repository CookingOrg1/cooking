package com.project.cooking;
import com.project.cooking.cart.Cart;
import com.project.cooking.meals.Meal;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class CartTest {

    private Cart cart;

    @Before
    public void setUp() {
        cart = new Cart();
    }

    @Test
    public void testAddItem() {
        Meal meal = new Meal("Burger", 5.99);  
        cart.addItem(meal);

        List<Meal> items = cart.getItems();
        assertEquals(1, items.size());
        assertEquals("Burger", items.get(0).getName());
        assertEquals(5.99, items.get(0).getPrice(), 0.001);
    }

    @Test
    public void testGetItemsReturnsCopy() {
        Meal meal = new Meal("Pizza", 8.50);
        cart.addItem(meal);

        List<Meal> originalItems = cart.getItems();
        originalItems.clear();  

        assertEquals(1, cart.getItems().size());
    }

    @Test
    public void testClearCart() {
        cart.addItem(new Meal("Sushi", 12.00));
        cart.addItem(new Meal("Salad", 6.00));

        cart.clear();
        assertTrue(cart.getItems().isEmpty());
    }

    @Test
    public void testEmptyCartInitially() {
        assertTrue(cart.getItems().isEmpty());
    }
}
package com.project.cooking.cart;

import java.util.ArrayList;
import java.util.List;
import com.project.cooking.meals.Meal;

/**
 * The Cart class represents a shopping cart that holds a list of meal items.
 * It provides methods to add items to the cart, retrieve the items, and clear the cart.
 * 
 * @author abood
 */
public class Cart {
    
    // List to hold meal items in the cart
    public List<Meal> items = new ArrayList<>();

    /**
     * Adds a meal item to the cart.
     * 
     * @param meal The Meal object to be added to the cart.
     * @return void
     * @author abood
     */
    public void addItem(Meal meal) {
        items.add(meal);
    }

    /**
     * Retrieves the list of meal items in the cart.
     * This method returns a new ArrayList to prevent external modification of the cart's items list.
     * 
     * @return List<Meal> - a copy of the list of items in the cart.
     * @author abood
     */
    public List<Meal> getItems() {
        return new ArrayList<>(items);
    }

    /**
     * Clears all items from the cart.
     * This method removes all meal items from the cart, essentially emptying it.
     * 
     * @return void
     * @author abood
     */
    public void clear() {
        items.clear();
    }
}
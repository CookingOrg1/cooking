package com.project.cooking.kitchen;

import java.util.ArrayList;
import java.util.List;
import com.project.cooking.actors.Chef;

/**
 * Manages kitchen operations such as tracking chefs and handling ingredient notifications
 * based on stock levels.
 * 
 * @author abood
 */
public class Kitchen {

    /**
     * List of all chefs working in the kitchen.
     * 
     * @author abood
     */
    private static List<Chef> chefs = new ArrayList<>();

    /**
     * Indicates whether a general restocking notification has been sent.
     * 
     * @author abood
     */
    private boolean notified = false;

    /**
     * Indicates whether an urgent restocking notification has been sent.
     * 
     * @author abood
     */
    private boolean notifiedImmediately = false;

    /**
     * Adds a chef to the kitchen.
     *
     * @param chef The Chef object to add.
     * 
     * @author abood
     */
    public static void addChef(Chef chef) {
        chefs.add(chef);
    }

    /**
     * Retrieves a list of all chefs in the kitchen.
     *
     * @return A new list containing all current chefs.
     * 
     * @author abood
     */
    public static List<Chef> getAllChefs() {
        return new ArrayList<>(chefs);
    }

    /**
     * Clears the list of all chefs in the kitchen.
     * 
     * @author abood
     */
    public static void clearChefs() {
        chefs.clear();
    }

    /**
     * Checks the stock level of an ingredient and sets restocking flags accordingly.
     * 
     * If stock level is below threshold but not zero, general restocking is suggested.
     * If stock level is zero, urgent restocking is suggested.
     *
     * @param ingredient The Ingredient to evaluate.
     * 
     * @author abood
     */
    public void checkAndNotify(Ingredient ingredient) {
        if (ingredient.getStockLevel() <= ingredient.getThreshold() && ingredient.getStockLevel() > 0) {
            ingredient.setRestockingSuggested(true);
            notified = true;
        } else if (ingredient.getStockLevel() == 0) {
            ingredient.setUrgentRestockingSuggested(true);
            notifiedImmediately = true;
        }
    }

    /**
     * Checks if a general restocking notification has been issued.
     *
     * @return true if a notification has been issued; false otherwise.
     * 
     * @author abood
     */
    public boolean isNotified() {
        return notified;
    }

    /**
     * Checks if an urgent restocking notification has been issued.
     *
     * @return true if an urgent notification has been issued; false otherwise.
     * 
     * @author abood
     */
    public boolean isNotifiedImmediately() {
        return notifiedImmediately;
    }

    /**
     * Dummy method that returns true if a notification was received for a given ingredient name.
     * Currently returns the `notified` flag regardless of ingredient name.
     *
     * @param ingredientName The name of the ingredient (not used in logic).
     * @return true if a notification was sent; false otherwise.
     * 
     * @author abood
     */
    public boolean receivedNotificationFor(String ingredientName) {
        return notified;
    }
}
package com.project.cooking.kitchen;

import java.time.LocalDate;
import java.util.List;
import com.project.cooking.actors.KitchenManager;

/**
 * Manages stock and expiry monitoring of kitchen ingredients and issues alerts
 * through the KitchenManager.
 * 
 * @author abood
 */
public class StockManager {

    /**
     * The KitchenManager responsible for handling alerts related to stock and expiry.
     * 
     * @author abood
     */
    private KitchenManager kitchenManager;

    /**
     * Constructs a StockManager with a given KitchenManager.
     *
     * @param kitchenManager the KitchenManager to send alerts to
     * 
     * @author abood
     */
    public StockManager(KitchenManager kitchenManager) {
        this.kitchenManager = kitchenManager;
    }

    /**
     * Checks the expiry dates of a list of ingredients and issues alerts if any ingredient
     * is expiring within the next 7 days.
     *
     * @param ingredients list of ingredients to check
     * 
     * @author abood
     */
    public void checkExpiryDates(List<Ingredient> ingredients) {
        for (Ingredient ingredient : ingredients) {
            if (ingredient.getExpiryDate().isBefore(LocalDate.now().plusDays(7))) {
                kitchenManager.addAlert("Use or reorder " + ingredient.getName());
            }
        }
    }

    /**
     * Checks the stock levels of ingredients and issues alerts based on thresholds.
     * Issues a general alert if stock is below the reorder threshold.
     * Issues an urgent alert if stock is critically low (less than 2).
     *
     * @param ingredients list of ingredients to check
     * 
     * @author abood
     */
    public void checkStockLevels(List<Ingredient> ingredients) {
        for (Ingredient ingredient : ingredients) {
            if (ingredient.getStock() < ingredient.getReorderThreshold()) {
                kitchenManager.addAlert("Reorder " + ingredient.getName());
            }
            if (ingredient.getStock() < 2) {
                kitchenManager.addAlert("URGENT: Reorder " + ingredient.getName() + " immediately");
            }
        }
    }
}
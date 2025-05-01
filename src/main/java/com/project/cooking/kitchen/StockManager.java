package com.project.cooking.kitchen;
import java.time.LocalDate;
import java.util.List;

import com.project.cooking.actors.KitchenManager;

public class StockManager {
    private KitchenManager kitchenManager;

    public StockManager(KitchenManager kitchenManager) {
        this.kitchenManager = kitchenManager;
    }

    public void checkExpiryDates(List<Ingredient> ingredients) {
        for (Ingredient ingredient : ingredients) {
            if (ingredient.getExpiryDate().isBefore(LocalDate.now().plusDays(7))) {
                kitchenManager.addAlert("Use or reorder " + ingredient.getName());
            }
        }
    }

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
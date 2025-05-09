package com.project.cooking.kitchen;
import java.util.ArrayList;
import java.util.List;

import com.project.cooking.actors.Chef; 

public class Kitchen {
    private static List<Chef> chefs = new ArrayList<>();
    private boolean notified = false;
    private boolean notifiedImmediately = false;

    public static void addChef(Chef chef) {
    	chefs.add(chef);
    }

    public static List<Chef> getAllChefs() {
        return new ArrayList<>(chefs);
    }

    public static void clearChefs() {
        chefs.clear();
    }
    public void checkAndNotify(Ingredient ingredient) {
        if (ingredient.getStockLevel() <= ingredient.getThreshold() && ingredient.getStockLevel() > 0) {
            ingredient.setRestockingSuggested(true);
            notified = true;
        } else if (ingredient.getStockLevel() == 0) {
            ingredient.setUrgentRestockingSuggested(true);
            notifiedImmediately = true;
        }
    }

    public boolean isNotified() {
        return notified;
    }

    public boolean isNotifiedImmediately() {
        return notifiedImmediately;
    }

    public boolean receivedNotificationFor(String ingredientName) {
        return notified;
    }
}
package com.project.cooking.cart;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import com.project.cooking.actors.Chef;
import com.project.cooking.meals.Task;

public class NotificationCenter {

    private static Map<Chef, List<Notification>> chefNotifications = new HashMap<>();

    /**
     * Assigns a task to a Chef and sends a notification to the Chef about the task.
     * This method adds a notification to the Chef's notification list and 
     * increments the Chef's workload.
     * 
     * @param chef The Chef to whom the task is assigned.
     * @param task The task that is assigned to the Chef.
     * @author abood
     * @return void
     */
    public static void assignTaskToChef(Chef chef, Task task) {
        Notification notification = new Notification("Task assigned: " + task.getDescription());
        chefNotifications.computeIfAbsent(chef, k -> new ArrayList<>()).add(notification);
        chef.setWorkload(chef.getWorkload() + 1);
    }

    /**
     * Retrieves the list of notifications for a given Chef.
     * 
     * @param chef The Chef whose notifications are to be retrieved.
     * @author abood
     * @param none
     * @return List<Notification> - the list of notifications for the Chef.
     */
    public static List<Notification> getNotificationsForChef(Chef chef) {
        return chefNotifications.getOrDefault(chef, new ArrayList<>());
    }

    /**
     * Marks a specific notification for a Chef as "READ".
     * 
     * @param chef The Chef whose notification status is to be updated.
     * @param notification The notification to be marked as read.
     * @author abood
     * @param none
     * @return void
     */
    public static void markNotificationAsRead(Chef chef, Notification notification) {
        notification.setStatus("READ");
    }

    /**
     * Clears all notifications for all chefs.
     * 
     * @author abood
     * @param none
     * @return void
     */
    public static void clearNotifications() {
        chefNotifications.clear();
    }
}
package com.project.cooking.cart;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import com.project.cooking.actors.Chef;
import com.project.cooking.meals.Task;

public class NotificationCenter {

    private static Map<Chef, List<Notification>> chefNotifications = new HashMap<>();

    public static void assignTaskToChef(Chef chef, Task task) {
        Notification notification = new Notification("Task assigned: " + task.getDescription());
        chefNotifications.computeIfAbsent(chef, k -> new ArrayList<>()).add(notification);
        chef.setWorkload(chef.getWorkload() + 1); 
    }

    public static List<Notification> getNotificationsForChef(Chef chef) {
        return chefNotifications.getOrDefault(chef, new ArrayList<>());
    }

    public static void markNotificationAsRead(Chef chef, Notification notification) {
        notification.setStatus("READ");
    }

    public static void clearNotifications() {
        chefNotifications.clear();
    }
}
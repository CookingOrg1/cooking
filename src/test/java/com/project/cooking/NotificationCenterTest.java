package com.project.cooking;

import com.project.cooking.actors.Chef;
import com.project.cooking.cart.Notification;
import com.project.cooking.cart.NotificationCenter;
import com.project.cooking.meals.Task;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class NotificationCenterTest {

    private Chef chef;
    private Task task;

    @Before
    public void setUp() {
        chef = new Chef();
        chef.setName("Chef John");
        chef.setWorkload(0);

        task = new Task();
        task.setDescription("Prepare Salad");

        NotificationCenter.clearNotifications(); 
    }

    @Test
    public void testAssignTaskToChef_AddsNotificationAndIncrementsWorkload() {
        NotificationCenter.assignTaskToChef(chef, task);

        List<Notification> notifications = NotificationCenter.getNotificationsForChef(chef);

        assertEquals(1, notifications.size());
        assertEquals("Task assigned: Prepare Salad", notifications.get(0).getMessage());
        assertEquals(1, chef.getWorkload());
    }

    @Test
    public void testGetNotificationsForChef_EmptyInitially() {
        List<Notification> notifications = NotificationCenter.getNotificationsForChef(chef);
        assertTrue(notifications.isEmpty());
    }

    @Test
    public void testMarkNotificationAsRead() {
        NotificationCenter.assignTaskToChef(chef, task);
        Notification notification = NotificationCenter.getNotificationsForChef(chef).get(0);

        assertEquals("UNREAD", notification.getStatus());  

        NotificationCenter.markNotificationAsRead(chef, notification);

        assertEquals("READ", notification.getStatus());
    }

    @Test
    public void testClearNotifications() {
        NotificationCenter.assignTaskToChef(chef, task);
        NotificationCenter.clearNotifications();

        assertTrue(NotificationCenter.getNotificationsForChef(chef).isEmpty());
    }
}

package com.project.cooking;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import com.project.cooking.cart.Notification;

public class NotificationTest {

    private Notification notification;

    @Before
    public void setUp() {
        notification = new Notification("Order completed");
    }

    @Test
    public void testConstructorSetsMessageAndDefaultStatus() {
        assertEquals("Order completed", notification.getMessage());
        assertEquals("UNREAD", notification.getStatus());
    }

    @Test
    public void testGetMessage() {
        assertEquals("Order completed", notification.getMessage());
    }

    @Test
    public void testGetStatus() {
        assertEquals("UNREAD", notification.getStatus());
    }

    @Test
    public void testSetStatus() {
        notification.setStatus("READ");
        assertEquals("READ", notification.getStatus());

        notification.setStatus("UNREAD");
        assertEquals("UNREAD", notification.getStatus());
    }
}
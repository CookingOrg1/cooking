package com.project.cooking;

import org.junit.Before;
import org.junit.Test;

import com.project.cooking.kitchen.ChefAlertService;

import static org.junit.Assert.*;

public class ChefAlertServiceTest {

    private ChefAlertService service;

    @Before
    public void setUp() {
        service = new ChefAlertService();
    }

    @Test
    public void testNotifyChef() {
        String substitute = "Almond Milk";
        String original = "Cow Milk";
        String expectedMessage = "Alert: The ingredient 'Cow Milk' has been substituted with 'Almond Milk'. Please review.";
        String actualMessage = service.notifyChef(substitute, original);
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void testApproveSubstitution_True() {
        boolean decision = true;
        boolean result = service.approveSubstitution(decision);
        assertTrue(result);
        assertTrue(service.isApproved());
    }

    @Test
    public void testApproveSubstitution_False() {
        boolean decision = false;
        boolean result = service.approveSubstitution(decision);
        assertFalse(result);
        assertFalse(service.isApproved());
    }

    @Test
    public void testIsApproved_Default() {
        assertFalse(service.isApproved());
    }
}

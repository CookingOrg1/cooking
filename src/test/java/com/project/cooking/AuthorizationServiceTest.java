package com.project.cooking;
import com.project.cooking.actors.AuthorizationService;
import com.project.cooking.actors.Chef;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class AuthorizationServiceTest {

    private AuthorizationService authorizationService;

    @Before
    public void setUp() {
        authorizationService = new AuthorizationService();
    }

    @Test
    public void testHasOrderHistoryAccess_WithHeadChef() {
        Chef headChef = new Chef();
        headChef.setRole("HEAD_CHEF");
        assertTrue(authorizationService.hasOrderHistoryAccess(headChef));
    }

    @Test
    public void testHasOrderHistoryAccess_WithOtherRole() {
        Chef sousChef = new Chef();
        sousChef.setRole("SOUS_CHEF");
        assertFalse(authorizationService.hasOrderHistoryAccess(sousChef));
    }

    @Test
    public void testHasOrderHistoryAccess_WithNullChef() {
        assertFalse(authorizationService.hasOrderHistoryAccess(null));
    }

    @Test
    public void testHasOrderHistoryAccess_WithNullRole() {
        Chef chefWithNullRole = new Chef();
        chefWithNullRole.setRole(null);
        assertFalse(authorizationService.hasOrderHistoryAccess(chefWithNullRole));
    }
}

package com.project.cooking.actors;


public class AuthorizationService {

    /**
     * Determines whether the specified chef has access to view order history.
     * Only chefs with the role of "HEAD_CHEF" are permitted to access the order history.
     *
     * @author abood
     * @param chef - the Chef object representing the user whose access is being evaluated.
     * @return boolean - true if the chef is not null and has the role "HEAD_CHEF"; false otherwise.
     */
    public boolean hasOrderHistoryAccess(Chef chef) {
        return chef != null && chef.getRole().equals("HEAD_CHEF");
    }
}

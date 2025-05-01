package com.project.cooking.actors;


public class AuthorizationService {
    public boolean hasOrderHistoryAccess(Chef chef) {
        return chef != null && chef.getRole().equals("HEAD_CHEF");
    }
}



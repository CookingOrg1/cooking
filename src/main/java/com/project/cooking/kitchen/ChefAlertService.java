package com.project.cooking.kitchen;
public class ChefAlertService {

    private boolean approvalStatus;

    
    public String notifyChef(String substitute, String original) {
        return "Alert: The ingredient '" + original + "' has been substituted with '" + substitute + "'. Please review.";
    }

    
    public boolean approveSubstitution(boolean decision) {
        this.approvalStatus = decision;
        return approvalStatus;
    }

   
    public boolean isApproved() {
        return approvalStatus;
    }
}
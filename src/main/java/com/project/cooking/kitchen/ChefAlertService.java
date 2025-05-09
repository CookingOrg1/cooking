package com.project.cooking.kitchen;

public class ChefAlertService {

    private boolean approvalStatus;

    /**
     * Notifies the chef about an ingredient substitution.
     * 
     * @param substitute The substitute ingredient.
     * @param original The original ingredient that is being substituted.
     * @return String - The alert message informing the chef about the substitution.
     * @author abood
     */
    public String notifyChef(String substitute, String original) {
        return "Alert: The ingredient '" + original + "' has been substituted with '" + substitute + "'. Please review.";
    }

    /**
     * Approves or rejects the substitution decision.
     * 
     * @param decision A boolean value indicating the approval status.
     * @return boolean - The approval status (true for approval, false for rejection).
     * @author abood
     */
    public boolean approveSubstitution(boolean decision) {
        this.approvalStatus = decision;
        return approvalStatus;
    }

    /**
     * Checks if the substitution has been approved.
     * 
     * @return boolean - true if the substitution is approved, false otherwise.
     * @author abood
     */
    public boolean isApproved() {
        return approvalStatus;
    }
}
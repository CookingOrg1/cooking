package com.project.cooking;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.*;

import com.project.cooking.kitchen.ChefAlertService;
import com.project.cooking.kitchen.Ingredient;
import com.project.cooking.meals.Meal;

public class ChefIngredientSubstitutionAlertTest {

    private Meal currentMeal;
    private ChefAlertService chefAlertService;
    private String alertMessage;
    private boolean chefApproval;
    private String originalIngredient;
    private String substitutedIngredient;

    @Given("A substitution of {string} for {string} has been applied")
    public void aSubstitutionOfForHasBeenApplied(String substitute, String original) {
        this.originalIngredient = original;
        this.substitutedIngredient = substitute;
        this.currentMeal = new Meal("Test Meal");
        this.currentMeal.addIngredient(new Ingredient(substitute));
        this.chefAlertService = new ChefAlertService();
    }

    @When("the chef is notified about the substitution")
    public void theChefIsNotifiedAboutTheSubstitution() {
        alertMessage = chefAlertService.notifyChef(substitutedIngredient, originalIngredient);
    }

    @Then("the chef should receive an alert with message {string}")
    public void theChefShouldReceiveAnAlertWithMessage(String expectedMessage) {
        assertEquals(expectedMessage, alertMessage);
    }

    @When("the chef approves the substitution")
    public void theChefApprovesTheSubstitution() {
        chefApproval = chefAlertService.approveSubstitution(true);
    }

    @Then("the recipe should be updated with {string} instead of {string}")
    public void theRecipeShouldBeUpdatedWithInsteadOf(String substitute, String original) {
        if (chefApproval) {
            assertTrue(currentMeal.hasIngredient(substitute));
            assertFalse(currentMeal.hasIngredient(original));
        }
    }

    @When("the chef rejects the substitution")
    public void theChefRejectsTheSubstitution() {
        chefApproval = chefAlertService.approveSubstitution(false);
    }

    @Then("the substitution should be canceled")
    public void theSubstitutionShouldBeCanceled() {
    	 if (!chefApproval) {
    	        Ingredient originalIngredientObj = new Ingredient(originalIngredient);
    	        currentMeal.removeIngredient(new Ingredient(substitutedIngredient));
    	        currentMeal.addIngredient(originalIngredientObj);
    	    }
    }

    @Then("the original ingredient {string} should remain in the recipe")
    public void theOriginalIngredientShouldRemainInTheRecipe(String original) {
        assertTrue(currentMeal.hasIngredient(original));
        assertFalse(currentMeal.hasIngredient(substitutedIngredient));
    }
}
package com.project.cooking;

import com.project.cooking.kitchen.Ingredient;
import com.project.cooking.kitchen.Kitchen;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.*;

public class IngredientRestockingNotificationTest {

    private Ingredient ingredient;
    private Kitchen kitchenManager;
    private Kitchen kitchen;

    @Given("the ingredient {string} has a threshold of {int} units")
    public void theIngredientHasAThresholdOfUnits(String ingredientName, Integer threshold) {
        ingredient = new Ingredient(ingredientName, threshold);
        kitchen = new Kitchen();
    }

    @Given("the current stock level of {string} is {int} units")
    public void theCurrentStockLevelOfIsUnits(String ingredientName, Integer stockLevel) {
        if (ingredient != null) {
            ingredient.setStockLevel(stockLevel);
        }
    }

    @When("the system checks ingredient levels")
    public void theSystemChecksIngredientLevels() {
        kitchenManager = new Kitchen();
        kitchenManager.checkAndNotify(ingredient);
    }

    @Then("the system should suggest restocking {string}")
    public void theSystemShouldSuggestRestocking(String ingredientName) {
        assertTrue(ingredient.isRestockingSuggested());
    }

    @Then("the system should suggest urgent restocking of {string}")
    public void theSystemShouldSuggestUrgentRestockingOf(String ingredientName) {
        assertTrue(ingredient.isUrgentRestockingSuggested());
    }

   

    @When("the system suggests restocking {string}")
    public void theSystemSuggestsRestocking(String ingredientName) {
        ingredient.setRestockingSuggested(true);
    }

  
}
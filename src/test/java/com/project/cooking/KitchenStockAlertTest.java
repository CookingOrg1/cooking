package com.project.cooking;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.util.List;

import com.project.cooking.actors.KitchenManager;
import com.project.cooking.kitchen.Ingredient;
import com.project.cooking.kitchen.StockManager;

import java.util.ArrayList;

public class KitchenStockAlertTest {

    private KitchenManager kitchenManager;
    private List<Ingredient> ingredients;
    private StockManager stockManager;

    @Given("I am a kitchen manager with ingredients in stock")
    public void iAmAKitchenManagerWithIngredientsInStock() {
        kitchenManager = new KitchenManager();
        ingredients = new ArrayList<>();
        stockManager = new StockManager(kitchenManager);

        ingredients.add(new Ingredient("Tomato", 10, 5));
        ingredients.add(new Ingredient("Onion", 20, 5)); 
    }

    @When("the stock level of an ingredient falls below the reorder threshold")
    public void theStockLevelOfAnIngredientFallsBelowTheReorderThreshold() {
       
        Ingredient tomato = ingredients.get(0);
        tomato.setStock(3); 
        stockManager.checkStockLevels(ingredients);
    }

    @Then("I should receive an alert for that ingredient to reorder")
    public void iShouldReceiveAnAlertForThatIngredientToReorder() {
        
        assert kitchenManager.getAlerts().contains("Reorder Tomato");
    }

    @When("the stock level of an ingredient is above the reorder threshold")
    public void theStockLevelOfAnIngredientIsAboveTheReorderThreshold() {
        Ingredient onion = ingredients.get(1);
        onion.setStock(20); 
        stockManager.checkStockLevels(ingredients);
    }

    @Then("I should not receive an alert for that ingredient")
    public void iShouldNotReceiveAnAlertForThatIngredient() {
        assert !kitchenManager.getAlerts().contains("Reorder Onion");
    }

    @Given("I am a kitchen manager with multiple ingredients in stock")
    public void iAmAKitchenManagerWithMultipleIngredientsInStock() {
        kitchenManager = new KitchenManager();
        ingredients = new ArrayList<>();
        stockManager = new StockManager(kitchenManager);

        ingredients.add(new Ingredient("Tomato", 2, 5)); 
        ingredients.add(new Ingredient("Onion", 6, 5));  
        ingredients.add(new Ingredient("Lettuce", 3, 5)); 
    }

    @When("multiple ingredients fall below their reorder thresholds")
    public void multipleIngredientsFallBelowTheirReorderThresholds() {
        stockManager.checkStockLevels(ingredients);
    }

    @Then("I should receive separate alerts for each ingredient to reorder")
    public void iShouldReceiveSeparateAlertsForEachIngredientToReorder() {
        assert kitchenManager.getAlerts().contains("Reorder Tomato");
        assert kitchenManager.getAlerts().contains("Reorder Lettuce");
    }

    @Given("I am a kitchen manager with ingredients nearing expiry")
    public void iAmAKitchenManagerWithIngredientsNearingExpiry() {
        kitchenManager = new KitchenManager();
        ingredients = new ArrayList<>();
        stockManager = new StockManager(kitchenManager);

        ingredients.add(new Ingredient("Tomato", 5, 3, "2023-12-01"));
        ingredients.add(new Ingredient("Onion", 10, 5, "2024-02-15"));
    }

    @When("an ingredient's expiry date is near")
    public void anIngredientSExpiryDateIsNear() {
        stockManager.checkExpiryDates(ingredients);
    }

    @Then("I should receive an alert to use or reorder that ingredient")
    public void iShouldReceiveAnAlertToUseOrReorderThatIngredient() {
        assert kitchenManager.getAlerts().contains("Use or reorder Tomato");
    }

    @Given("I am a kitchen manager with critical stock levels of ingredients")
    public void iAmAKitchenManagerWithCriticalStockLevelsOfIngredients() {
        kitchenManager = new KitchenManager();
        ingredients = new ArrayList<>();
        stockManager = new StockManager(kitchenManager);

        ingredients.add(new Ingredient("Tomato", 1, 2)); 
    }
    @When("the stock level falls to a critical low")
    public void theStockLevelFallsToACriticalLow() {
        stockManager.checkStockLevels(ingredients);
    }

    @Then("I should receive an urgent alert to reorder immediately")
    public void iShouldReceiveAnUrgentAlertToReorderImmediately() {
        assert kitchenManager.getAlerts().contains("URGENT: Reorder Tomato immediately");
    }
}

package com.project.cooking;

import java.util.HashMap;
import java.util.Map;

//import com.project.cooking.actors.Ingredient;
//import com.project.cooking.actors.Kitchen;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static org.junit.Assert.*;

public class IngredientStockManagementTest {

    private Map<String, Double> inventory = new HashMap<>();
    private Map<String, Double> thresholds = new HashMap<>();
    private String lastErrorMessage = "";
    private String lastAlertMessage = "";


    @Given("{string} with quantity {string} exists in the inventory")
    public void withQuantityExistsInTheInventory(String ingredientName, String quantityStr) {
        double quantity = parseQuantity(quantityStr);
        inventory.put(ingredientName, quantity);
    }

    @When("{string} of {string} are attempted to be used")
    public void ofAreAttemptedToBeUsed(String quantityStr, String ingredientName) {
        double requestedAmount = parseQuantity(quantityStr);
        double currentStock = inventory.getOrDefault(ingredientName, 0.0);

        if (requestedAmount > currentStock) {
            lastErrorMessage = "Insufficient stock for " + ingredientName;
        } else {
            inventory.put(ingredientName, currentStock - requestedAmount);
        }
    }

    @Then("an error message should be displayed stating {string}")
    public void anErrorMessageShouldBeDisplayedStating(String expectedMessage) {
        assertEquals(expectedMessage, lastErrorMessage);
    }

    @Then("the stock level should remain {string}")
    public void theStockLevelShouldRemain(String expectedQuantity) {
        double expected = parseQuantity(expectedQuantity);
        String ingredientName = extractIngredientNameFromInventory(expected);
        assertEquals(expected, inventory.getOrDefault(ingredientName, 0.0), 0.01);
    }


    @Given("the minimum threshold for {string} is set to {string}")
    public void theMinimumThresholdForIsSetTo(String ingredientName, String thresholdStr) {
        double threshold = parseQuantity(thresholdStr);
        thresholds.put(ingredientName, threshold);
    }

    @When("the stock level of {string} is updated")
    public void theStockLevelOfIsUpdated(String ingredientName) {
        double currentStock = inventory.getOrDefault(ingredientName, 0.0);
        double threshold = thresholds.getOrDefault(ingredientName, 0.0);

        if (currentStock < threshold) {
            lastAlertMessage = "Low stock alert for " + ingredientName;
        }
    }

    @Then("an alert should be generated indicating low stock for {string}")
    public void anAlertShouldBeGeneratedIndicatingLowStockFor(String ingredientName) {
        assertEquals("Low stock alert for " + ingredientName, lastAlertMessage);
    }


    @When("a kitchen staff member uses {string} of {string}")
    public void aKitchenStaffMemberUsesOf(String quantityStr, String ingredientName) {
        double usedAmount = parseQuantity(quantityStr);
        double currentStock = inventory.getOrDefault(ingredientName, 0.0);

        if (usedAmount <= currentStock) {
            inventory.put(ingredientName, currentStock - usedAmount);
        } else {
            lastErrorMessage = "Insufficient stock for " + ingredientName;
        }
    }

    @Then("as a kitchen manager, I should immediately see {string} of {string} in stock")
    public void asAKitchenManagerIShouldImmediatelySeeOfInStock(String expectedQuantity, String ingredientName) {
        double expected = parseQuantity(expectedQuantity);
        assertEquals(expected, inventory.getOrDefault(ingredientName, 0.0), 0.01);
    }


    private double parseQuantity(String quantityStr) {
        return Double.parseDouble(quantityStr.split(" ")[0]);
    }

    private String extractIngredientNameFromInventory(double expectedQuantity) {
        for (Map.Entry<String, Double> entry : inventory.entrySet()) {
            if (entry.getValue() == expectedQuantity) {
                return entry.getKey();
            }
        }
        return "";
    }
}
package com.project.cooking;

import com.project.cooking.kitchen.Ingredient;
import com.project.cooking.kitchen.IngredientService;
import com.project.cooking.meals.Meal;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.*;

public class AlternativeIngredientSuggestionTest {
    private Meal currentMeal;
    private String dietaryRestriction;
    private String suggestedAlternative;
    private IngredientService ingredientService = new IngredientService();

    @Given("I am ordering a dish with {string} as an ingredient")
    public void iAmOrderingADishWithAsAnIngredient(String ingredient) {
        currentMeal = new Meal("Test Meal");
        currentMeal.addIngredient(new Ingredient(ingredient));
    }

    @When("{string} is unavailable")
    public void isUnavailable(String ingredient) {
        suggestedAlternative = ingredientService.findAlternative(ingredient, dietaryRestriction, false);
    }

    @Given("I have the dietary restriction {string}")
    public void iHaveTheDietaryRestriction(String restriction) {
        this.dietaryRestriction = restriction;
    }

    @When("{string} does not fit my dietary restriction")
    public void doesNotFitMyDietaryRestriction(String ingredient) {
        suggestedAlternative = ingredientService.findAlternative(ingredient, dietaryRestriction, true);
    }

    @Then("the system should suggest {string} as an alternative")
    public void theSystemShouldSuggestAsAnAlternative(String expected) {
        assertEquals(expected, suggestedAlternative);
    }

    @When("{string} is available")
    public void isAvailable(String ingredient) {
    	
        boolean available = ingredientService.isAvailable(ingredient);
        if (available) {
            suggestedAlternative = null; 
        }
    }

    @When("{string} fits my dietary restrictions")
    public void fitsMyDietaryRestrictions(String ingredient) {
        assertTrue(ingredientService.isDietaryCompliant(ingredient, dietaryRestriction));
    }

    @Then("the system should not suggest any alternative ingredient")
    public void theSystemShouldNotSuggestAnyAlternativeIngredient() {
        assertNull(suggestedAlternative);
    }
}
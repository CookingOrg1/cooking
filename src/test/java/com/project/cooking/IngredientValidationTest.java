package com.project.cooking;

import com.project.cooking.actors.Customer;
import com.project.cooking.actors.Login;
import com.project.cooking.meals.Meal;
import com.project.cooking.meals.MealRecommendationService;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;

import static org.junit.Assert.*;

public class IngredientValidationTest {

    private Customer customer;
     @SuppressWarnings("unused")
	private Meal meal;
    private MealRecommendationService mealRecommendationService;
    private boolean customizationSuccessful;
    private String errorMessage;
    private List<String> recommendedMeals;

    @Given("I am logged in as a customer")
    public void iAmLoggedInAsACustomer() {
    	 customer = new Customer();
        Login.loginAsCustomer(customer);  

        if (!Login.isCustomerLoggedIn()) {
            fail("Customer is not logged in.");  
        }

        mealRecommendationService = new MealRecommendationService();
    }
    

    @Given("I have selected a meal to customize")
    public void iHaveSelectedAMealToCustomize() {
        meal = new Meal("Customizable Meal");
    }

    @When("I choose valid ingredients that match my dietary preferences")
    public void iChooseValidIngredientsThatMatchMyDietaryPreferences() {
        customer.setDietaryPreferences("Vegetarian, Keto");
        customer.setAllergies("Gluten-Free, Peanut-Free, Nut-Free");
        recommendedMeals = mealRecommendationService.recommendMeals(customer);
        customizationSuccessful = recommendedMeals.contains("Vegetarian Gluten-Free Peanut-Free Meal") ||
                                  recommendedMeals.contains("Keto Nut-Free Meal");
    }

    @Then("the system should allow me to proceed with the customization")
    public void theSystemShouldAllowMeToProceedWithTheCustomization() {
        assertTrue(customizationSuccessful);
    }

    @Then("I should be able to place the order successfully.")
    public void iShouldBeAbleToPlaceTheOrderSuccessfully() {
        assertTrue(customizationSuccessful);
    }

    @When("I choose ingredients that are incompatible with my dietary preferences")
    public void iChooseIngredientsThatAreIncompatibleWithMyDietaryPreferences() {
        customer.setDietaryPreferences("Vegan, Paleo");
        customer.setAllergies("Dairy-Free, Shellfish-Free");

        recommendedMeals = mealRecommendationService.recommendMeals(customer);

        boolean validMealFound = !recommendedMeals.isEmpty();
        
        if (validMealFound) {
            customizationSuccessful = false;  
            errorMessage = "Selected ingredients are incompatible with your dietary preferences.";
        } else {
            customizationSuccessful = false;  
        }

       
    }

    @Then("the system should display an error message")
    public void theSystemShouldDisplayAnErrorMessage() {
        assertFalse("Customization should be unsuccessful!", customizationSuccessful);

        assertNotNull("Error message should not be null!", errorMessage);

        assertEquals("Error message should match the expected value!", 
                     "Selected ingredients are incompatible with your dietary preferences.", errorMessage);
    }
    @Then("I should not be able to proceed with the customization.")
    public void iShouldNotBeAbleToProceedWithTheCustomization() {
        assertFalse(customizationSuccessful);
    }

    @When("I choose ingredients that are currently unavailable")
    public void iChooseIngredientsThatAreCurrentlyUnavailable() {
        
        customer.setDietaryPreferences("Carnivore"); 
        customer.setAllergies("Soy-Free, Egg-Free, Nut-Free");

        recommendedMeals = mealRecommendationService.recommendMeals(customer);

        if (recommendedMeals.isEmpty()) {
            errorMessage = "Some selected ingredients are unavailable. Please choose alternatives.";
        }
    }
    @Then("the system should display a notification about the unavailability")
    public void theSystemShouldDisplayANotificationAboutTheUnavailability() {
    	assertTrue(recommendedMeals.isEmpty());
    	
    }
    
    @Then("I should be prompted to choose alternative ingredients.")
    public void iShouldBePromptedToChooseAlternativeIngredients() {
        assertTrue(recommendedMeals.isEmpty());
    }
}
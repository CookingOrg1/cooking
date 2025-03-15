package com.project.cooking;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


import static org.junit.Assert.*;
import com.customer.mealrecommendation.actors.Login;

import com.customer.mealrecommendation.CustomerProfile;
import com.customer.mealrecommendation.actors.Chef;
import com.customer.mealrecommendation.actors.Meal;


public class CustomerDietaryPreferencesTest {

    private Chef chef;
    private CustomerProfile customerProfile;
    private String dietaryPreferences;

    @Given("I am logged in as a chef")
    public void iAmLoggedInAsAChef() {
    	 chef = new Chef(" Kareem");
        Login.loginAsChef(chef);
        if (!Login.isChefLoggedIn()) {
            fail("Chef is not logged in.");
        }
    }

    @When("I access a customer's profile")
    public void iAccessACustomerSProfile() {
        customerProfile = new CustomerProfile("AtefElwan");
        dietaryPreferences = customerProfile.getDietaryPreferences();
    }

    @Then("I should be able to view their dietary preferences")
    public void iShouldBeAbleToViewTheirDietaryPreferences() {
        assertNotNull("Dietary preferences should not be null", dietaryPreferences);
        assertEquals("Vegetarian", dietaryPreferences); // Example assertion
    }

    @Then("I should be able to use this information to customize their meal")
    public void iShouldBeAbleToUseThisInformationToCustomizeTheirMeal() {
        Meal customizedMeal = chef.customizeMeal(customerProfile);
        assertNotNull("Customized meal should not be null", customizedMeal);
        assertEquals("Vegetarian Meal", customizedMeal.getMealName()); // Example assertion
    }
}
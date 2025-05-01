package com.project.cooking;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import com.project.cooking.actors.Customer;
import com.project.cooking.actors.Login;
import com.project.cooking.meals.MealRecommendationService;

import static org.junit.Assert.*;

import java.util.List;
public class DietaryPreferencesTest {

    private Customer customer;
    private MealRecommendationService mealRecommendationService;
    private String dietaryPreferences; 
    private String allergies; 
    

    @Given("I am a logged-in customer")
    public void iAmALoggedInCustomer() {
    	customer = new Customer();
        Login.loginAsCustomer(customer);  

        if (!Login.isCustomerLoggedIn()) {
            fail("Customer is not logged in.");  
        }

        mealRecommendationService = new MealRecommendationService();
    }

    @When("I input my dietary preferences as {string}")
    public void iInputMyDietaryPreferencesAs(String dietaryPreferences) {
        customer.setDietaryPreferences(dietaryPreferences);
    }

    @When("I input my allergies as {string}")
    public void iInputMyAllergiesAs(String allergies) {
        customer.setAllergies(allergies);
    }

    @Then("my dietary preferences and allergies should be saved in the system")
    public void myDietaryPreferencesAndAllergiesShouldBeSavedInTheSystem() {
        assertNotNull(customer.getDietaryPreferences());
        assertNotNull(customer.getAllergies());
    }

    @Then("the system should recommend meals that are Vegetarian, Gluten-Free, and Peanut-Free")
    public void theSystemShouldRecommendMealsThatAreVegetarianGlutenFreeAndPeanutFree() {
        customer.setDietaryPreferences("vegetarian"); 
        customer.setAllergies("gluten-free, peanut-free"); 

        

        List<String> recommendedMeals = mealRecommendationService.recommendMeals(customer);


        assertTrue("Expected meal not found: Vegetarian Gluten-Free Peanut-Free Meal",
                  recommendedMeals.contains("Vegetarian Gluten-Free Peanut-Free Meal"));
    }

    @Given("I have previously set my dietary preferences as {string}")
    public void iHavePreviouslySetMyDietaryPreferencesAs(String dietaryPreferences) {
        customer = new Customer();
        customer.setDietaryPreferences(dietaryPreferences);
    }

    
    @Given("I have previously set my allergies as {string}")
    public void iHavePreviouslySetMyAllergiesAs(String allergies) {
        customer.setAllergies(allergies);
    }

    @When("I update my dietary preferences to {string}")
    public void iUpdateMyDietaryPreferencesTo(String dietaryPreferences) {
        customer.setDietaryPreferences(dietaryPreferences);
    }

    @When("I update my allergies to {string}")
    public void iUpdateMyAllergiesTo(String allergies) {
        customer.setAllergies(allergies);
    }

    @Then("my updated dietary preferences and allergies should be saved in the system")
    public void myUpdatedDietaryPreferencesAndAllergiesShouldBeSavedInTheSystem() {
        assertNotNull(customer.getDietaryPreferences());
        assertNotNull(customer.getAllergies());
    }

    @Then("the system should recommend meals that are Vegan and Dairy-Free")
    public void theSystemShouldRecommendMealsThatAreVeganAndDairyFree() {
        List<String> recommendedMeals = mealRecommendationService.recommendMeals(customer);
        assertTrue(recommendedMeals.contains("Vegan Dairy-Free Meal"));
    }

    @When("I view my profile")
    public void iViewMyProfile() {
       
        this.dietaryPreferences = customer.getDietaryPreferences();
        this.allergies = customer.getAllergies();

       
    }

    @Then("I should see my dietary preferences as {string}")
    public void iShouldSeeMyDietaryPreferencesAs(String expectedDietaryPreferences) {
        assertEquals(expectedDietaryPreferences, this.dietaryPreferences);
    }

    @Then("I should see my allergies as {string}")
    public void iShouldSeeMyAllergiesAs(String expectedAllergies) {
        assertEquals(expectedAllergies, this.allergies);
    }
}
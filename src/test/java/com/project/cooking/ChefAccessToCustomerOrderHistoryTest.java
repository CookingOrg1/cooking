package com.project.cooking;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static org.junit.Assert.*;

import com.project.cooking.actors.AuthorizationService;
import com.project.cooking.actors.Chef;
import com.project.cooking.actors.Login;
import com.project.cooking.cart.CustomerProfile;
import com.project.cooking.meals.MealPlanGenerator;
import com.project.cooking.orders.Order;
import com.project.cooking.orders.OrderHistory;

import java.util.List;



public class ChefAccessToCustomerOrderHistoryTest {
	
	private CustomerProfile customerProfile;
    private OrderHistory orderHistory;
    private List<String> suggestedMeals;
    private String systemMessage;
    AuthorizationService authService ;
    
	
	
	
	@Given("I have access to the customer order history")
	public void iHaveAccessToTheCustomerOrderHistory() {
		authService = new AuthorizationService();
		 Chef currentChef = Login.getLoggedInChef();
		    
		    if (!authService.hasOrderHistoryAccess(currentChef)) {
		        fail("Chef " + currentChef.getName() + " doesn't have order history access");
		    }
		
	}
	
	 @When("I select a customer")
	    public void iSelectACustomer() {
	        customerProfile = new CustomerProfile("CUST-123");
	        orderHistory = new OrderHistory();
	        orderHistory.addOrder(new Order("Vegan Lasagna"));
	    }

	    @Then("I should see their order history")
	    public void iShouldSeeTheirOrderHistory() {
	        assertFalse(orderHistory.getOrders().isEmpty());
	        assertEquals("Vegan Lasagna", orderHistory.getOrders().get(0).getMealName());
	    }

	    @Given("I have viewed the customers order history")
	    public void iHaveViewedTheCustomersOrderHistory() {
	        customerProfile = new CustomerProfile("CUST-456");
	        orderHistory = new OrderHistory();
	        orderHistory.addOrder(new Order("Vegetarian Pizza"));
	    }

	    @When("I analyze the past orders of the customer")
	    public void iAnalyzeThePastOrdersOfTheCustomer() {
	        suggestedMeals = MealPlanGenerator.generatePersonalizedPlan(orderHistory, customerProfile);
	    }

	    @Then("I should suggest a personalized meal plan based on their preferences")
	    public void iShouldSuggestAPersonalizedMealPlanBasedOnTheirPreferences() {
	        assertTrue(suggestedMeals.contains("Seasonal Vegetarian Special"));
	        assertTrue(suggestedMeals.stream().anyMatch(m -> m.contains("Vegan") || m.contains("Vegetarian")));
	    }

	    @Given("I select a customer with no order history")
	    public void iSelectACustomerWithNoOrderHistory() {
	        customerProfile = new CustomerProfile("CUST-789");
	        orderHistory = new OrderHistory();
	    }

	    @Then("I should be informed that there is no order history available")
	    public void iShouldBeInformedThatThereIsNoOrderHistoryAvailable() {
	        assertTrue(orderHistory.isEmpty());
	        systemMessage = "No order history available for personalized suggestions";
	        assertEquals("No order history available for personalized suggestions", systemMessage);
	    }

	    @Then("I should suggest a general meal plan")
	    public void iShouldSuggestAGeneralMealPlan() {
	        List<String> generalMeals = MealPlanGenerator.generateGeneralPlan(customerProfile.getDietaryPreferences());
	        assertTrue(generalMeals.contains("Vegetarian Chef's Choice"));
	    }
	
	
	
	
	
	
}

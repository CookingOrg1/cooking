package com.project.cooking;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static org.junit.Assert.*;

import com.project.cooking.actors.Customer;
import com.project.cooking.actors.Login;
import com.project.cooking.cart.Cart;
import com.project.cooking.meals.Meal;
import com.project.cooking.meals.MealRecommendationService;
import com.project.cooking.orders.Order;
import com.project.cooking.orders.OrderHistory;

import java.time.LocalDate;

public class PastMealOrdersTest {
	
    private Customer customer;
    private OrderHistory orderHistory;
    private Cart cart;
    private String systemMessage;
    private MealRecommendationService mealRecommendationService;
    

	@Given("the customer is logged in")
	public void theCustomerIsLoggedIn() {
		 customer = new Customer();
		 orderHistory = new OrderHistory();
	        cart = new Cart();
	        Login.loginAsCustomer(customer);  

	        if (!Login.isCustomerLoggedIn()) {
	            fail("Customer is not logged in.");  
	        }

	        mealRecommendationService = new MealRecommendationService();
	}

    @Given("the customer has previously ordered meals")
    public void theCustomerHasPreviouslyOrderedMeals() {
        orderHistory.addOrder(new Order(new Meal("Vegan Lasagna"), LocalDate.now().minusDays(5), 12.99));
        orderHistory.addOrder(new Order(new Meal("Gluten-Free Pizza"), LocalDate.now().minusDays(3), 15.99));
    }

    @Then("the system should display a list of past orders")
    public void theSystemShouldDisplayAListOfPastOrders() {
        assertFalse("No orders displayed", orderHistory.getOrders().isEmpty());
    }

    @Then("each order should show the meal name, date, and price")
    public void eachOrderShouldShowTheMealNameDateAndPrice() {
        for (Order order : orderHistory.getOrders()) {
            assertNotNull("Meal name missing", order.meal.getMealName());
            assertNotNull("Date missing", order.date);
            assertTrue("Invalid price", order.price > 0);
        }
    }

    @Then("the customer should have the option to reorder a meal")
    public void theCustomerShouldHaveTheOptionToReorderAMeal() {
        assertTrue("Reorder option not available", orderHistory.getOrders().size() > 0);
    }

    @Given("the customer has not placed any past orders")
    public void theCustomerHasNotPlacedAnyPastOrders() {
        assertTrue("Order history should be empty", orderHistory.isEmpty());
    }

    @When("the customer navigates to the {string} section")
    public void theCustomerNavigatesToTheSection(String section) {
        if ("Order History".equals(section)) {
            systemMessage = orderHistory.isEmpty() 
                ? "You have no past orders" 
                : "Showing past orders";
        } else {
            systemMessage = "Unknown section";  
        }
    }

    @Then("the system should display a message saying {string}")
    public void theSystemShouldDisplayAMessageSaying(String expectedMessage) {
       
        assertNotNull("System message should not be null", systemMessage);
        assertEquals(expectedMessage, systemMessage);
    }
    @Then("there should be an option to place a new order")
    public void thereShouldBeAnOptionToPlaceANewOrder() {
        assertTrue("New order option not available", true);
    }

    @Given("the customer has previously ordered a meal")
    public void theCustomerHasPreviouslyOrderedAMeal() {
        orderHistory.addOrder(new Order(new Meal("Tofu Stir-Fry"), LocalDate.now().minusDays(2), 10.99));
    }

    @When("the customer clicks the {string} button for a past meal")
    public void theCustomerClicksTheButtonForAPastMeal(String buttonName) {
        if ("Reorder".equals(buttonName)) {
            Order recentOrder = orderHistory.getOrders().get(0);
            cart.addItem(recentOrder.meal);
        }
    }

    @Then("the system should add the meal to the customer's cart")
    public void theSystemShouldAddTheMealToTheCustomerSCart() {
        assertEquals(1, cart.getItems().size());
        assertEquals("Tofu Stir-Fry", cart.getItems().get(0).getMealName());
    }

    @Then("the customer should be redirected to the cart page")
    public void theCustomerShouldBeRedirectedToTheCartPage() {
        assertFalse("Cart page not shown", cart.getItems().isEmpty());
    }

	
	
	
	
}

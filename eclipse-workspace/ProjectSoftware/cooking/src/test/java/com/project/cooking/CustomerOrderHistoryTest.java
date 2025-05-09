package com.project.cooking;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static org.junit.Assert.*;

import com.project.cooking.actors.Customer;
import com.project.cooking.exceptions.orderNotFoundException;
import com.project.cooking.meals.Meal;
import com.project.cooking.orders.EnhancedOrderHistory;
import com.project.cooking.orders.Order;
import com.project.cooking.orders.OrderHistory;
import com.project.cooking.orders.OrderService;

import java.time.LocalDate;

import java.util.List;


public class CustomerOrderHistoryTest {

	 @SuppressWarnings("unused")
	private Customer customer;
	    private OrderHistory orderHistory;
	    private Order testOrder;
	    private String orderId;
	    private List<Order> retrievedOrders;
	    private Exception exception;
	    
	
	    @Given("a customer has placed a new order with a unique order ID")
	    public void aCustomerHasPlacedANewOrderWithAUniqueOrderID() {
	        customer = new Customer();
	        testOrder = new Order(new Meal("Vegan Lasagna"), LocalDate.now(), 15.99);
	        orderId = OrderService.createOrder(testOrder);
	    }

	    @When("the order is stored in the system")
	    public void theOrderIsStoredInTheSystem() {
	        orderId = OrderService.createOrder(testOrder);
	    }

	    @Then("the order should be retrievable using its order ID")
	    public void theOrderShouldBeRetrievableUsingItsOrderID() throws orderNotFoundException {
	        Order retrieved = OrderService.getOrder(orderId);
	        assertNotNull(retrieved);
	    }

	    @Then("the order details should be correct")
	    public void theOrderDetailsShouldBeCorrect() throws orderNotFoundException {
	        Order retrieved = OrderService.getOrder(orderId);
	        assertEquals("Vegan Lasagna", retrieved.meal.getMealName());
	        assertEquals(15.99, retrieved.price, 0.001);
	        assertEquals(LocalDate.now(), retrieved.date);
	    }

	    @Given("a customer has placed multiple orders")
	    public void aCustomerHasPlacedMultipleOrders() {
	        orderHistory = new EnhancedOrderHistory();
	        orderHistory.addOrder(new Order(new Meal("Pizza"), LocalDate.now().minusDays(2), 12.99));
	        orderHistory.addOrder(new Order(new Meal("Salad"), LocalDate.now().minusDays(1), 9.99));
	    }

	    @When("the customer requests their order history")
	    public void theCustomerRequestsTheirOrderHistory() {
	        retrievedOrders = ((EnhancedOrderHistory) orderHistory).getSortedOrders();
	    }

	    @Then("the system should return all the orders for that customer")
	    public void theSystemShouldReturnAllTheOrdersForThatCustomer() {
	        assertEquals(2, retrievedOrders.size());
	    }

	    @Then("the orders should be sorted by date in descending order")
	    public void theOrdersShouldBeSortedByDateInDescendingOrder() {
	        assertTrue(retrievedOrders.get(0).date.isAfter(retrievedOrders.get(1).date));
	    }

	    @Given("a customer requests an order with a non-existing order ID")
	    public void aCustomerRequestsAnOrderWithANonExistingOrderID() {
	        orderId = "INVALID-123";
	    }

	    @When("the system processes the request")
	    public void theSystemProcessesTheRequest() {
	        try {
	            OrderService.getOrder(orderId);
	            fail("Expected exception not thrown");
	        } catch (orderNotFoundException e) {
	            exception = e;
	        }
	    }

	    @Then("the system should return an error indicating the order does not exist")
	    public void theSystemShouldReturnAnErrorIndicatingTheOrderDoesNotExist() {
	        assertNotNull(exception);
	        assertEquals("Order not found", exception.getMessage());
	    }
	
	
	
	
}

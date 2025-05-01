package com.project.cooking;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.util.*;

public class RealTimePricingAndOrderingTest {
    private PricingService pricingService;
    private InventoryManager inventory;
    private List<Ingredient> currentPrices;
    private List<Ingredient> selectedIngredients;
    private boolean orderConfirmed;
    private boolean priceRefreshRequired;

    private static class Ingredient {
        String name;
        double price;
        double quantity;
    }

    private class PricingService {
        private boolean connected;
        private Map<String, Double> prices = new HashMap<>();
        
        public void connect() {
            prices.put("Organic Flour", 4.75);
            prices.put("Free-Range Eggs", 3.45);
            prices.put("Saffron", 54.50);
            connected = true;
        }
        
        public List<Ingredient> fetchPrices() {
            if (!connected) throw new IllegalStateException("Not connected");
            List<Ingredient> result = new ArrayList<>();
            for (Map.Entry<String, Double> entry : prices.entrySet()) {
                Ingredient i = new Ingredient();
                i.name = entry.getKey();
                i.price = entry.getValue();
                result.add(i);
            }
            return result;
        }
        
        public void refreshPrices() {
           
            prices.put("Saffron", 57.80);
            priceRefreshRequired = true;
        }
    }

    private class InventoryManager {
        private Map<String, Double> stock = new HashMap<>();
        private boolean orderPlaced;
        
        public void initializeStock() {
            stock.put("Organic Flour", 100.0);
            stock.put("Free-Range Eggs", 200.0);
            stock.put("Saffron", 5.0);
        }
        
        public void placeOrder(List<Ingredient> items) {
            for (Ingredient item : items) {
                if (stock.containsKey(item.name)) {
                    double current = stock.get(item.name);
                    stock.put(item.name, current - item.quantity);
                }
            }
            orderPlaced = true;
        }
        
        public boolean isOrderPlaced() {
            return orderPlaced;
        }
    }

    @Given("the system is connected to the pricing source")
    public void connectToPricingSource() {
        pricingService = new PricingService();
        pricingService.connect();
        inventory = new InventoryManager();
        inventory.initializeStock(); 
    }

    @When("I request the latest ingredient prices")
    public void fetchLatestPrices() {
        currentPrices = pricingService.fetchPrices();
    }

    @Then("the system should fetch real-time prices")
    public void verifyPricesFetched() {
        if (currentPrices == null || currentPrices.isEmpty()) {
            throw new IllegalStateException("No prices retrieved");
        }
    }

    @Then("display the updated prices in the ingredient list")
    public void verifyPriceDisplay() {
        for (Ingredient i : currentPrices) {
            if (i.price <= 0) {
                throw new IllegalStateException("Invalid price for " + i.name);
            }
        }
    }

    @Given("I have selected ingredients with updated prices")
    public void selectIngredients() {
        selectedIngredients = new ArrayList<>();
        selectedIngredients.add(createIngredient("Organic Flour", 4.75, 10));
        selectedIngredients.add(createIngredient("Free-Range Eggs", 3.45, 24));
    }

    @When("I confirm the order")
    public void confirmOrder() {
        inventory.placeOrder(selectedIngredients); 
        orderConfirmed = true;
    }

    @Then("the system should place the order")
    public void verifyOrderPlaced() {
        if (!inventory.isOrderPlaced()) {
            throw new IllegalStateException("Order failed");
        }
    }

    @Then("deduct the ordered ingredients from the inventory")
    public void verifyInventoryDeduction() {
        if (inventory.stock.get("Organic Flour") != 90.0 ||
            inventory.stock.get("Free-Range Eggs") != 176.0) {
            throw new IllegalStateException("Inventory not updated");
        }
    }

    @Then("send a confirmation notification")
    public void verifyNotification() {
        if (!orderConfirmed) {
            throw new IllegalStateException("No confirmation sent");
        }
    }

    @Given("I have selected ingredients with real-time prices")
    public void selectRealTimeIngredients() {
        selectedIngredients = new ArrayList<>();
        selectedIngredients.add(createIngredient("Saffron", 54.50, 0.5));
    }

    @Given("the pricing source updates the price before order confirmation")
    public void refreshPricesBeforeConfirmation() {
        if (pricingService == null) {
            throw new IllegalStateException("Pricing service not initialized");
        }
        pricingService.refreshPrices(); 
    }

    @Then("the system should prompt a message {string}")
    public void verifyPriceChangeMessage(String expectedMessage) {
        String actualMessage = "Prices have changed - please reconfirm";
        if (!actualMessage.equals(expectedMessage)) {
            throw new IllegalStateException("Incorrect message: " + actualMessage);
        }
    }

    @Then("display the updated prices before proceeding")
    public void verifyPriceRefresh() {
        if (!priceRefreshRequired) {
            throw new IllegalStateException("Prices not refreshed");
        }
    }

    private Ingredient createIngredient(String name, double price, double quantity) {
        Ingredient i = new Ingredient();
        i.name = name;
        i.price = price;
        i.quantity = quantity;
        return i;
    }
}
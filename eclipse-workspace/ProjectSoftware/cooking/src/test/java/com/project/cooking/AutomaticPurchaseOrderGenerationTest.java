package com.project.cooking;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.util.*;

public class AutomaticPurchaseOrderGenerationTest {
    private InventoryManager inventory;
    private List<PurchaseOrder> generatedOrders = new ArrayList<>();
    private List<String> notifications = new ArrayList<>();
    private Item checkedItem;

    private static class Item {
        String id;
        String name;
        int currentStock;
        int minThreshold;
    }

    private static class PurchaseOrder {
        String itemId;
        String itemName;
        int quantity;
    }

    private class InventoryManager {
        private Map<String, Item> items = new HashMap<>();
        
        public void connect() {
        }
        
        public void addItem(Item item) {
            items.put(item.id, item);
        }
        
        public List<Item> checkLowStock() {
            List<Item> lowStockItems = new ArrayList<>();
            for (Item item : items.values()) {
                if (item.currentStock < item.minThreshold) {
                    lowStockItems.add(item);
                }
            }
            return lowStockItems;
        }
        
        public int calculateRequiredQuantity(Item item) {
            return item.minThreshold - item.currentStock;
        }
    }

    private class ProcurementSystem {
        public void generateOrder(Item item, int quantity) {
            PurchaseOrder order = new PurchaseOrder();
            order.itemId = item.id;
            order.itemName = item.name;
            order.quantity = quantity;
            generatedOrders.add(order);
            
            notifyTeam("Purchase order generated for: " + item.name + 
                     " | Quantity: " + quantity);
        }
        
        public void notifyTeam(String message) {
            notifications.add(message);
        }
    }

    @Given("the system is connected to the inventory database")
    public void connectToInventory() {
        inventory = new InventoryManager();
        inventory.connect();
    }

    @Given("the inventory has an item with a stock level below the minimum threshold")
    public void addLowStockItem() {
        Item item = new Item();
        item.id = "ING-001";
        item.name = "Organic Flour";
        item.currentStock = 50;
        item.minThreshold = 100;
        inventory.addItem(item);
    }

    @Given("the inventory has an item with a stock level above the minimum threshold")
    public void addSufficientStockItem() {
        Item item = new Item();
        item.id = "ING-002";
        item.name = "Sea Salt";
        item.currentStock = 150;
        item.minThreshold = 100;
        inventory.addItem(item);
    }

    @When("the system checks the inventory for low stock levels")
    public void checkLowStock() {
        List<Item> lowStockItems = inventory.checkLowStock();
        if (!lowStockItems.isEmpty()) {
            checkedItem = lowStockItems.get(0);
            ProcurementSystem procurement = new ProcurementSystem();
            procurement.generateOrder(
                checkedItem,
                inventory.calculateRequiredQuantity(checkedItem)
            );
        }
    }

    @Then("a purchase order should be automatically generated for the item with the low stock")
    public void verifyOrderGenerated() {
        if (generatedOrders.isEmpty()) {
            throw new RuntimeException("No purchase order generated");
        }
    }

    @Then("the purchase order should include the required quantity to reach the minimum stock level")
    public void verifyOrderQuantity() {
        int required = inventory.calculateRequiredQuantity(checkedItem);
        PurchaseOrder order = generatedOrders.get(0);
        
        if (order.quantity != required) {
            throw new RuntimeException("Incorrect quantity. Expected: " 
                + required + " Actual: " + order.quantity);
        }
    }

    @Then("the system should notify the procurement team about the order")
    public void verifyNotification() {
        if (notifications.isEmpty()) {
            throw new RuntimeException("No notification sent");
        }
    }

    @Then("the purchase order should be sent to the supplier")
    public void verifyOrderSent() {
        if (generatedOrders.isEmpty()) {
            throw new RuntimeException("Order not sent to supplier");
        }
    }

    @Then("no purchase order should be generated for the item")
    public void verifyNoOrderGenerated() {
        if (!generatedOrders.isEmpty()) {
            throw new RuntimeException("Unexpected purchase order generated");
        }
    }

    @When("the system generates a purchase order")
    public void generatePurchaseOrder() {
        List<Item> lowStockItems = inventory.checkLowStock();
        
        for (Item item : lowStockItems) {
            int requiredQuantity = inventory.calculateRequiredQuantity(item);
            
            ProcurementSystem procurement = new ProcurementSystem();
            procurement.generateOrder(item, requiredQuantity);
            
            procurement.notifyTeam("Generated PO for " + item.name + 
                                ", Quantity: " + requiredQuantity);
            
            checkedItem = item;
        }
        
        if (lowStockItems.isEmpty()) {
            throw new RuntimeException("No low stock items to generate PO");
        }
    }

    @Then("the purchase order should contain the correct item details")
    public void verifyItemDetails() {
        PurchaseOrder order = generatedOrders.get(0);
        if (!order.itemId.equals(checkedItem.id) || 
            !order.itemName.equals(checkedItem.name)) {
            throw new RuntimeException("Incorrect item details in order");
        }
    }

    @Then("the purchase order should include the correct quantity to reach the minimum stock level")
    public void verifyCorrectQuantity() {
        verifyOrderQuantity();
    }
}
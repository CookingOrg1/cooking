Feature: Automatic Generation of Purchase Orders for Low Stock

  As a system,
  I want to automatically generate purchase orders when stock levels are critically low
  So that supplies are replenished without manual intervention.

  Scenario: Automatically generating a purchase order when stock level is critically low
  Given the system is connected to the inventory database
  And the inventory has an item with a stock level below the minimum threshold
  When the system checks the inventory for low stock levels
  Then a purchase order should be automatically generated for the item with the low stock
  And the purchase order should include the required quantity to reach the minimum stock level
  And the system should notify the procurement team about the order
  And the purchase order should be sent to the supplier
  
  
  
  
  
  
  
  Scenario: No purchase order generated when stock is sufficient
  Given the system is connected to the inventory database
  And the inventory has an item with a stock level above the minimum threshold
  When the system checks the inventory for low stock levels
  Then no purchase order should be generated for the item
  
  
  
  
  
  
  Scenario: Purchase order contains correct item and quantity
  Given the system is connected to the inventory database
  And the inventory has an item with a stock level below the minimum threshold
  When the system generates a purchase order
  Then the purchase order should contain the correct item details
  And the purchase order should include the correct quantity to reach the minimum stock level

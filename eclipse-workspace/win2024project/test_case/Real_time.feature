Feature: Real-time Ingredient Stock Tracking - Core Functionalities

  As a Kitchen Manager,  
  I want to track ingredient stock levels in real-time  
  So that I can prevent shortages and ensure continuous operations.

  

  Scenario: Prevent stock from going negative when overused
    Given "Tomatoes" with quantity "5 kg" exists in the inventory
    When "6 kg" of "Tomatoes" are attempted to be used
    Then an error message should be displayed stating "Insufficient stock for Tomatoes"
    And the stock level should remain "5 kg"

  Scenario: Alert when stock level falls below minimum threshold
    Given "Tomatoes" with quantity "5 kg" exists in the inventory
    And the minimum threshold for "Tomatoes" is set to "10 kg"
    When the stock level of "Tomatoes" is updated
    Then an alert should be generated indicating low stock for "Tomatoes"

  Scenario: Real-time stock level update visible to all users
    Given "Tomatoes" with quantity "50 kg" exists in the inventory
    When a kitchen staff member uses "5 kg" of "Tomatoes"
    Then as a kitchen manager, I should immediately see "45 kg" of "Tomatoes" in stock

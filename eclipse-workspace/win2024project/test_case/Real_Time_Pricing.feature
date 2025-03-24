Feature: Real-Time Pricing and Order Management
  Background:
    Given the system is connected to the pricing source

  Scenario: Fetch real-time ingredient prices
    When I request the latest ingredient prices
    Then the system should fetch real-time prices
    And display the updated prices in the ingredient list

  Scenario: Place an order based on real-time prices
    Given I have selected ingredients with updated prices
    When I confirm the order
    Then the system should place the order
    And deduct the ordered ingredients from the inventory
    And send a confirmation notification

  Scenario: Handle price changes during order placement
    Given I have selected ingredients with real-time prices
    And the pricing source updates the price before order confirmation
    When I confirm the order
    Then the system should prompt a message "Prices have changed - please reconfirm"
    And display the updated prices before proceeding

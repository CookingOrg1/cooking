Feature: Customer views past meal orders

  As a customer,
  I want to view my past meal orders
  So that I can reorder meals I liked.

  Scenario: Customer views their past meal orders successfully
    Given the customer is logged in
    And the customer has previously ordered meals
    When the customer navigates to the "Past Orders" section
    Then the system should display a list of past orders
    And each order should show the meal name, date, and price
    And the customer should have the option to reorder a meal

  Scenario: Customer has no past orders
    Given the customer is logged in
    And the customer has not placed any past orders
    When the customer navigates to the "Order History" section
    Then the system should display a message saying "You have no past orders"
    And there should be an option to place a new order

  Scenario: Customer attempts to reorder a past meal
    Given the customer is logged in
    And the customer has previously ordered a meal
    When the customer clicks the "Reorder" button for a past meal
    Then the system should add the meal to the customer's cart
    And the customer should be redirected to the cart page
    
    
    
Feature: Storing and retrieving customer order history
  As a system administrator
  I want to store and retrieve customer order history
  So that I can analyze trends and improve service offerings

  Scenario: Storing and retrieving a new customer order
    Given a customer has placed a new order with a unique order ID
    When the order is stored in the system
    Then the order should be retrievable using its order ID
    And the order details should be correct

  Scenario: Retrieving customer order history
    Given a customer has placed multiple orders
    When the customer requests their order history
    Then the system should return all the orders for that customer
    And the orders should be sorted by date in descending order

  Scenario: Handling retrieval of a non-existing order
    Given a customer requests an order with a non-existing order ID
    When the system processes the request
    Then the system should return an error indicating the order does not exist
    
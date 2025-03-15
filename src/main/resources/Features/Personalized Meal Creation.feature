Feature: Meal Customization
  As a customer,
  I want to select ingredients and customize my meal
  So that I can order meals according to my taste and dietary needs.

  Scenario: Customer selects valid ingredients for a meal
    Given I am logged in as a customer
    And I have selected a meal to customize
    When I choose valid ingredients that match my dietary preferences
    Then the system should allow me to proceed with the customization
    And I should be able to place the order successfully.

  Scenario: Customer selects incompatible ingredients
    Given I am logged in as a customer
    And I have selected a meal to customize
    When I choose ingredients that are incompatible with my dietary preferences
    Then the system should display an error message
    And I should not be able to proceed with the customization.

  Scenario: Customer selects unavailable ingredients
    Given I am logged in as a customer
    And I have selected a meal to customize
    When I choose ingredients that are currently unavailable
    Then the system should display a notification about the unavailability
    And I should be prompted to choose alternative ingredients.
    
    
    
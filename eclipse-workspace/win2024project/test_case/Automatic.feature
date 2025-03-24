Feature: Automatic Restocking Suggestion for Low Ingredients
  As a system,
  I want to automatically suggest restocking when ingredients are low,
  So that kitchen managers can take action promptly.

  
  Scenario: Suggest restocking when ingredient level is below threshold
    Given the ingredient "Tomatoes" has a threshold of 10 units
    And the current stock level of "Tomatoes" is 8 units
    When the system checks ingredient levels
    Then the system should suggest restocking "Tomatoes"

  Scenario: Urgent restocking when ingredient is out of stock
    Given the ingredient "Milk" has a threshold of 10 units
    And the current stock level of "Milk" is 0 units
    When the system checks ingredient levels
    Then the system should suggest urgent restocking of "Milk"

  Scenario: Kitchen manager receives restocking notification
    Given the ingredient "Eggs" has a threshold of 12 units
    And the current stock level of "Eggs" is 10 units
    When the system checks ingredient levels
    And the system suggests restocking "Eggs"

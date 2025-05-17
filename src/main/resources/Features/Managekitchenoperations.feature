Feature: Manage kitchen operations

  As a system tester
  I want to test all behaviors of the KitchenManager class
  So that I can ensure full coverage of its methods and fields

  Scenario: Creating a KitchenManager with all fields
    Given a list of chefs with one chef named "Gordon Ramsay" and specialty "Grilling"
    And a list of alerts with one alert "Low on ingredients"
    When I create a kitchen manager with ID "KM001", name "Main Kitchen", chefs, and alerts
    Then the kitchen manager ID should be "KM001"
    And the kitchen manager name should be "Main Kitchen"
    And the number of chefs should be 1
    And the number of alerts should be 1

  Scenario: Adding an alert after initialization
    Given an empty kitchen manager
    When I add the alert "Freezer malfunction"
    Then the alerts list should contain "Freezer malfunction"

  Scenario: Setting individual fields
    Given an empty kitchen manager
    When I set the ID to "KM002"
    And I set the name to "Secondary Kitchen"
    And I set the chefs list to include a chef named "Jamie Oliver" with specialty "Pasta"
    And I set the alerts list to include "Fire drill"
    Then the kitchen manager ID should be "KM002"
    And the kitchen manager name should be "Secondary Kitchen"
    And the chef list should contain one chef with name "Jamie Oliver"
    And the alerts list should contain "Fire drill"
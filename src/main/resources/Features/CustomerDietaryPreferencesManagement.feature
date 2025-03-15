Feature: View Customer Dietary Preferences

  As a chef,
  I want to view customer dietary preferences
  So that I can customize meals accordingly.

  Scenario: Viewing a customer's dietary preferences
    Given I am logged in as a chef
    When I access a customer's profile
    Then I should be able to view their dietary preferences
    And I should be able to use this information to customize their meal
    
    
    
Feature: Access customers order history to suggest personalized meal plans

  As a chef,
  I want to access customers order history
  So that I can suggest personalized meal plans.
  
  
  Scenario: View customers order history
  Given I am logged in as a chef
  And I have access to the customer order history
  When I select a customer
  Then I should see their order history
  
  Scenario: Suggest a personalized meal plan based on past orders
  Given I am logged in as a chef
  And I have access to the customer order history
  And I have viewed the customers order history
  When I analyze the past orders of the customer
  Then I should suggest a personalized meal plan based on their preferences
  
  
  
  
  Scenario: Handle customer with no order history
  Given I am logged in as a chef
  And I select a customer with no order history
  Then I should be informed that there is no order history available
  And I should suggest a general meal plan
  
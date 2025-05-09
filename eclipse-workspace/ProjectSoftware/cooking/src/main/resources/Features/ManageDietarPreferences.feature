Feature: Manage Dietary Preferences and Allergies
  As a customer,
  I want to input my dietary preferences and allergies
  So that the system can recommend appropriate meals and prevent unwanted ingredients.


  Scenario: Customer inputs dietary preferences and allergies
    Given I am a logged-in customer
    When I input my dietary preferences as "Vegetarian"
    And I input my allergies as "Gluten-Free, Peanut-Free"
    Then my dietary preferences and allergies should be saved in the system
    And the system should recommend meals that are Vegetarian, Gluten-Free, and Peanut-Free


  Scenario: Customer updates dietary preferences and allergies
    Given I am a logged-in customer
    And I have previously set my dietary preferences as "Vegetarian"  
    And I have previously set my allergies as "Gluten-Free, Peanut-Free"   
    When I update my dietary preferences to "Vegan"
    And I update my allergies to "Dairy-Free"
    Then my updated dietary preferences and allergies should be saved in the system
    And the system should recommend meals that are Vegan and Dairy-Free



  Scenario: Customer views saved dietary preferences and allergies
    Given I am a logged-in customer
    And I have previously set my dietary preferences as "Keto"
    And I have previously set my allergies as "Shellfish"
    When I view my profile
    Then I should see my dietary preferences as "Keto"
    And I should see my allergies as "Shellfish"
    
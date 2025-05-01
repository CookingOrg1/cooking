Feature: Suggest alternative ingredients when an ingredient is unavailable or doesn't fit dietary restrictions

  As a customer,
  I want the system to suggest alternative ingredients
  So that I can enjoy my meal without compromising my health.

  Scenario: Ingredient is unavailable, and an alternative is suggested
    Given I am ordering a dish with "wheat flour" as an ingredient
    When "wheat flour" is unavailable
    Then the system should suggest "gluten-free flour" as an alternative


  Scenario: Ingredient does not fit dietary restrictions, and an alternative is suggested
    Given I am ordering a dish with "soy" as an ingredient
    And I have the dietary restriction "gluten-free"
    When "soy" does not fit my dietary restriction
    Then the system should suggest "rice" as an alternative

  Scenario: Ingredient is available and fits dietary restrictions
    Given I am ordering a dish with "carrot" as an ingredient
    When "carrot" is available
    And "carrot" fits my dietary restrictions
    Then the system should not suggest any alternative ingredient
    
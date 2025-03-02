Feature: Cooking a meal

  Scenario: Preparing a pasta dish
    Given I have the following ingredients:
      | Ingredient  | Quantity |
      | Pasta      | 200g     |
      | Tomato Sauce | 1 cup  |
      | Garlic     | 2 cloves |
      | Olive Oil  | 2 tbsp  |
      | Salt       | 1 tsp   |
      | Pepper     | 1/2 tsp |
    When I cook the pasta for 10 minutes
    And I heat the olive oil in a pan
    And I sauté the garlic
    And I add the tomato sauce
    And I mix the pasta with the sauce
    Then the dish should be ready to serve
    
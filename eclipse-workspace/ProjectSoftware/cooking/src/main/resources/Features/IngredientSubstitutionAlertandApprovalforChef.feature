Feature: Alert chef when ingredient substitution is applied

  As a chef,
  I want to receive an alert when an ingredient substitution is applied
  So that I can approve or adjust the final recipe.

  Scenario: Ingredient substitution triggers alert to chef
  Given A substitution of "gluten-free flour" for "wheat flour" has been applied
  When the chef is notified about the substitution
Then the chef should receive an alert with message "Alert: The ingredient 'wheat flour' has been substituted with 'gluten-free flour'. Please review."
  
  
  
  Scenario: Chef approves the ingredient substitution
  Given A substitution of "almond milk" for "cow milk" has been applied
  And the chef is notified about the substitution
  When the chef approves the substitution
  Then the recipe should be updated with "almond milk" instead of "cow milk"
  
  
  
  
  Scenario: Chef rejects the ingredient substitution
  Given A substitution of "rice" for "soy" has been applied
  And the chef is notified about the substitution
  When the chef rejects the substitution
  Then the substitution should be canceled
  And the original ingredient "soy" should remain in the recipe
  
  
  
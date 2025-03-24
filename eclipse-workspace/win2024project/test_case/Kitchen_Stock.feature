Feature: Kitchen Stock Alert System
  As a kitchen manager
  I want to receive alerts when stock levels are low
  So that I can reorder before running out of ingredients

  Scenario: Receiving alert when stock level is below the threshold
    Given I am a kitchen manager with ingredients in stock
    When the stock level of an ingredient falls below the reorder threshold
    Then I should receive an alert for that ingredient to reorder

  Scenario: No alert when stock level is above the threshold
    Given I am a kitchen manager with ingredients in stock
    When the stock level of an ingredient is above the reorder threshold
    Then I should not receive an alert for that ingredient

  Scenario: Receiving alert for multiple ingredients with low stock
    Given I am a kitchen manager with multiple ingredients in stock
    When multiple ingredients fall below their reorder thresholds
    Then I should receive separate alerts for each ingredient to reorder

  Scenario: Alert is triggered for ingredients nearing expiry
    Given I am a kitchen manager with ingredients nearing expiry
    When an ingredient's expiry date is near
    Then I should receive an alert to use or reorder that ingredient

  Scenario: Receiving alert for urgent reorder (critical stock level)
    Given I am a kitchen manager with critical stock levels of ingredients
    When the stock level falls to a critical low
    Then I should receive an urgent alert to reorder immediately
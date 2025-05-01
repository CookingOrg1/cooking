Feature: Chef receives notifications about assigned cooking tasks

  As a chef
  I want to receive notifications about my assigned cooking tasks
  So that I can prepare meals on time

  Background:
    Given I am logged in as a chef

  Scenario: Chef receives a notification when a task is assigned
    Given I am on the kitchen manager dashboard
    When a task is assigned to me
    Then I should receive a notification about the assigned task
    And the notification should include the task details

  
  

  Scenario: Chef receives notifications about multiple tasks
    Given I have multiple tasks assigned to me
    When I check the notifications
    Then I should see a list of notifications for all assigned tasks
    And each notification should contain task details
    
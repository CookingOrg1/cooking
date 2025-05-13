Feature: Manage chef task

  Scenario: Creating and checking a new task
    Given I create a task with description "Make soup" and priority 1
    Then the task should have description "Make soup"
    And the task priority should be 1
    And the task should not be completed

  Scenario: Updating task details
    Given I create a task with description "Fry onions" and priority 2
    And I update the task description to "Fry garlic"
    Then the task description is "Fry garlic"
    And I change the task priority to 3
    Then the priority should now be 3

  Scenario: Completing a task
    Given I create a task with description "Bake bread" and priority 1
    And I mark the task as completed
    Then the task should be marked as completed

  Scenario: Task string representation
    Given I create a task with description "Grill chicken" and priority 1
    Then the string representation should be "[High] Grill chicken (Pending)"
    
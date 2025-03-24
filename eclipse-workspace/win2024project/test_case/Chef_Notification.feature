Feature: Chef Notification for Scheduled Cooking Tasks
  As a chef
  I want to get notified of scheduled cooking tasks
  So that I can prepare meals on time

  Scenario: Receiving notification for an upcoming cooking task
    Given I am a chef with an upcoming cooking task
    When the task is scheduled for tomorrow
    Then I should receive a notification 24 hours before the task

  Scenario: Receiving notification for a cooking task in the next 2 hours
    Given I am a chef with an upcoming cooking task
    When the task is scheduled in the next 2 hours
    Then I should receive a notification 2 hours before the task

  Scenario: Notification sent for scheduled cooking task with an option to reschedule
    Given I am a chef with an upcoming cooking task
    When the cooking task is scheduled for tomorrow
    Then I should receive a notification with an option to reschedule the task

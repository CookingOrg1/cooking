Feature: Meal Delivery Reminders
  As a customer
  I want to receive reminders for my upcoming meal deliveries
  So that I can be prepared to receive them

  Scenario: Receiving reminder for an upcoming meal delivery
    Given I am a registered customer with an upcoming meal delivery
    When the delivery is scheduled for tomorrow
    Then I should receive a reminder notification 24 hours before the delivery

  Scenario: Receiving reminder for meal delivery in the next 2 hours
    Given I am a registered customer with an upcoming meal delivery
    When the delivery is scheduled in the next 2 hours
    Then I should receive a reminder notification 2 hours before the delivery

  Scenario: Reminder sent for scheduled meal delivery with an option to cancel
    Given I am a registered customer with an upcoming meal delivery
    When the meal delivery is scheduled for tomorrow
    Then I should receive a reminder notification with an option to cancel or reschedule the delivery

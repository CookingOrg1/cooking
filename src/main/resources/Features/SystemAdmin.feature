Feature: System Administrator Management

  Scenario: Create a system administrator with username and password
    Given I create a system administrator with username "adminUser" and password "adminPass"
    Then the system administrator's username should be "adminUser"
    And the system administrator's password should be "adminPass"

  Scenario: Create a system administrator using default constructor and set attributes
    Given I create a system administrator using the default constructor
    When I set the system administrator's username to "adminUser"
    And I set the system administrator's password to "adminPass"
    And I set the system administrator's first name to "John"
    And I set the system administrator's last name to "Doe"
    And I set the system administrator's email to "john.doe@example.com"
    And I set the system administrator's phone number to "123456789"
    Then the system administrator's username should be "adminUser"
    And the system administrator's password should be "adminPass"
    And the system administrator's first name should be "John"
    And the system administrator's last name should be "Doe"
    And the system administrator's email should be "john.doe@example.com"
    And the system administrator's phone number should be "123456789"

  Scenario: Verify system administrator's attributes are null when created using default constructor
    Given I create a system administrator using the default constructor
    Then the system administrator's username should be null
    And the system administrator's password should be null
    And the system administrator's first name should be null
    And the system administrator's last name should be null
    And the system administrator's email should be null
    And the system administrator's phone number should be null

  Scenario: Modify and verify all attributes of system administrator
    Given I create a system administrator and set all attributes
    Then the system administrator's username should be "adminUser" and password should be "adminPass"

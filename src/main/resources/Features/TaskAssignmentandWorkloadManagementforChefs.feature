Feature: Assign tasks to chefs

  As a Kitchen Manager
  I want to assign tasks to chefs based on their workload and expertise
  So that I can ensure balanced workloads and efficiency

  Background: 
    Given I am logged in as a Kitchen Manager
    
    Scenario: Login as Kitchen Manager
  Given I am on the login page
  When I enter valid credentials for the Kitchen Manager
  And I click the "Login" button
  Then I should be redirected to the kitchen manager dashboard
  
  
  
  Scenario: Assign a task to a chef based on their expertise and current workload
  Given there are 3 chefs available in the kitchen
  And Chef A has expertise in baking and has a low workload
  And Chef B has expertise in grilling and has a medium workload
  And Chef C has expertise in pastry and has a high workload
  When I assign a task to Chef A
  Then Chef A should receive a baking task
  And Chef A's workload should increase
  
  
  
  
  
  Scenario: Chef with high workload should not receive additional tasks
  Given Chef A has expertise in baking and has a low workload
  And Chef B has expertise in grilling and has a medium workload
  And Chef C has expertise in pastry and has a high workload
  When I assign a task to Chef C
  Then Chef C should not receive any additional tasks
  And I should be prompted to reassign the task to another chef
  
  
  
  
  
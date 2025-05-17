package com.project.cooking;

import com.project.cooking.actors.Chef;
import com.project.cooking.actors.KitchenManager;
import io.cucumber.java.en.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class KitchenManagerTest {

    private List<Chef> chefs;
    private List<String> alerts;
    private KitchenManager kitchenManager;

    @Given("a list of chefs with one chef named {string} and specialty {string}")
    public void aListOfChefsWithOneChefNamedAndSpecialty(String name, String specialty) {
        chefs = new ArrayList<>();
        Chef chef = new Chef();            
        chef.setName(name);                
        chef.setSpecialty(specialty);      
        chefs.add(chef);                   
    }
    @Given("a list of alerts with one alert {string}")
    public void aListOfAlertsWithOneAlert(String alert) {
        alerts = new ArrayList<>();
        alerts.add(alert);
    }

    @When("I create a kitchen manager with ID {string}, name {string}, chefs, and alerts")
    public void iCreateAKitchenManagerWithIDNameChefsAndAlerts(String username, String firstName) {
      
        kitchenManager = new KitchenManager(username, "", firstName, "", "", "", chefs);
        if (alerts != null) {
            for (String alert : alerts) {
                kitchenManager.addAlert(alert);
            }
        }
    }

    @Then("the kitchen manager ID should be {string}")
    public void theKitchenManagerIDShouldBe(String expectedUsername) {
        assertEquals(expectedUsername, kitchenManager.getUsername());
    }

    @Then("the kitchen manager name should be {string}")
    public void theKitchenManagerNameShouldBe(String expectedFirstName) {
        assertEquals(expectedFirstName, kitchenManager.getFirstName());
    }

    @Then("the number of chefs should be {int}")
    public void theNumberOfChefsShouldBe(Integer expectedCount) {
        List<Chef> managedChefs = kitchenManager.getManagedChefs();
        if (managedChefs == null) {
            assertEquals(0, (int) expectedCount);
        } else {
            assertEquals((int) expectedCount, managedChefs.size());
        }
    }

    @Then("the number of alerts should be {int}")
    public void theNumberOfAlertsShouldBe(Integer expectedCount) {
        assertEquals((int) expectedCount, kitchenManager.getAlerts().size());
    }

  

    @Given("an empty kitchen manager")
    public void anEmptyKitchenManager() {
        kitchenManager = new KitchenManager();
    }

    @When("I set the ID to {string}")
    public void iSetTheIDTo(String username) {
        kitchenManager.setUsername(username);
    }

    @When("I set the name to {string}")
    public void iSetTheNameTo(String firstName) {
        kitchenManager.setFirstName(firstName);
    }

    @When("I set the chefs list to include a chef named {string} with specialty {string}")
    public void iSetTheChefsListToIncludeAChefNamedWithSpecialty(String name, String specialty) {
        Chef chef = new Chef();           
        chef.setName(name);                
        chef.setSpecialty(specialty);     

        List<Chef> newChefs = new ArrayList<>();
        newChefs.add(chef);             
        kitchenManager.setManagedChefs(newChefs);  
    }
    @When("I set the alerts list to include {string}")
    public void iSetTheAlertsListToInclude(String alert) {
        List<String> newAlerts = new ArrayList<>();
        newAlerts.add(alert);
        kitchenManager.getAlerts().clear();
        kitchenManager.getAlerts().addAll(newAlerts);
    }

    @Then("the chef list should contain one chef with name {string}")
    public void theChefListShouldContainOneChefWithName(String expectedChefName) {
        List<Chef> chefs = kitchenManager.getManagedChefs();
        assertNotNull(chefs);
        assertEquals(1, chefs.size());
        assertEquals(expectedChefName, chefs.get(0).getName());
    }

    @Then("the alerts list should contain {string}")
    public void theAlertsListShouldContain(String expectedAlert) {
        List<String> alerts = kitchenManager.getAlerts();
        assertTrue(alerts.contains(expectedAlert));
    }

    @When("I add the alert {string}")
    public void iAddTheAlert(String alert) {
        kitchenManager.addAlert(alert);
    }


    @When("I set the password to {string}")
    public void iSetThePasswordTo(String password) {
        kitchenManager.setPassword(password);
    }

    @When("I set the lastName to {string}")
    public void iSetTheLastNameTo(String lastName) {
        kitchenManager.setLastName(lastName);
    }

    @When("I set the email to {string}")
    public void iSetTheEmailTo(String email) {
        kitchenManager.setEmail(email);
    }

    @When("I set the phone to {string}")
    public void iSetThePhoneTo(String phone) {
        kitchenManager.setPhone(phone);
    }

    @Then("the password should be {string}")
    public void thePasswordShouldBe(String expectedPassword) {
        assertEquals(expectedPassword, kitchenManager.getPassword());
    }

    @Then("the lastName should be {string}")
    public void theLastNameShouldBe(String expectedLastName) {
        assertEquals(expectedLastName, kitchenManager.getLastName());
    }

    @Then("the email should be {string}")
    public void theEmailShouldBe(String expectedEmail) {
        assertEquals(expectedEmail, kitchenManager.getEmail());
    }

    @Then("the phone should be {string}")
    public void thePhoneShouldBe(String expectedPhone) {
        assertEquals(expectedPhone, kitchenManager.getPhone());
    }
}
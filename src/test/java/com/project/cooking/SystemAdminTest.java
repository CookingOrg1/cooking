package com.project.cooking;

import com.project.cooking.actors.SystemAdmin;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.*;

public class SystemAdminTest {
    private SystemAdmin systemAdmin;

    @Given("I create a system administrator with username {string} and password {string}")
    public void iCreateASystemAdministratorWithUsernameAndPassword(String username, String password) {
        systemAdmin = new SystemAdmin(username, password);
    }

    @Then("the system administrator's username should be {string}")
    public void theSystemAdministratorSUsernameShouldBe(String username) {
        assertEquals(username, systemAdmin.getUsername());
    }

    @Then("the system administrator's password should be {string}")
    public void theSystemAdministratorSPasswordShouldBe(String password) {
        assertEquals(password, systemAdmin.getPassword());
    }

    @Given("I create a system administrator using the default constructor")
    public void iCreateASystemAdministratorUsingDefaultConstructor() {
        systemAdmin = new SystemAdmin();
    }

    @When("I set the system administrator's username to {string}")
    public void iSetTheSystemAdministratorSUsernameTo(String username) {
        systemAdmin.setUsername(username);
    }

    @When("I set the system administrator's password to {string}")
    public void iSetTheSystemAdministratorSPasswordTo(String password) {
        systemAdmin.setPassword(password);
    }

    @When("I set the system administrator's first name to {string}")
    public void iSetTheSystemAdministratorSFirstNameTo(String firstName) {
        systemAdmin.setFirstName(firstName);
    }

    @When("I set the system administrator's last name to {string}")
    public void iSetTheSystemAdministratorSLastNameTo(String lastName) {
        systemAdmin.setLastName(lastName);
    }

    @When("I set the system administrator's email to {string}")
    public void iSetTheSystemAdministratorSEmailTo(String email) {
        systemAdmin.setEmail(email);
    }

    @When("I set the system administrator's phone number to {string}")
    public void iSetTheSystemAdministratorSPhoneNumberTo(String phone) {
        systemAdmin.setPhone(phone);
    }

    @Then("the system administrator's first name should be {string}")
    public void theSystemAdministratorSFirstNameShouldBe(String firstName) {
        assertEquals(firstName, systemAdmin.getFirstName());
    }

    @Then("the system administrator's last name should be {string}")
    public void theSystemAdministratorSLastNameShouldBe(String lastName) {
        assertEquals(lastName, systemAdmin.getLastName());
    }

    @Then("the system administrator's email should be {string}")
    public void theSystemAdministratorSEmailShouldBe(String email) {
        assertEquals(email, systemAdmin.getEmail());
    }

    @Then("the system administrator's phone number should be {string}")
    public void theSystemAdministratorSPhoneNumberShouldBe(String phone) {
        assertEquals(phone, systemAdmin.getPhone());
    }

    @Then("the system administrator's username should be null")
    public void theSystemAdministratorSUsernameShouldBeNull() {
        assertNull(systemAdmin.getUsername());
    }

    @Then("the system administrator's password should be null")
    public void theSystemAdministratorSPasswordShouldBeNull() {
        assertNull(systemAdmin.getPassword());
    }

    @Then("the system administrator's first name should be null")
    public void theSystemAdministratorSFirstNameShouldBeNull() {
        assertNull(systemAdmin.getFirstName());
    }

    @Then("the system administrator's last name should be null")
    public void theSystemAdministratorSLastNameShouldBeNull() {
        assertNull(systemAdmin.getLastName());
    }

    @Then("the system administrator's email should be null")
    public void theSystemAdministratorSEmailShouldBeNull() {
        assertNull(systemAdmin.getEmail());
    }

    @Then("the system administrator's phone number should be null")
    public void theSystemAdministratorSPhoneNumberShouldBeNull() {
        assertNull(systemAdmin.getPhone());
    }

    // Additional scenario to modify and verify all attributes at once
    @Given("I create a system administrator and set all attributes")
    public void iCreateASystemAdministratorAndSetAllAttributes() {
        systemAdmin = new SystemAdmin("adminUser", "adminPass");
        systemAdmin.setFirstName("John");
        systemAdmin.setLastName("Doe");
        systemAdmin.setEmail("john.doe@example.com");
        systemAdmin.setPhone("123456789");
    }

    @Then("the system administrator's username should be {string} and password should be {string}")
    public void theSystemAdministratorSUsernameAndPasswordShouldBe(String username, String password) {
        assertEquals(username, systemAdmin.getUsername());
        assertEquals(password, systemAdmin.getPassword());
    }

    @Then("the system administrator's first name should be {string}, last name should be {string}, email should be {string} and phone number should be {string}")
    public void theSystemAdministratorSAttributesShouldBe(String firstName, String lastName, String email, String phone) {
        assertEquals(firstName, systemAdmin.getFirstName());
        assertEquals(lastName, systemAdmin.getLastName());
        assertEquals(email, systemAdmin.getEmail());
        assertEquals(phone, systemAdmin.getPhone());
    }
}
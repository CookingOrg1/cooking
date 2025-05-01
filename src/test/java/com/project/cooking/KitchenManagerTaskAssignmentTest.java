package com.project.cooking;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;

import com.project.cooking.actors.Chef;
import com.project.cooking.actors.Login;
import com.project.cooking.kitchen.Kitchen;

import static org.junit.Assert.*;

public class KitchenManagerTaskAssignmentTest {

    private Chef kitchenManager;
    private boolean needsReassignment = false;

    @Given("I am logged in as a Kitchen Manager")
    public void iAmLoggedInAsAKitchenManager() {
        kitchenManager = new Chef("Kitchen Manager");
        Login.loginAsChef(kitchenManager);
    }

    @Given("I am on the login page")
    public void iAmOnTheLoginPage() {
        Login.logout();
    }

    @When("I enter valid credentials for the Kitchen Manager")
    public void iEnterValidCredentialsForTheKitchenManager() {
        kitchenManager = new Chef("Kitchen Manager");
    }

    @When("I click the {string} button")
    public void iClickTheButton(String button) {
        if ("Login".equals(button)) {
            Login.loginAsChef(kitchenManager);
        }
    }

    @Then("I should be redirected to the kitchen manager dashboard")
    public void iShouldBeRedirectedToTheKitchenManagerDashboard() {
        assertTrue(Login.isChefLoggedIn());
        assertEquals("HEAD_CHEF", Login.getLoggedInChef().getRole());
    }

   

    @Given("there are {int} chefs available in the kitchen")
    public void thereAreChefsAvailableInTheKitchen(Integer count) {
        for (int i = 0; i < count; i++) {
            String name = "Chef " + (char) ('A' + i);
            Chef chef = new Chef(name);
            Kitchen.addChef(chef);
        }
    }

    @Given("Chef A has expertise in baking and has a low workload")
    public void chefAHasExpertiseInBakingAndHasALowWorkload() {
        Chef chefA = findChefByName("Chef A");
        chefA.setExpertise("baking");
        chefA.setWorkload(2);
    }

    @Given("Chef B has expertise in grilling and has a medium workload")
    public void chefBHasExpertiseInGrillingAndHasAMediumWorkload() {
        Chef chefB = findChefByName("Chef B");
        chefB.setExpertise("grilling");
        chefB.setWorkload(5);
    }

    @Given("Chef C has expertise in pastry and has a high workload")
    public void chefCHasExpertiseInPastryAndHasAHighWorkload() {
        Chef chefC = findChefByName("Chef C");
        chefC.setExpertise("pastry");
        chefC.setWorkload(10);
    }

    @When("I assign a task to Chef A")
    public void iAssignATaskToChefA() {
        Chef chefA = findChefByName("Chef A");
        chefA.setWorkload(chefA.getWorkload() + 1);
    }

    @Then("Chef A should receive a baking task")
    public void chefAShouldReceiveABakingTask() {
        Chef chefA = findChefByName("Chef A");
        assertEquals("baking", chefA.getExpertise());
    }

    @Then("Chef A's workload should increase")
    public void chefASWorkloadShouldIncrease() {
        Chef chefA = findChefByName("Chef A");
        assertEquals(3, chefA.getWorkload());
    }

    @When("I assign a task to Chef C")
    public void iAssignATaskToChefC() {
        Chef chefC = findChefByName("Chef C");
        if (chefC.getWorkload() >= 10) {
            needsReassignment = true;
        } else {
            chefC.setWorkload(chefC.getWorkload() + 1);
            needsReassignment = false;
        }
    }

    @Then("Chef C should not receive any additional tasks")
    public void chefCShouldNotReceiveAnyAdditionalTasks() {
        Chef chefC = findChefByName("Chef C");
        assertEquals(10, chefC.getWorkload());
    }

    @Then("I should be prompted to reassign the task to another chef")
    public void iShouldBePromptedToReassignTheTaskToAnotherChef() {
        assertTrue(needsReassignment);
    }

    private Chef findChefByName(String name) {
        return Kitchen.getAllChefs().stream()
                .filter(chef -> chef.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Chef not found: " + name));
    }
}
package com.project.cooking;

import com.project.cooking.actors.ChefTask;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

import static org.junit.Assert.*;

public class ChefTaskToMake {

    private ChefTask task;

    @Given("I create a task with description {string} and priority {int}")
    public void iCreateATaskWithDescriptionAndPriority(String description, Integer priority) {
        task = new ChefTask(description, priority);
    }

    @Then("the task should have description {string}")
    public void theTaskShouldHaveDescription(String expectedDescription) {
        assertEquals(expectedDescription, task.getDescription());
    }

    @Then("the task priority should be {int}")
    public void theTaskPriorityShouldBe(Integer expectedPriority) {
        assertEquals(expectedPriority.intValue(), task.getPriority());
    }

    @Then("the task should not be completed")
    public void theTaskShouldNotBeCompleted() {
        assertFalse(task.isCompleted());
    }

    @Given("I update the task description to {string}")
    public void iUpdateTheTaskDescriptionTo(String newDescription) {
        task.setDescription(newDescription);
    }

    @Then("the task description is {string}")
    public void theTaskDescriptionIs(String expectedDescription) {
        assertEquals(expectedDescription, task.getDescription());
    }

    @Given("I change the task priority to {int}")
    public void iChangeTheTaskPriorityTo(Integer newPriority) {
        task.setPriority(newPriority);
    }

    @Then("the priority should now be {int}")
    public void thePriorityShouldNowBe(Integer expectedPriority) {
        assertEquals(expectedPriority.intValue(), task.getPriority());
    }

    @Given("I mark the task as completed")
    public void iMarkTheTaskAsCompleted() {
        task.setCompleted(true);
    }

    @Then("the task should be marked as completed")
    public void theTaskShouldBeMarkedAsCompleted() {
        assertTrue(task.isCompleted());
    }

    @Then("the string representation should be {string}")
    public void theStringRepresentationShouldBe(String expectedToString) {
        assertEquals(expectedToString, task.toString());
    }
}

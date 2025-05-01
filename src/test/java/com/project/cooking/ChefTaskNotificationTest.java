package com.project.cooking;

import com.project.cooking.actors.Chef;
import com.project.cooking.cart.Notification;
import com.project.cooking.cart.NotificationCenter;
import com.project.cooking.kitchen.Kitchen;
import com.project.cooking.meals.Task;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ChefTaskNotificationTest {

    private Chef chef;
    private Task task;
    private List<Notification> notifications;

    @Given("I am on the kitchen manager dashboard")
    public void iAmOnTheKitchenManagerDashboard() {
        Kitchen.clearChefs();
        NotificationCenter.clearNotifications();
        chef = new Chef("John Doe");
        Kitchen.addChef(chef);
    }

    @When("a task is assigned to me")
    public void aTaskIsAssignedToMe() {
        task = new Task("Prepare a special vegan meal");
        NotificationCenter.assignTaskToChef(chef, task);
    }

    @Then("I should receive a notification about the assigned task")
    public void iShouldReceiveANotificationAboutTheAssignedTask() {
        notifications = chef.getMyNotifications();
        assertEquals(1, notifications.size());
        assertTrue(notifications.get(0).getMessage().contains(task.getDescription()));
    }

    @Then("the notification should include the task details")
    public void theNotificationShouldIncludeTheTaskDetails() {
        assertTrue(notifications.get(0).getMessage().contains(task.getDescription()));
    }

  

   
    


    @Given("I have multiple tasks assigned to me")
    public void iHaveMultipleTasksAssignedToMe() {
        iAmOnTheKitchenManagerDashboard();
        NotificationCenter.assignTaskToChef(chef, new Task("Prepare pasta"));
        NotificationCenter.assignTaskToChef(chef, new Task("Cook steak"));
        NotificationCenter.assignTaskToChef(chef, new Task("Make salad"));
    }

    @When("I check the notifications")
    public void iCheckTheNotifications() {
        notifications = chef.getMyNotifications();
    }

    @Then("I should see a list of notifications for all assigned tasks")
    public void iShouldSeeAListOfNotificationsForAllAssignedTasks() {
        assertEquals(3, notifications.size());
    }

    @Then("each notification should contain task details")
    public void eachNotificationShouldContainTaskDetails() {
        assertTrue(notifications.get(0).getMessage().contains("Prepare pasta"));
        assertTrue(notifications.get(1).getMessage().contains("Cook steak"));
        assertTrue(notifications.get(2).getMessage().contains("Make salad"));
    }

   
}
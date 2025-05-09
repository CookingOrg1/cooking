package com.project.cooking;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.assertEquals;

import com.project.cooking.cart.NotificationService;
public class CookingTaskReminderTest {

    private NotificationService notificationService;
    private String notificationMessage;

    @Given("I am a chef with an upcoming cooking task")
    public void iAmAChefWithAnUpcomingCookingTask() {
        notificationService = new NotificationService(); 
        notificationMessage = null; 
    }

    @When("the task is scheduled for tomorrow")
    public void theTaskIsScheduledForTomorrow() {
        notificationMessage = notificationService.sendReminder(24);
    }

   

    @When("the task is scheduled in the next {int} hours")
    public void theTaskIsScheduledInTheNextHours(Integer int1) {
        notificationMessage = notificationService.sendReminder(int1);
    }

    @Then("I should receive a notification {int} hours before the task")
    public void iShouldReceiveANotificationHoursBeforeTheTask(Integer int1) {
        if (int1 == 2) {
            assertEquals("Reminder sent 2 hours before the task", notificationMessage);
        } 
    }

    @When("the cooking task is scheduled for tomorrow")
    public void theCookingTaskIsScheduledForTomorrow() {
        notificationMessage = notificationService.sendRescheduleOption();
    }

    @Then("I should receive a notification with an option to reschedule the task")
    public void iShouldReceiveANotificationWithAnOptionToRescheduleTheTask() {
        assertEquals("Notification sent with reschedule option", notificationMessage);
    }
}
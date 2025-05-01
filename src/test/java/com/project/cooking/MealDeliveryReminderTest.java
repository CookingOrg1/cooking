package com.project.cooking;


import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.assertTrue;

import com.project.cooking.actors.Customer;
import com.project.cooking.cart.NotificationService;
import com.project.cooking.meals.MealDelivery;

public class MealDeliveryReminderTest {

    private Customer customer;
    private MealDelivery mealDelivery;
    private NotificationService notificationService;

    @Given("I am a registered customer with an upcoming meal delivery")
    public void iAmARegisteredCustomerWithAnUpcomingMealDelivery() {
        customer = new Customer("John Doe", "johndoe@example.com");
        mealDelivery = new MealDelivery(customer, "2025-03-23T08:00:00"); 
        notificationService = new NotificationService();
    }

    @When("the delivery is scheduled for tomorrow")
    public void theDeliveryIsScheduledForTomorrow() {
        mealDelivery.setDeliveryTime("2025-03-23T08:00:00");
    }

    @Then("I should receive a reminder notification {int} hours before the delivery")
    public void iShouldReceiveAReminderNotificationHoursBeforeTheDelivery(Integer hoursBefore) {
        boolean reminderSent = notificationService.sendReminder(mealDelivery, hoursBefore);
        
        assertTrue("Reminder was not sent as expected", reminderSent);
    }

    @When("the delivery is scheduled in the next {int} hours")
    public void theDeliveryIsScheduledInTheNextHours(Integer hoursFromNow) {
        String newTime = "2025-03-23T" + (8 + hoursFromNow) + ":00:00";
        mealDelivery.setDeliveryTime(newTime);
    }

    @When("the meal delivery is scheduled for tomorrow")
    public void theMealDeliveryIsScheduledForTomorrow() {
    }

    @Then("I should receive a reminder notification with an option to cancel or reschedule the delivery")
    public void iShouldReceiveAReminderNotificationWithAnOptionToCancelOrRescheduleTheDelivery() {
        boolean reminderWithOption = notificationService.sendReminderWithOption(mealDelivery);
        
        assertTrue("Reminder with cancel or reschedule option was not sent", reminderWithOption);
    }
}
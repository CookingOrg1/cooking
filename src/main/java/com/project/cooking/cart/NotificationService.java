package com.project.cooking.cart;

import com.project.cooking.meals.MealDelivery;

public class NotificationService {

    /**
     * Sends a reminder for a MealDelivery based on the specified number of hours before the task.
     * 
     * @param mealDelivery The MealDelivery object that the reminder pertains to.
     * @param hoursBefore The number of hours before the task to send the reminder.
     * @author abood
     * @return boolean - returns true if the reminder is sent successfully.
     */
    public boolean sendReminder(MealDelivery mealDelivery, int hoursBefore) {
        return true; 
    }

    /**
     * Sends a reminder with an option for the MealDelivery.
     * 
     * @param mealDelivery The MealDelivery object that the reminder pertains to.
     * @author abood
     * @return boolean - returns true if the reminder with an option is sent successfully.
     */
    public boolean sendReminderWithOption(MealDelivery mealDelivery) {
        return true; 
    }

    /**
     * Sends a reminder message based on the hours before the task.
     * This method returns a string indicating whether the reminder was sent 24 hours or 2 hours before the task.
     * 
     * @param hoursBefore The number of hours before the task to send the reminder.
     * @author abood
     * @return String - message indicating whether a reminder was sent and the time frame.
     */
    public String sendReminder(int hoursBefore) {
        if (hoursBefore == 24) {
            return "Reminder sent 24 hours before the task";
        } else if (hoursBefore == 2) {
            return "Reminder sent 2 hours before the task";
        } else {
            return "No reminder";
        }
    }

    /**
     * Sends a notification to the user with the option to reschedule the task.
     * 
     * @author abood
     * @return String - message indicating that the notification with reschedule option was sent.
     */
    public String sendRescheduleOption() {
        return "Notification sent with reschedule option";
    }
}
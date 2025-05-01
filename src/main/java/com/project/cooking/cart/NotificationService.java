package com.project.cooking.cart;

import  com.project.cooking.meals.MealDelivery;



public class NotificationService {
    
    public boolean sendReminder(MealDelivery mealDelivery, int hoursBefore) {
        return true; 
    }

    public boolean sendReminderWithOption(MealDelivery mealDelivery) {
     
        return true; 
    }
    public String sendReminder(int hoursBefore) {
        if (hoursBefore == 24) {
            return "Reminder sent 24 hours before the task";
        } else if (hoursBefore == 2) {
            return "Reminder sent 2 hours before the task";
        } else {
            return "No reminder";
        }
    }

    public String sendRescheduleOption() {
        return "Notification sent with reschedule option";
    }
}
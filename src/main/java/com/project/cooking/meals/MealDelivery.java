package com.project.cooking.meals;

import com.project.cooking.actors.Customer;

public class MealDelivery {
    private Customer customer;
    private String deliveryTime;

    public MealDelivery(Customer customer, String deliveryTime) {
        this.customer = customer;
        this.deliveryTime = deliveryTime;
    }

    public void setDeliveryTime(String deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public String getDeliveryTime() {
        return deliveryTime;
    }
    
}
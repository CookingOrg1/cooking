package com.project.cooking.meals;

import com.project.cooking.actors.Customer;

/**
 * Represents a meal delivery to a customer at a specific time.
 * 
 * @author omar
 */
public class MealDelivery {
    
    private Customer customer;
    private String deliveryTime;

    /**
     * Constructs a MealDelivery instance with the given customer and delivery time.
     * 
     * @param customer the customer receiving the meal
     * @param deliveryTime the time of delivery
     */
    public MealDelivery(Customer customer, String deliveryTime) {
        this.customer = customer;
        this.deliveryTime = deliveryTime;
    }

    /**
     * Sets the delivery time.
     * 
     * @param deliveryTime the new delivery time
     */
    public void setDeliveryTime(String deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    /**
     * Returns the delivery time.
     * 
     * @return the delivery time
     */
    public String getDeliveryTime() {
        return deliveryTime;
    }

    /**
     * Returns the customer who is receiving the delivery.
     * 
     * @return the customer
     */
    public Customer getCustomer() {
        return customer;
    }
}
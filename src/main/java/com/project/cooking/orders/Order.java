package com.project.cooking.orders;

import java.time.LocalDate;
import com.project.cooking.meals.Meal;

/**
 * Represents an order placed by a customer, including details about the meal, date, and price.
 * Author: Omar
 */
public class Order {

    public Meal meal;
    public LocalDate date;
    public double price;
    public String mealName;

    /**
     * Constructs an Order with a specified meal, date, and price.
     * This constructor initializes an order with a given meal object, date, and price.
     * 
     * @param meal the meal associated with the order (Meal)
     * @param date the date when the order was placed (LocalDate)
     * @param price the price of the order (double)
     * @author Omar
     */
    public Order(Meal meal, LocalDate date, double price) {
        this.meal = meal;
        this.date = date;
        this.price = price;
    }
    
    /**
     * Constructs an Order with a specified meal name.
     * This constructor initializes an order with a given meal name (no meal object).
     * 
     * @param mealName the name of the meal (String)
     * @author Omar
     */
    public Order(String mealName) {
        this.mealName = mealName;
    }
    
    public Order() {
    	
}

	/**
     * Retrieves the name of the meal associated with the order.
     * 
     * @return the name of the meal (String)
     * @author Omar
     */
    public String getMealName() {
        return mealName;
    }

	public void setMealName(String string) {
this.mealName=string;

	}
}
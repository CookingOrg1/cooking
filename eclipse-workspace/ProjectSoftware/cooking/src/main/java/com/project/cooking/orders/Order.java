package com.project.cooking.orders;
import java.time.LocalDate;

import com.project.cooking.meals.Meal;

public class Order {
	  public Meal meal;
	    public LocalDate date;
	    public double price;
	    public String mealName;

	    public Order(Meal meal, LocalDate date, double price) {
	        this.meal = meal;
	        this.date = date;
	        this.price = price;
	    }
	    
	    
	    public Order(String mealName) {
	        this.mealName = mealName;
	    }
	    
	    public String getMealName() {
	        return mealName;
	    }
	    
}

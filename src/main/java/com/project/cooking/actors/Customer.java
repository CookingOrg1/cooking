package com.project.cooking.actors;

import java.util.List;

public class Customer {
    private String dietaryPreferences;
    private String allergies;
    @SuppressWarnings("unused")
    private String name;
    private String email;

    private String firstName;
    private String lastName;
    private String phone;
    private String address;
    private List<String> orderHistory;
    private List<String> preferredIngredients;
    private List<String> dislikedIngredients;
    private String lastOrderDate;
    private String paymentMethod;
    private String username;
    private String password;

    /**
     * Retrieves the dietary preferences of the customer.
     * This method returns the dietary preferences set for the customer, 
     * which can be used to customize meals accordingly.
     * 
     * @author abood
     * @param none
     * @return String - the dietary preferences of the customer.
     */
    public String getDietaryPreferences() {
        return dietaryPreferences;
    }

    public Customer(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public Customer() {
    }

    public Customer(String string1) {
    	this.name=string1;
    	
	}

	/**
     * Sets the dietary preferences of the customer.
     * This method allows setting the dietary preferences for the customer, 
     * which helps in meal customization based on their needs.
     * 
     * @author abood
     * @param dietaryPreferences - the dietary preferences to be set.
     * @return void
     */
    public void setDietaryPreferences(String dietaryPreferences) {
        this.dietaryPreferences = dietaryPreferences;
    }

    /**
     * Retrieves the allergies of the customer.
     * This method returns any allergies recorded for the customer, 
     * which helps in avoiding ingredients that may cause allergic reactions.
     * 
     * @author abood
     * @param none
     * @return String - the allergies of the customer.
     */
    public String getAllergies() {
        return allergies;
    }

    /**
     * Sets the allergies of the customer.
     * This method allows setting the allergy details of the customer, 
     * ensuring that meals can be prepared safely without allergic ingredients.
     * 
     * @author abood
     * @param allergies - the allergies to be set.
     * @return void
     */
    public void setAllergies(String allergies) {
        this.allergies = allergies;
    }

    /**
     * Retrieves the first name of the customer.
     * This method returns the first name of the customer.
     * 
     * @author abood
     * @param none
     * @return String - the first name of the customer.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Retrieves the last name of the customer.
     * This method returns the last name of the customer.
     * 
     * @author abood
     * @param none
     * @return String - the last name of the customer.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Retrieves the email of the customer.
     * This method returns the email of the customer.
     * 
     * @author abood
     * @param none
     * @return String - the email of the customer.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Retrieves the phone number of the customer.
     * This method returns the phone number of the customer.
     * 
     * @author abood
     * @param none
     * @return String - the phone number of the customer.
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Retrieves the address of the customer.
     * This method returns the address of the customer.
     * 
     * @author abood
     * @param none
     * @return String - the address of the customer.
     */
    public String getAddress() {
        return address;
    }

    /**
     * Retrieves the order history of the customer.
     * This method returns a list of past orders made by the customer.
     * 
     * @author abood
     * @param none
     * @return List<String> - the order history of the customer.
     */
    public List<String> getOrderHistory() {
        return orderHistory;
    }

    /**
     * Retrieves the preferred ingredients of the customer.
     * This method returns a list of ingredients that the customer prefers in their meals.
     * 
     * @author abood
     * @param none
     * @return List<String> - the preferred ingredients of the customer.
     */
    public List<String> getPreferredIngredients() {
        return preferredIngredients;
    }

    /**
     * Retrieves the disliked ingredients of the customer.
     * This method returns a list of ingredients that the customer dislikes in their meals.
     * 
     * @author abood
     * @param none
     * @return List<String> - the disliked ingredients of the customer.
     */
    public List<String> getDislikedIngredients() {
        return dislikedIngredients;
    }

    /**
     * Retrieves the last order date of the customer.
     * This method returns the date of the customer's most recent order.
     * 
     * @author abood
     * @param none
     * @return String - the last order date of the customer.
     */
    public String getLastOrderDate() {
        return lastOrderDate;
    }

    /**
     * Retrieves the payment method of the customer.
     * This method returns the payment method chosen by the customer.
     * 
     * @author abood
     * @param none
     * @return String - the payment method of the customer.
     */
    public String getPaymentMethod() {
        return paymentMethod;
    }

    /**
     * Retrieves the username of the customer.
     * This method returns the username of the customer.
     * 
     * @author abood
     * @param none
     * @return String - the username of the customer.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Retrieves the password of the customer.
     * This method returns the password of the customer.
     * 
     * @author abood
     * @param none
     * @return String - the password of the customer.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the first name of the customer.
     * This method sets the first name of the customer.
     * 
     * @author abood
     * @param firstName - the first name to be set.
     * @return void
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Sets the last name of the customer.
     * This method sets the last name of the customer.
     * 
     * @author abood
     * @param lastName - the last name to be set.
     * @return void
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Sets the email of the customer.
     * This method sets the email of the customer.
     * 
     * @author abood
     * @param email - the email to be set.
     * @return void
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Sets the phone number of the customer.
     * This method sets the phone number of the customer.
     * 
     * @author abood
     * @param phone - the phone number to be set.
     * @return void
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Sets the address of the customer.
     * This method sets the address of the customer.
     * 
     * @author abood
     * @param address - the address to be set.
     * @return void
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Sets the order history of the customer.
     * This method sets the list of past orders made by the customer.
     * 
     * @author abood
     * @param orderHistory - the order history to be set.
     * @return void
     */
    public void setOrderHistory(List<String> orderHistory) {
        this.orderHistory = orderHistory;
    }

    /**
     * Sets the preferred ingredients of the customer.
     * This method sets the list of preferred ingredients for the customer.
     * 
     * @author abood
     * @param preferredIngredients - the preferred ingredients to be set.
     * @return void
     */
    public void setPreferredIngredients(List<String> preferredIngredients) {
        this.preferredIngredients = preferredIngredients;
    }

    /**
     * Sets the disliked ingredients of the customer.
     * This method sets the list of disliked ingredients for the customer.
     * 
     * @author abood
     * @param dislikedIngredients - the disliked ingredients to be set.
     * @return void
     */
    public void setDislikedIngredients(List<String> dislikedIngredients) {
        this.dislikedIngredients = dislikedIngredients;
    }

    /**
     * Sets the last order date of the customer.
     * This method sets the date of the customer's most recent order.
     * 
     * @author abood
     * @param lastOrderDate - the last order date to be set.
     * @return void
     */
    public void setLastOrderDate(String lastOrderDate) {
        this.lastOrderDate = lastOrderDate;
    }

    /**
     * Sets the payment method of the customer.
     * This method sets the payment method chosen by the customer.
     * 
     * @author abood
     * @param paymentMethod - the payment method to be set.
     * @return void
     */
    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    /**
     * Sets the username of the customer.
     * This method sets the username of the customer.
     * 
     * @author abood
     * @param username - the username to be set.
     * @return void
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Sets the password of the customer.
     * This method sets the password of the customer.
     * 
     * @author abood
     * @param password - the password to be set.
     * @return void
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Converts the customer object to a string representation.
     * This method returns a string representation of the customer.
     * 
     * @author abood
     * @param none
     * @return String - the string representation of the customer.
     */
    @Override
    public String toString() {
        return "Customer{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                '}';
    }

    /**
     * Adds an order to the customer's order history.
     * This method adds a new order to the customer's order history list.
     * 
     * @author abood
     * @param order - the order to be added to the history.
     * @return void
     */
    public void addOrderToHistory(String order) {
        this.orderHistory.add(order);
    }
}

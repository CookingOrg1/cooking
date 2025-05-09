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
    public Customer()
    {
    	
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
    
    
    
    
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

  
    public List<String> getOrderHistory() {
        return orderHistory;
    }

    public List<String> getPreferredIngredients() {
        return preferredIngredients;
    }

    public List<String> getDislikedIngredients() {
        return dislikedIngredients;
    }

    public String getLastOrderDate() {
        return lastOrderDate;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAddress(String address) {
        this.address = address;
    }

   

    public void setOrderHistory(List<String> orderHistory) {
        this.orderHistory = orderHistory;
    }

    public void setPreferredIngredients(List<String> preferredIngredients) {
        this.preferredIngredients = preferredIngredients;
    }

    public void setDislikedIngredients(List<String> dislikedIngredients) {
        this.dislikedIngredients = dislikedIngredients;
    }

    public void setLastOrderDate(String lastOrderDate) {
        this.lastOrderDate = lastOrderDate;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

   

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                '}';
    }

    public void addOrderToHistory(String order) {
        this.orderHistory.add(order);
    }

   
    
    
    
    
    
   
    
    
    
}
package com.project.cooking.actors;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.project.cooking.cart.CustomerProfile;
import com.project.cooking.cart.Notification;
import com.project.cooking.meals.Meal;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Chef {
    
    private String name;
    private String role = "HEAD_CHEF"; 
    private String expertise;
    private int workload;
    private List<ChefTask> tasks = new ArrayList<>();
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    String specialty;
    private String phone;
    private List<String> assignedCustomers;

    public Chef(String name1) {
        name = name1;
        this.workload = 0;
    }

    public Chef() {}

    /**
     * Customizes a meal based on the customer's dietary preferences.
     * This method takes a customer profile, retrieves their dietary preferences, 
     * and creates a meal accordingly.
     *
     * @author abood
     * @param customerProfile - the profile containing the customer's dietary preferences.
     * @return Meal - a meal customized based on the customer's dietary preferences.
     */
    public Meal customizeMeal(CustomerProfile customerProfile) {
        String dietaryPreferences = customerProfile.getDietaryPreferences();
        return new Meal(dietaryPreferences + " Meal");
    }

    /**
     * Gets the list of tasks assigned to the chef.
     * 
     * @author abood
     * @return List<ChefTask> - the list of tasks assigned to the chef.
     */
    public List<ChefTask> getTasks() {
        return tasks;
    }

    /**
     * Sets the list of tasks assigned to the chef.
     * 
     * @author abood
     * @param tasks - the list of tasks to be set for the chef.
     */
    public void setTasks(List<ChefTask> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the list of tasks assigned to the chef.
     * 
     * @author abood
     * @param task - the task to be added to the chef's task list.
     */
    public void addTask(ChefTask task) {
        this.tasks.add(task);
    }

    /**
     * Removes a task from the list of tasks assigned to the chef.
     * 
     * @author abood
     * @param task - the task to be removed from the chef's task list.
     */
    public void removeTask(ChefTask task) {
        this.tasks.remove(task);
    }

    /**
     * Gets the role of the chef.
     * 
     * @author abood
     * @return String - the role of the chef.
     */
    public String getRole() {
        return role;
    }

    /**
     * Sets the role of the chef.
     * 
     * @author abood
     * @param role - the role to be set for the chef.
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * Gets the name of the chef.
     * 
     * @author abood
     * @return String - the name of the chef.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the expertise of the chef.
     * 
     * @author abood
     * @return String - the expertise of the chef.
     */
    public String getExpertise() {
        return expertise;
    }

    /**
     * Sets the expertise of the chef.
     * 
     * @author abood
     * @param expertise - the expertise to be set for the chef.
     */
    public void setExpertise(String expertise) {
        this.expertise = expertise;
    }

    /**
     * Gets the workload of the chef.
     * 
     * @author abood
     * @return int - the workload of the chef.
     */
    public int getWorkload() {
        return workload;
    }

    /**
     * Sets the workload of the chef.
     * 
     * @author abood
     * @param workload - the workload to be set for the chef.
     */
    public void setWorkload(int workload) {
        this.workload = workload;
    }

    /**
     * Retrieves the notifications associated with the chef.
     * 
     * @author abood
     * @return List<Notification> - the list of notifications for the chef.
     */
    public List<Notification> getMyNotifications() {
        return com.project.cooking.cart.NotificationCenter.getNotificationsForChef(this);
    }

    /**
     * Constructor to initialize a chef with user details.
     * 
     * @author abood
     * @param username - the username for the chef.
     * @param password - the password for the chef.
     * @param firstName - the first name of the chef.
     * @param lastName - the last name of the chef.
     * @param email - the email of the chef.
     * @param phone - the phone number of the chef.
     * @param assignedCustomers - the list of assigned customers to the chef.
     */
    public Chef(String username, String password, String firstName, String lastName, 
                String email, String phone, List<String> assignedCustomers) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.assignedCustomers = assignedCustomers;
    }

    /**
     * Gets the username of the chef.
     * 
     * @author abood
     * @return String - the username of the chef.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username of the chef.
     * 
     * @author abood
     * @param username - the username to be set for the chef.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets the password of the chef.
     * 
     * @author abood
     * @return String - the password of the chef.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password of the chef.
     * 
     * @author abood
     * @param password - the password to be set for the chef.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets the first name of the chef.
     * 
     * @author abood
     * @return String - the first name of the chef.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the first name of the chef.
     * 
     * @author abood
     * @param firstName - the first name to be set for the chef.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets the last name of the chef.
     * 
     * @author abood
     * @return String - the last name of the chef.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the last name of the chef.
     * 
     * @author abood
     * @param lastName - the last name to be set for the chef.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets the email of the chef.
     * 
     * @author abood
     * @return String - the email of the chef.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email of the chef.
     * 
     * @author abood
     * @param email - the email to be set for the chef.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the phone number of the chef.
     * 
     * @author abood
     * @return String - the phone number of the chef.
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Sets the phone number of the chef.
     * 
     * @author abood
     * @param phone - the phone number to be set for the chef.
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Gets the list of assigned customers to the chef.
     * 
     * @author abood
     * @return List<String> - the list of customer names assigned to the chef.
     */
    public List<String> getAssignedCustomers() {
        return assignedCustomers;
    }

    /**
     * Sets the list of assigned customers to the chef.
     * 
     * @author abood
     * @param assignedCustomers - the list of customer names to be assigned to the chef.
     */
    public void setAssignedCustomers(List<String> assignedCustomers) {
        this.assignedCustomers = assignedCustomers;
    }

    /**
     * Adds a customer to the list of assigned customers for the chef.
     * 
     * @author abood
     * @param customerName - the name of the customer to be added.
     */
    public void addAssignedCustomer(String customerName) {
        this.assignedCustomers.add(customerName);
    }
    
public void  setName(String name)
{
this.name=name;
	
	
}

public void setSpecialty(String specialty) {
	this.specialty=specialty;
}



@Override
public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Chef)) return false;
    Chef chef = (Chef) o;
    return Objects.equals(name, chef.name);
}

@Override
public int hashCode() {
    return Objects.hash(name);
}
	
}


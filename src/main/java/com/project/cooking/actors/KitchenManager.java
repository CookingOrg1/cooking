package com.project.cooking.actors;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a Kitchen Manager responsible for managing chefs and handling alerts.
 * The Kitchen Manager can add alerts and manage a list of chefs in the kitchen.
 * 
 * @author abood
 */
public class KitchenManager {
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private List<Chef> managedChefs;
    private List<String> alerts = new ArrayList<>();

    /**
     * Retrieves the list of alerts for the Kitchen Manager.
     * 
     * @author abood
     * @param none
     * @return List<String> - the list of alerts.
     */
    public List<String> getAlerts() {
        return alerts;
    }

    /**
     * Adds an alert to the list of alerts for the Kitchen Manager.
     * 
     * @author abood
     * @param alert - the alert message to be added.
     * @return void
     */
    public void addAlert(String alert) {
        alerts.add(alert);
    }

    /**
     * Default constructor for the KitchenManager class.
     * Initializes a new instance with default values.
     * 
     * @author abood
     * @param none
     * @return void
     */
    public KitchenManager() {}

    /**
     * Constructor for the KitchenManager class that sets the provided values.
     * 
     * @author abood
     * @param username - the username of the Kitchen Manager.
     * @param password - the password for the Kitchen Manager.
     * @param firstName - the first name of the Kitchen Manager.
     * @param lastName - the last name of the Kitchen Manager.
     * @param email - the email of the Kitchen Manager.
     * @param phone - the phone number of the Kitchen Manager.
     * @param managedChefs - the list of chefs managed by the Kitchen Manager.
     * @return void
     */
    public KitchenManager(String username, String password, String firstName, String lastName, 
                         String email, String phone, List<Chef> managedChefs) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.managedChefs = managedChefs;
    }

    /**
     * Retrieves the username of the Kitchen Manager.
     * 
     * @author abood
     * @param none
     * @return String - the username of the Kitchen Manager.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Retrieves the password of the Kitchen Manager.
     * 
     * @author abood
     * @param none
     * @return String - the password of the Kitchen Manager.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Retrieves the first name of the Kitchen Manager.
     * 
     * @author abood
     * @param none
     * @return String - the first name of the Kitchen Manager.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Retrieves the last name of the Kitchen Manager.
     * 
     * @author abood
     * @param none
     * @return String - the last name of the Kitchen Manager.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Retrieves the email of the Kitchen Manager.
     * 
     * @author abood
     * @param none
     * @return String - the email of the Kitchen Manager.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Retrieves the phone number of the Kitchen Manager.
     * 
     * @author abood
     * @param none
     * @return String - the phone number of the Kitchen Manager.
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Retrieves the list of chefs managed by the Kitchen Manager.
     * 
     * @author abood
     * @param none
     * @return List<Chef> - the list of chefs managed by the Kitchen Manager.
     */
    public List<Chef> getManagedChefs() {
        return managedChefs;
    }

    /**
     * Sets the username of the Kitchen Manager.
     * 
     * @author abood
     * @param username - the username to be set.
     * @return void
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Sets the password of the Kitchen Manager.
     * 
     * @author abood
     * @param password - the password to be set.
     * @return void
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Sets the first name of the Kitchen Manager.
     * 
     * @author abood
     * @param firstName - the first name to be set.
     * @return void
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Sets the last name of the Kitchen Manager.
     * 
     * @author abood
     * @param lastName - the last name to be set.
     * @return void
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Sets the email of the Kitchen Manager.
     * 
     * @author abood
     * @param email - the email to be set.
     * @return void
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Sets the phone number of the Kitchen Manager.
     * 
     * @author abood
     * @param phone - the phone number to be set.
     * @return void
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Sets the list of chefs managed by the Kitchen Manager.
     * 
     * @author abood
     * @param managedChefs - the list of chefs to be set.
     * @return void
     */
    public void setManagedChefs(List<Chef> managedChefs) {
        this.managedChefs = managedChefs;
    }

    /**
     * Provides a string representation of the Kitchen Manager.
     * 
     * @author abood
     * @param none
     * @return String - a string representation of the Kitchen Manager.
     */
    @Override
    public String toString() {
        return "KitchenManager{" +
                "username='" + username + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", managedChefsCount=" + (managedChefs != null ? managedChefs.size() : 0) +
                '}';
    }
}

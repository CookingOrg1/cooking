package com.project.cooking.actors;

import java.util.ArrayList;
import java.util.List;

public class KitchenManager {
	 private String username;
	    private String password;
	    private String firstName;
	    private String lastName;
	    private String email;
	    private String phone;
	    private List<Chef> managedChefs;
    private List<String> alerts = new ArrayList<>();

    public List<String> getAlerts() {
        return alerts;
    }

    public void addAlert(String alert) {
        alerts.add(alert);
    }
    
    
    
    
    public KitchenManager() {}

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

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
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

    public List<Chef> getManagedChefs() {
        return managedChefs;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public void setManagedChefs(List<Chef> managedChefs) {
        this.managedChefs = managedChefs;
    }

    

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
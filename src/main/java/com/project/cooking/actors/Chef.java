package com.project.cooking.actors;

import java.util.ArrayList;
import java.util.List;

import com.project.cooking.cart.CustomerProfile;
import com.project.cooking.cart.Notification;
import com.project.cooking.meals.Meal;

public class Chef {
    
    private String name;
    private String role = "HEAD_CHEF"; 
    private String expertise;
    private int workload;
   
    
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private List<String> assignedCustomers;
    
    public Chef(String name1) {
        name = name1;
        this.workload=0;
        
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
    
    
    
    public String getRole() {
        return role;
    }
    
    public void setRole(String role) {
        this.role = role;
    }

	public String getName() {
		return name;
	}
	public String getExpertise() {
	    return expertise;
	}

	public void setExpertise(String expertise) {
	    this.expertise = expertise;
	}

	public int getWorkload() {
	    return workload;
	}

	public void setWorkload(int workload) {
	    this.workload = workload;
	}
	

  

    
	public List<Notification> getMyNotifications() {
	    return com.project.cooking.cart.NotificationCenter.getNotificationsForChef(this);
	}
    
    
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

public String getUsername() {
    return username;
}

public void setUsername(String username) {
    this.username = username;
}

public String getPassword() {
    return password;
}

public void setPassword(String password) {
    this.password = password;
}

public String getFirstName() {
    return firstName;
}

public void setFirstName(String firstName) {
    this.firstName = firstName;
}

public String getLastName() {
    return lastName;
}

public void setLastName(String lastName) {
    this.lastName = lastName;
}

public String getEmail() {
    return email;
}

public void setEmail(String email) {
    this.email = email;
}

public String getPhone() {
    return phone;
}

public void setPhone(String phone) {
    this.phone = phone;
}

public List<String> getAssignedCustomers() {
    return assignedCustomers;
}

public void setAssignedCustomers(List<String> assignedCustomers) {
    this.assignedCustomers = assignedCustomers;
}

public void addAssignedCustomer(String customerName) {
    this.assignedCustomers.add(customerName);
}

public void removeAssignedCustomer(String customerName) {
    this.assignedCustomers.remove(customerName);
}
    
    
    
	
	    
	}
    
    
    
    
    
    
    


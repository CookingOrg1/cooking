package com.project.cooking.actors;


/**
 * The Login class handles user login functionality for both Customer and Chef roles. 
 * It ensures that only one user (either a Customer or a Chef) is logged in at any time 
 * and provides methods to check login status and retrieve logged-in user details.
 * 
 * This class helps in managing and controlling user sessions in the system.
 * 
 * @author abood
 */
public class Login {

    private static Customer loggedInCustomer;
    private static Chef loggedInChef;

    /**
     * Logs in as a customer.
     * This method sets the provided Customer as the logged-in user and 
     * ensures that any previously logged-in Chef is logged out.
     * 
     * @param customer The Customer object to be logged in.
     * @return void
     * @author abood
     */
    public static void loginAsCustomer(Customer customer) {
        loggedInCustomer = customer;
        loggedInChef = null; // Ensures chef is logged out
    }

    /**
     * Logs in as a chef.
     * This method sets the provided Chef as the logged-in user and 
     * ensures that any previously logged-in Customer is logged out.
     * 
     * @param chef The Chef object to be logged in.
     * @return void
     * @author abood
     */
    public static void loginAsChef(Chef chef) {
        loggedInChef = chef;
        loggedInCustomer = null; // Ensures customer is logged out
    }

    /**
     * Checks if any user (Customer or Chef) is currently logged in.
     * 
     * @param none
     * @return boolean - true if either a Customer or a Chef is logged in, false otherwise.
     * @author abood
     */
    public static boolean isLoggedIn() {
        return loggedInCustomer != null || loggedInChef != null;
    }

    /**
     * Checks if a Customer is currently logged in.
     * 
     * @param none
     * @return boolean - true if a Customer is logged in, false otherwise.
     * @author abood
     */
    public static boolean isCustomerLoggedIn() {
        return loggedInCustomer != null;
    }

    /**
     * Checks if a Chef is currently logged in.
     * 
     * @param none
     * @return boolean - true if a Chef is logged in, false otherwise.
     * @author abood
     */
    public static boolean isChefLoggedIn() {
        return loggedInChef != null;
    }

    /**
     * Retrieves the currently logged-in Customer.
     * 
     * @param none
     * @return Customer - the logged-in Customer object, or null if no Customer is logged in.
     * @author abood
     */
    public static Customer getLoggedInCustomer() {
        return loggedInCustomer;
    }

    /**
     * Retrieves the currently logged-in Chef.
     * 
     * @param none
     * @return Chef - the logged-in Chef object, or null if no Chef is logged in.
     * @author abood
     */
    public static Chef getLoggedInChef() {
        return loggedInChef;
    }

    /**
     * Retrieves the role of the currently logged-in user.
     * This method returns a string indicating the role of the logged-in user.
     * 
     * @param none
     * @return String - "Customer" if a Customer is logged in, "Chef" if a Chef is logged in, "None" if no user is logged in.
     * @author abood
     */
    public static String getLoggedInUserRole() {
        if (isCustomerLoggedIn()) {
            return "Customer";
        } else if (isChefLoggedIn()) {
            return "Chef";
        } else {
            return "None";
        }
    }
    
    
    public static void logout() {
        loggedInCustomer = null;
        loggedInChef = null;
    }
    
    
}
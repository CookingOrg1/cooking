package com.project.cooking.actors;

/**
 * The SystemAdmin class represents an administrative user in the system.
 * This class stores information about the system administrator's credentials and personal details.
 * It provides methods for accessing and modifying the administrator's information.
 * 
 * @author abood
 */
public class SystemAdmin {

    private String username;
    private String password;
    private String firstName;  
    private String lastName;  
    private String email;     
    private String phone;    

    /**
     * Constructor to initialize the SystemAdmin object with a username and password.
     * 
     * @param username The username of the system administrator.
     * @param password The password of the system administrator.
     */
    public SystemAdmin(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * Default constructor for creating a SystemAdmin object without initializing fields.
     */
    public SystemAdmin() {
    }

    /**
     * Retrieves the username of the system administrator.
     * 
     * @return String - the username of the system administrator.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Retrieves the password of the system administrator.
     * 
     * @return String - the password of the system administrator.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the username of the system administrator.
     * 
     * @param username The username to be set for the system administrator.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Sets the password of the system administrator.
     * 
     * @param password The password to be set for the system administrator.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Retrieves the first name of the system administrator.
     * 
     * @return String - the first name of the system administrator.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Retrieves the last name of the system administrator.
     * 
     * @return String - the last name of the system administrator.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Retrieves the email address of the system administrator.
     * 
     * @return String - the email address of the system administrator.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Retrieves the phone number of the system administrator.
     * 
     * @return String - the phone number of the system administrator.
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Sets the first name of the system administrator.
     * 
     * @param firstName The first name to be set for the system administrator.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Sets the last name of the system administrator.
     * 
     * @param lastName The last name to be set for the system administrator.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Sets the email address of the system administrator.
     * 
     * @param email The email address to be set for the system administrator.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Sets the phone number of the system administrator.
     * 
     * @param phone The phone number to be set for the system administrator.
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }
}
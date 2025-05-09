package com.project.cooking.actors;

public class SystemAdmin {

    private String username;
    private String password;
    private String firstName;  
    private String lastName;  
    private String email;     
    private String phone;    
    public SystemAdmin(String username, String password) {
        this.username = username;
        this.password = password;
    }
    public SystemAdmin() {
    }


	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}
	 public void setUsername(String username) {
	        this.username = username;
	    }

	    public void setPassword(String password) {
	        this.password = password;
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

}
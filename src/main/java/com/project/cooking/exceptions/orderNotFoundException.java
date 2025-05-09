package com.project.cooking.exceptions;

 @SuppressWarnings("serial")
public class orderNotFoundException extends Exception {
	 
	 

	    /**
	     * Constructor for OrderNotFoundException that accepts a message.
	     * 
	     * @param message The message that describes the exception.
	     * @author abood
	     */
    public orderNotFoundException(String message) {
        super(message);
    }
    
}
 
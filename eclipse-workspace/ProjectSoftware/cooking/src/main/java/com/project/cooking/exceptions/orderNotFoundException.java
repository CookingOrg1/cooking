package com.project.cooking.exceptions;

 @SuppressWarnings("serial")
public class orderNotFoundException extends Exception {
    public orderNotFoundException(String message) {
        super(message);
    }
    
}
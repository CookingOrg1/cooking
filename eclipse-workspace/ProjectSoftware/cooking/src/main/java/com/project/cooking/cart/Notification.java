package com.project.cooking.cart;
public class Notification {
    private String message;
    private String status; 

    public Notification(String message) {
        this.message = message;
        this.status = "UNREAD";
    }

    public String getMessage() {
        return message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
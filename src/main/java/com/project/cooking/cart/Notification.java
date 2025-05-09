package com.project.cooking.cart;

public class Notification {
    private String message;
    private String status;

    /**
     * Constructs a Notification with a given message.
     * Initializes the status to "UNREAD" by default.
     * 
     * @param message The message to be sent in the notification.
     * @author abood
     */
    public Notification(String message) {
        this.message = message;
        this.status = "UNREAD";
    }

    /**
     * Retrieves the message of the notification.
     * 
     * @author abood
     * @param none
     * @return String - the message of the notification.
     */
    public String getMessage() {
        return message;
    }

    /**
     * Retrieves the status of the notification.
     * 
     * @author abood
     * @param none
     * @return String - the status of the notification ("READ" or "UNREAD").
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the status of the notification.
     * 
     * @author abood
     * @param status - the new status of the notification ("READ" or "UNREAD").
     * @return void
     */
    public void setStatus(String status) {
        this.status = status;
    }
}
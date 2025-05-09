package com.project.cooking.meals;

/**
 * Represents a task with a description.
 * This class encapsulates a task that includes a description.
 * Author: Omar
 */
public class Task {

    private String description;

    /**
     * Constructs a Task with the specified description.
     * 
     * @param description the description of the task (String)
     * @author Omar
     */
    public Task(String description) {
        this.description = description;
    }

    /**
     * Retrieves the description of the task.
     * 
     * @return the description of the task (String)
     * @author Omar
     */
    public String getDescription() {
        return description;
    }
}
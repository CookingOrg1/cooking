package com.project.cooking.actors;

public class ChefTask {

    private String description;
    private int priority; 
    private boolean completed;

    /**
     * Default constructor for ChefTask.
     * Initializes a new instance of the ChefTask without any description, priority, or completion status.
     *
     * @author abood
     */
    public ChefTask() {}

    /**
     * Constructor for ChefTask.
     * Initializes a new ChefTask with a specified description and priority. The task is set as not completed by default.
     *
     * @author abood
     * @param description - the description of the task.
     * @param priority - the priority of the task (1 for High, 2 for Medium, 3 for Low).
     */
    public ChefTask(String description, int priority) {
        this.description = description;
        this.priority = priority;
        this.completed = false;
    }

    /**
     * Retrieves the description of the task.
     * 
     * @author abood
     * @return String - the description of the task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the task.
     * 
     * @author abood
     * @param description - the description to set for the task.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Retrieves the priority of the task.
     * 
     * @author abood
     * @return int - the priority of the task (1 for High, 2 for Medium, 3 for Low).
     */
    public int getPriority() {
        return priority;
    }

    /**
     * Sets the priority of the task.
     * 
     * @author abood
     * @param priority - the priority to set for the task (1 for High, 2 for Medium, 3 for Low).
     */
    public void setPriority(int priority) {
        this.priority = priority;
    }

    /**
     * Checks if the task is completed.
     * 
     * @author abood
     * @return boolean - true if the task is completed, false otherwise.
     */
    public boolean isCompleted() {
        return completed;
    }

    /**
     * Sets the completion status of the task.
     * 
     * @author abood
     * @param completed - the completion status to set for the task.
     */
    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    /**
     * Returns a string representation of the task with its priority, description, and completion status.
     * 
     * @author abood
     * @return String - a formatted string representing the task's priority, description, and completion status.
     */
    @Override
    public String toString() {
        String priorityStr = switch(priority) {
            case 1 -> "High";
            case 2 -> "Medium";
            case 3 -> "Low";
            default -> "Unknown";
        };
        return String.format("[%s] %s (%s)", 
            priorityStr, 
            description, 
            completed ? "Completed" : "Pending");
    }
}

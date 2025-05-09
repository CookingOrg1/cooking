package com.project.cooking.actors;

public class ChefTask {

	 private String description;
	    private int priority; 
	    private boolean completed;
	    
	    public ChefTask() {}
	    
	    public ChefTask(String description, int priority) {
	        this.description = description;
	        this.priority = priority;
	        this.completed = false;
	    }
	    
	    public String getDescription() {
	        return description;
	    }
	    
	    public void setDescription(String description) {
	        this.description = description;
	    }
	    
	    public int getPriority() {
	        return priority;
	    }
	    
	    public void setPriority(int priority) {
	        this.priority = priority;
	    }
	    
	    public boolean isCompleted() {
	        return completed;
	    }
	    
	    public void setCompleted(boolean completed) {
	        this.completed = completed;
	    }
	    
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

package com.project.cooking.meals;

import java.util.ArrayList;
import java.util.List;

import com.project.cooking.kitchen.Ingredient;

public class Meal {
    private String mealName;
    private List<Ingredient> ingredients = new ArrayList<>();
    private double price;
    private List<String> ingredienT;
    public Meal(String mealName) {
        this.mealName = mealName;
    }

    public String getMealName() {
        return mealName;
    }
    public List<String> getIngredientss() {
        return ingredienT;
    }
    public void addIngredient(Ingredient ingredient) {
    	if (ingredients == null) {
            ingredients = new ArrayList<>();
        }
        ingredients.add(ingredient);    }

    public List<Ingredient> getIngredients() {
        return new ArrayList<>(ingredients);
    }
    
    public void removeIngredient(Ingredient ingredient) {
    	if (ingredients != null) {
            ingredients.removeIf(i -> i.getName().equals(ingredient.getName()));
        }
    }

    public boolean hasIngredient(String ingredientName) {
        if (ingredients != null) {
            return ingredients.stream().anyMatch(i -> i.getName().equals(ingredientName));
        }
        return false;
    }
    
    
    
    
    
    
    public Meal(String name, double price, List<String> ingredienT) {
        this.mealName = name;
        this.price = price;
        this.ingredienT = ingredienT;
    }

    public String getName() {
        return mealName;
    }

    public void setName(String name) {
        this.mealName = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public List<String> getingredienT() {
        return ingredienT;
    }

    public void setIngredients(List<String> ingredienT) {
        this.ingredienT = ingredienT;
    }
    
    
    
}
package com.project.cooking.meals;

import java.util.ArrayList;
import java.util.List;
import com.project.cooking.kitchen.Ingredient;

/**
 * Represents a meal that consists of multiple ingredients and has a price.
 * Supports both ingredient objects and simple ingredient names.
 * 
 * @author omar
 */
public class Meal {
    
    private String mealName;
    private List<Ingredient> ingredients = new ArrayList<>();
    private double price;
    private List<String> ingredienT;

    /**
     * Constructor to create a meal with just a name.
     *
     * @param mealName the name of the meal
     * 
     * @author omar
     */
    public Meal(String mealName) {
        this.mealName = mealName;
    }

    /**
     * Constructor to create a meal with name, price, and a list of ingredient names.
     *
     * @param name the name of the meal
     * @param price the price of the meal
     * @param ingredienT the list of ingredient names
     * 
     * @author omar
     */
    public Meal(String name, double price, List<String> ingredienT) {
        this.mealName = name;
        this.price = price;
        this.ingredienT = ingredienT;
    }

    public Meal(String name, double price) {
        this.mealName = name;
        this.price = price;
    }

	/**
     * Returns the name of the meal.
     *
     * @return meal name
     * 
     * @author omar
     */
    public String getMealName() {
        return mealName;
    }

    /**
     * Returns a list of Ingredient objects used in the meal.
     *
     * @return list of Ingredient objects
     * 
     * @author omar
     */
    public List<Ingredient> getIngredients() {
        return new ArrayList<>(ingredients);
    }

    /**
     * Adds an Ingredient object to the meal.
     *
     * @param ingredient the Ingredient to add
     * 
     * @author omar
     */
    public void addIngredient(Ingredient ingredient) {
        if (ingredients == null) {
            ingredients = new ArrayList<>();
        }
        ingredients.add(ingredient);
    }

    /**
     * Removes an Ingredient by matching its name.
     *
     * @param ingredient the Ingredient to remove
     * 
     * @author omar
     */
    public void removeIngredient(Ingredient ingredient) {
        if (ingredients != null) {
            ingredients.removeIf(i -> i.getName().equals(ingredient.getName()));
        }
    }

    /**
     * Checks whether the meal contains an ingredient by name.
     *
     * @param ingredientName the name of the ingredient
     * @return true if the ingredient is found, false otherwise
     * 
     * @author omar
     */
    public boolean hasIngredient(String ingredientName) {
        if (ingredients != null) {
            return ingredients.stream().anyMatch(i -> i.getName().equals(ingredientName));
        }
        return false;
    }

    /**
     * Returns the meal's name (alternative method).
     *
     * @return meal name
     * 
     * @author omar
     */
    public String getName() {
        return mealName;
    }

    /**
     * Sets the meal's name.
     *
     * @param name the name to set
     * 
     * @author omar
     */
    public void setName(String name) {
        this.mealName = name;
    }

    /**
     * Returns the price of the meal.
     *
     * @return meal price
     * 
     * @author omar
     */
    public double getPrice() {
        return price;
    }

    /**
     * Sets the price of the meal.
     *
     * @param price the price to set
     * 
     * @author omar
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Returns the list of ingredient names.
     *
     * @return list of ingredient names
     * 
     * @author omar
     */
    public List<String> getIngredientss() {
        return ingredienT;
    }

    /**
     * Returns the list of ingredient names (duplicate method).
     *
     * @return list of ingredient names
     * 
     * @author omar
     */
    public List<String> getingredienT() {
        return ingredienT;
    }

    /**
     * Sets the list of ingredient names.
     *
     * @param ingredienT the list of ingredient names
     * 
     * @author omar
     */
    public void setIngredients(List<String> ingredienT) {
        this.ingredienT = ingredienT;
    }
   
   

    
}

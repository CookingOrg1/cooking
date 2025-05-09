package com.project.cooking.cart;

import java.util.ArrayList;
import java.util.List;

import com.project.cooking.meals.Meal;

 public  class Cart {
    public List<Meal> items = new ArrayList<>();

    public void addItem(Meal meal) {
        items.add(meal);
    }

    public List<Meal> getItems() {
        return new ArrayList<>(items);
    }

    public void clear() {
        items.clear();
    }
}

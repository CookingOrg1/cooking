package com.project.cooking.orders;
import java.util.ArrayList;
import java.util.List;



 public class OrderHistory {
    public List<Order> orders = new ArrayList<>();

    public void addOrder(Order order) {
        orders.add(order);
    }

    public List<Order> getOrders() {
        return new ArrayList<>(orders);
    }

    public boolean isEmpty() {
        return orders.isEmpty();
    }
}

 
  
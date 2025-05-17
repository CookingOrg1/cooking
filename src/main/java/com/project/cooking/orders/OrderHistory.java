package com.project.cooking.orders;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the history of orders placed by a customer.
 * This class manages a collection of orders and provides methods to add, retrieve, and check the order history.
 * Author: Omar
 */
public class OrderHistory {

    public List<Order> orders = new ArrayList<>();

    /**
     * Adds a new order to the order history.
     * This method allows adding an order to the history.
     * 
     * @param order the order to be added (Order)
     * @author Omar
     */
    public void addOrder(Order order) {
        orders.add(order);
    }

    /**
     * Retrieves a copy of the list of orders in the order history.
     * This method returns a new list containing all the orders in the history.
     * 
     * @return a list of orders (List<Order>)
     * @author Omar
     */
    public List<Order> getOrders() {
        return new ArrayList<>(orders);
    }

    /**
     * Checks whether the order history is empty.
     * This method returns true if there are no orders in the history, otherwise false.
     * 
     * @return true if the order history is empty, false otherwise (boolean)
     * @author Omar
     */
    public boolean isEmpty() {
        return orders.isEmpty();
    }

	public void setOrders(List<Order> emptyList) {
this.orders=emptyList;

	}
}
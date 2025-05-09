package com.project.cooking.orders;

import java.util.HashMap;
import java.util.Map;
import com.project.cooking.exceptions.orderNotFoundException;

/**
 * Provides services for creating and retrieving orders.
 * This class manages orders using an in-memory storage (HashMap) and provides methods 
 * to create new orders and retrieve orders by their ID.
 * Author: Omar
 */
public class OrderService {

    private static final Map<String, Order> orders = new HashMap<>();
    private static int idCounter = 1000;

    /**
     * Creates a new order and assigns it a unique ID.
     * This method generates a new order ID, stores the order in the map, 
     * and returns the generated ID for further reference.
     * 
     * @param order the order to be created (Order)
     * @return the unique ID of the newly created order (String)
     * @author Omar
     */
    public static String createOrder(Order order) {
        String newId = "ORD-" + idCounter++;
        orders.put(newId, order);
        return newId;
    }

    /**
     * Retrieves an order by its unique ID.
     * This method searches for an order in the map using the provided ID.
     * If the order is found, it is returned; otherwise, an exception is thrown.
     * 
     * @param orderId the ID of the order to be retrieved (String)
     * @return the order associated with the provided ID (Order)
     * @throws orderNotFoundException if no order is found with the provided ID
     * @author Omar
     */
    public static Order getOrder(String orderId) throws orderNotFoundException {
        Order order = orders.get(orderId);
        if (order == null) throw new orderNotFoundException("Order not found");
        return order;
    }
}
package com.project.cooking.orders;


import java.util.HashMap;
import java.util.Map;

import com.project.cooking.exceptions.orderNotFoundException;


public class OrderService {
    private static final Map<String, Order> orders = new HashMap<>();
    private static int idCounter = 1000;

    public static String createOrder(Order order) {
        String newId = "ORD-" + idCounter++;
        orders.put(newId, order);
        return newId;
    }

    public static Order getOrder(String orderId) throws orderNotFoundException {
        Order order = orders.get(orderId);
        if (order == null) throw new orderNotFoundException("Order not found");
        return order;
    }
}

     
package com.project.cooking.orders;

import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;

/**
 * An enhanced version of the OrderHistory class that includes additional functionality 
 * to sort orders by date in descending order.
 * Author: Omar
 */
public class EnhancedOrderHistory extends OrderHistory {

    /**
     * Retrieves the list of orders sorted by date in descending order.
     * This method overrides the basic order history functionality by sorting the orders 
     * based on their date from the most recent to the oldest.
     * 
     * @return a list of orders sorted by date in descending order (List<Order>)
     * @author Omar
     */
    public List<Order> getSortedOrders() {
        List<Order> sorted = new ArrayList<>(super.getOrders());
        sorted.sort(Comparator.comparing(o -> ((Order) o).date).reversed());
        return sorted;
    }
}
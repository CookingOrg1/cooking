package com.project.cooking.orders;

import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;

public class EnhancedOrderHistory extends OrderHistory {
    public List<Order> getSortedOrders() {
        List<Order> sorted = new ArrayList<>(super.getOrders());
        sorted.sort(Comparator.comparing(o -> ((Order) o).date).reversed());
        return sorted;
    }
}
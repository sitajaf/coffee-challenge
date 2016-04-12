package org.coffeeservice.services;

import org.coffeeservice.models.Order;
import org.coffeeservice.models.OrderNote;

public class CoffeeService {
    public OrderNote order(String coffeeName, Order order) {
        return new OrderNote(coffeeName, 5);
    }
}

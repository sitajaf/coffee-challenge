package org.coffeeservice.services;

import org.coffeeservice.exceptions.CoffeeMenuException;
import org.coffeeservice.exceptions.CoffeeOrderException;
import org.coffeeservice.models.Order;
import org.coffeeservice.models.OrderNote;

public class CoffeeService {
    private final MenuService menuService;

    public CoffeeService() throws CoffeeMenuException {
        this.menuService = new MenuService();
    }

    public OrderNote order(String coffeeName, Order order) throws CoffeeOrderException {
        if (!menuService.exists(coffeeName)) {
            throw new CoffeeOrderException("Coffee not on menu!");
        }
        return new OrderNote(coffeeName, 5);
    }
}

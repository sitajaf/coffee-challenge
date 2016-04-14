package org.coffeeservice.services;

import org.coffeeservice.exceptions.CoffeeOrderException;
import org.coffeeservice.models.DelaySimulator;
import org.coffeeservice.models.Order;
import org.coffeeservice.models.OrderNote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CoffeeService {

    private MenuService menuService;

    @Autowired
    public CoffeeService(MenuService menuService) {
        this.menuService = menuService;
    }

    private int counter;

    public OrderNote order(String coffeeName, Order order) throws CoffeeOrderException {
        if (!menuService.exists(coffeeName)) {
            throw new CoffeeOrderException("Coffee not on menu!");
        }
        make(coffeeName, order);
        final String orderPath = "/order/";
        return new OrderNote(String.format(orderPath, coffeeName), 5);
    }

    private OrderNote make(String coffeeName, Order order) {
        counter++;

        return null;
    }
}

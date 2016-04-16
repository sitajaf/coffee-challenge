package org.coffeeservice.services;

import org.coffeeservice.exceptions.CoffeeOrderException;
import org.coffeeservice.models.CoffeeMachine;
import org.coffeeservice.models.Order;
import org.coffeeservice.models.OrderNote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CoffeeService {

    private MenuService menuService;
    private CoffeeMachine coffeeMachine;

    @Autowired
    public CoffeeService(MenuService menuService, CoffeeMachine coffeeMachine) {
        this.menuService = menuService;
        this.coffeeMachine = coffeeMachine;
    }

    private int counter = 0;

    public OrderNote order(String coffeeName, Order order) throws CoffeeOrderException {
        if (!menuService.exists(coffeeName)) {
            throw new CoffeeOrderException("Coffee not on menu!");
        }
        return make(coffeeName, order);
    }

    private OrderNote make(String coffeeName, Order order) {
        counter++;
        final String orderPath = "/order/%d";
        return new OrderNote(String.format(orderPath, counter), 5);
    }

}

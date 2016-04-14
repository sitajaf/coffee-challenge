package org.coffeeservice.services;

import org.coffeeservice.exceptions.CoffeeOrderException;
import org.coffeeservice.models.CoffeeMachine;
import org.coffeeservice.models.DelaySimulator;
import org.coffeeservice.models.Order;
import org.coffeeservice.models.OrderNote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class CoffeeService {

    private MenuService menuService;
    private List<CoffeeMachine> machines;

    @Autowired
    public CoffeeService(MenuService menuService) {
        this.menuService = menuService;
        this.machines = new ArrayList<>();

        this.machines.add(new CoffeeMachine(new DelaySimulator()));
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

    public int machines() {
        return machines.size();
    }

    public void addMachine() {
        this.machines.add(new CoffeeMachine(new DelaySimulator()));
    }
}

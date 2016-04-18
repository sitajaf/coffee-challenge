package org.coffeeservice.services;

import org.coffeeservice.enums.OrderStatus;
import org.coffeeservice.exceptions.CoffeeMachineException;
import org.coffeeservice.exceptions.CoffeeOrderException;
import org.coffeeservice.models.CoffeeMachine;
import org.coffeeservice.models.Order;
import org.coffeeservice.models.OrderNote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class CoffeeService {

    private MenuService menuService;
    private CoffeeMachine coffeeMachine;
    private Map<String, OrderStatus> orders;


    @Autowired
    public CoffeeService(MenuService menuService, CoffeeMachine coffeeMachine) {
        this.menuService = menuService;
        this.coffeeMachine = coffeeMachine;
        this.orders = new HashMap<>();
    }

    private int counter = 0;

    public OrderNote order(String coffeeName, Order order) throws CoffeeOrderException, CoffeeMachineException {
        if (!menuService.exists(coffeeName)) {
            throw new CoffeeOrderException("Coffee not on menu!");
        }
        return make(coffeeName, order);
    }

    private OrderNote make(String coffeeName, Order order) throws CoffeeMachineException {
        counter++;
        String orderPath = String.format("/order/%d", counter);

        if (coffeeMachine.isBusy()) {
            orders.put(orderPath, OrderStatus.QUEUED);
        } else {
            orders.put(orderPath, OrderStatus.MAKING);
            coffeeMachine.start(coffeeName, order.getExtras(), status -> this.orders.replace(orderPath, status));
        }


        return new OrderNote(orderPath, 5);
    }

    public OrderStatus statusOf(String orderPath) {
        System.out.println("orders: " + orders);
        return orders.get(orderPath);
    }

}

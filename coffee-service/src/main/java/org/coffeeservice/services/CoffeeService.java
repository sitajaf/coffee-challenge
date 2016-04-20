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
import java.util.function.Predicate;

@Service
public class CoffeeService {

    private MenuService menuService;
    private CoffeeMachine coffeeMachine;
    private Map<String, Order> orders;


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
        order.setCoffeeName(coffeeName);
        return make(order);
    }

    private OrderNote make(Order order) throws CoffeeMachineException {
        counter++;
        String orderPath = String.format("/order/%d", counter);

        if (coffeeMachine.isBusy()) {
            order.setStatus(OrderStatus.QUEUED);
            orders.put(orderPath, order);
        } else {
            orders.put(orderPath, order);
            startMachine(order, orderPath);
        }

        int waitTime = 2; //TODO: ready from property
        return new OrderNote(orderPath, waitTime);
    }

    private void startMachine(Order order, String orderPath) throws CoffeeMachineException {
        order.setStatus(OrderStatus.MAKING);
        coffeeMachine.start(order.getCoffeeName(), order.getExtras(), status -> {
            this.orders.get(orderPath).setStatus(status);
            if (status == OrderStatus.READY) {
                nextOrder();
            }
        });
    }

    private void nextOrder() throws CoffeeMachineException {
        Predicate<Map.Entry<String, Order>> condition = entryValue -> entryValue.getValue().getStatus().equals(OrderStatus.QUEUED);

        if (this.orders.entrySet().stream().anyMatch(condition)) {
            Map.Entry<String, Order> entry = this.orders.entrySet()
                    .stream()
                    .filter(condition)
                    .findFirst()
                    .get();
            startMachine(this.orders.get(entry.getKey()), entry.getKey());
        }

    }

    public OrderStatus statusOf(String orderPath) throws CoffeeOrderException {
        if (!orders.containsKey(orderPath)) {
            throw new CoffeeOrderException("Invalid Order!");
        }
        return orders.get(orderPath).getStatus();
    }

}

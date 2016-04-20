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
        return make(coffeeName, order);
    }

    private OrderNote make(String coffeeName, Order order) throws CoffeeMachineException {
        counter++;
        String orderPath = String.format("/order/%d", counter);

        if (coffeeMachine.isBusy()) {
            order.setStatus(OrderStatus.QUEUED);
            orders.put(orderPath, order);
        } else {
            order.setStatus(OrderStatus.MAKING);
            orders.put(orderPath, order );
            coffeeMachine.start(coffeeName, order.getExtras(), status -> {
                this.orders.get(orderPath).setStatus(status);
//                TODO: implement calling next order
//                if(status == OrderStatus.READY){
//                    nextOrder();
//                }
            });
        }


        return new OrderNote(orderPath, 5);
    }

    public OrderStatus statusOf(String orderPath) throws CoffeeOrderException {
        if(!orders.containsKey(orderPath)){
            throw new CoffeeOrderException("Invalid Order!");
        }
        return orders.get(orderPath).getStatus();
    }

}

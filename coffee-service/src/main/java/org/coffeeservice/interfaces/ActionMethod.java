package org.coffeeservice.interfaces;

import org.coffeeservice.enums.OrderStatus;
import org.coffeeservice.exceptions.CoffeeMachineException;

@FunctionalInterface
public interface ActionMethod {
    void execute(OrderStatus status) throws CoffeeMachineException;
}

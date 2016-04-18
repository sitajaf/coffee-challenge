package org.coffeeservice.interfaces;

import org.coffeeservice.enums.OrderStatus;

@FunctionalInterface
public interface ActionMethod {
    void execute(OrderStatus status);
}
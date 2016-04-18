package org.coffeeservice.interfaces;

import org.coffeeservice.enums.OrderStatus;

@FunctionalInterface
public interface IsBusyAction {
    void execute(boolean isBusy);
}

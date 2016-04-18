package org.coffeeservice.models;

import org.coffeeservice.exceptions.CoffeeMachineException;
import org.coffeeservice.interfaces.ActionMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CoffeeMachine {
    private boolean isBusy;
    private DelaySimulator delaySimulator;

    @Autowired
    public CoffeeMachine(DelaySimulator delaySimulator) {
        this.delaySimulator = delaySimulator;
    }

    public void start(String latte, List<String> extras, ActionMethod statusAction) throws CoffeeMachineException {
        isBusy = true;
        System.out.println(String.format("Making %s with %s", latte, extras.toString()));

        delaySimulator.simulate(statusAction, isBusy -> this.isBusy = isBusy);
    }

    public boolean isBusy() {
        return this.isBusy;
    }

}

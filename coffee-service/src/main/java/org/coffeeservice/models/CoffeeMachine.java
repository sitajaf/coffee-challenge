package org.coffeeservice.models;

import org.coffeeservice.exceptions.CoffeeMachineException;
import org.coffeeservice.interfaces.ActionMethod;

import java.util.List;

public class CoffeeMachine {

    private DelaySimulator delaySimulator;
    private boolean isBusy;

    public CoffeeMachine(DelaySimulator delaySimulator) {
        this.delaySimulator = delaySimulator;
    }

    public void start(String latte, List<String> extras, ActionMethod setStatusAction) throws CoffeeMachineException {
        this.isBusy = true;

        System.out.println(String.format("Making %s with %s", latte, extras.toString()));

        delaySimulator.simulate(() -> this.isBusy = false, setStatusAction);
    }

    public boolean isBusy() {
        return this.isBusy;
    }

}

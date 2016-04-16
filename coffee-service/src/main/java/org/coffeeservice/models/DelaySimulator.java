package org.coffeeservice.models;

import org.coffeeservice.interfaces.ActionMethod;
import org.coffeeservice.exceptions.CoffeeMachineException;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class DelaySimulator {

    public void simulate(ActionMethod action1, ActionMethod action2) throws CoffeeMachineException {
        Thread thread = new Thread(new CoffeeMachineRunner(action1, action2));
        thread.start();
        int delay = getDelay();
        try {
            thread.sleep(delay);
        } catch (InterruptedException e) {
            e.printStackTrace();
            throw new CoffeeMachineException("Failed to start making coffee!!");
        }
    }

    private int getDelay() {
        int min = 5;
        int max = 6;
        Random random = new Random();
        return (random.nextInt((max - min) + 1) + min) * 1000;
    }

    private class CoffeeMachineRunner implements Runnable {
        private ActionMethod action;
        private ActionMethod action2;

        public CoffeeMachineRunner(ActionMethod action, ActionMethod action2) {
            this.action = action;
            this.action2 = action2;
        }

        @Override
        public void run() {
            action.operation();
            action2.operation();
        }
    }


}

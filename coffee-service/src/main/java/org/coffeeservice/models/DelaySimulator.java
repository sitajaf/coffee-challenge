package org.coffeeservice.models;

import org.apache.commons.lang.time.StopWatch;
import org.coffeeservice.enums.OrderStatus;
import org.coffeeservice.exceptions.CoffeeMachineException;
import org.coffeeservice.interfaces.ActionMethod;
import org.coffeeservice.interfaces.IsBusyAction;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class DelaySimulator {
    private final int NORMAL_DURATION = 2;
    int MINUTE = 1000 * 60;

    public void simulate(ActionMethod statusAction, IsBusyAction isBusyAction) throws CoffeeMachineException {
        Thread thread = new Thread(new CoffeeMachineRunner(statusAction, isBusyAction));
        thread.start();
    }

    private int getDelay() {
        Random random = new Random();
        int DELAYED_DURATION = 3;
        int ONE = 1;

        return (random.nextInt((DELAYED_DURATION - NORMAL_DURATION) + ONE) + NORMAL_DURATION) * MINUTE;
    }

    private class CoffeeMachineRunner implements Runnable {
        private final StopWatch stopWatch;
        private final ActionMethod statusAction;
        private final IsBusyAction isBusyAction;

        private boolean delaySet = false;

        CoffeeMachineRunner(ActionMethod statusAction, IsBusyAction isBusyAction) {
            this.statusAction = statusAction;
            this.isBusyAction = isBusyAction;
            this.stopWatch = new StopWatch();
        }

        @Override
        public void run() {
            int delay = getDelay();
            try {
                stopWatch.start();
                while (stopWatch.getTime() <= delay) {
                    if (!delaySet && stopWatch.getTime() > NORMAL_DURATION * MINUTE) {
                        statusAction.execute(OrderStatus.DELAYED);
                        this.delaySet = true;
                    }
                }
                statusAction.execute(OrderStatus.READY);
                System.out.println("Coffee is ready!");

            } catch (Exception e) {
                System.out.println("A problem while making the coffee");
                e.printStackTrace();
            } finally {
                stopWatch.stop();
                isBusyAction.execute(false);
            }
        }
    }


}

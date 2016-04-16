package models;

import org.coffeeservice.interfaces.ActionMethod;
import org.coffeeservice.models.CoffeeMachine;
import org.coffeeservice.models.DelaySimulator;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class CoffeeMachineTest {
    DelaySimulator mockDelaySimulator = mock(DelaySimulator.class);

    CoffeeMachine machine = new CoffeeMachine(mockDelaySimulator);

    @Test
    public void shouldStartMakingCoffee() throws Exception {
        machine.start("latte", Arrays.asList("skim-milk, sugar"), ()->{});
        assertTrue(machine.isBusy());
        verify(mockDelaySimulator).simulate(any(ActionMethod.class), any(ActionMethod.class));
    }

}

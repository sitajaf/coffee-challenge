package services;

import org.coffeeservice.exceptions.CoffeeOrderException;
import org.coffeeservice.models.CoffeeMachine;
import org.coffeeservice.models.DelaySimulator;
import org.coffeeservice.models.Order;
import org.coffeeservice.models.OrderNote;
import org.coffeeservice.services.CoffeeService;
import org.coffeeservice.services.MenuService;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CoffeeServiceTest {
    MenuService mockMenuService = mock(MenuService.class);

    private CoffeeService coffeeService;
    private Order order;

    @Before
    public void setUp() throws Exception {
        coffeeService = new CoffeeService(mockMenuService);
        order = new Order("small", Arrays.asList("skim-milk", "sugar"), 4);
    }

    @Test
    public void shouldStartWithOneMachine() throws Exception {
        assertThat(coffeeService.machines(), is(1));
    }

    @Test
    public void shouldAddAMachine() throws Exception {
        coffeeService.addMachine();
        assertThat(coffeeService.machines(), is(2));
    }

    @Test(expected = CoffeeOrderException.class)
    public void shouldNotPlaceOrderIfCoffeeNoOnMenu() throws Exception {
        final String name = "kawa";
        when(mockMenuService.exists(name)).thenReturn(false);
        coffeeService.order(name, order);
    }

    @Test
    public void shouldPlaceAnOrder() throws Exception {
        final String name = "latte";
        when(mockMenuService.exists(name)).thenReturn(true);

        OrderNote orderNote = coffeeService.order(name, order);
        assertThat(orderNote.getOrder(), is("/order/1"));
    }

    @Test
    public void shouldPlaceAnotherOrder() throws Exception {
        final String name = "latte";
        when(mockMenuService.exists(name)).thenReturn(true);

        coffeeService.order(name, order);
        OrderNote orderNote = coffeeService.order(name, order);
        assertThat(orderNote.getOrder(), is("/order/2"));
    }





}

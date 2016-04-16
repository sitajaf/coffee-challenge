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
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CoffeeServiceTest {
    MenuService mockMenuService = mock(MenuService.class);
    CoffeeMachine mockCoffeeMachine = mock(CoffeeMachine.class);

    final String name = "kawa";
    private CoffeeService coffeeService;
    private Order order;

    @Before
    public void setUp() throws Exception {
        coffeeService = new CoffeeService(mockMenuService, mockCoffeeMachine);
        order = new Order("small", Arrays.asList("skim-milk", "sugar"), 4);
    }

    @Test(expected = CoffeeOrderException.class)
    public void shouldNotPlaceOrderIfCoffeeNoOnMenu() throws Exception {
        when(mockMenuService.exists(name)).thenReturn(false);
        coffeeService.order(name, order);
    }

    @Test
    public void shouldPlaceAnOrder() throws Exception {
        when(mockMenuService.exists(name)).thenReturn(true);

        OrderNote orderNote = coffeeService.order(name, order);
        assertThat(orderNote.getOrder(), is("/order/1"));
    }

    @Test
    public void shouldPlaceAnotherOrder() throws Exception {
        when(mockMenuService.exists(name)).thenReturn(true);

        coffeeService.order(name, order);
        OrderNote orderNote = coffeeService.order(name, order);
        assertThat(orderNote.getOrder(), is("/order/2"));
    }

}

package services;

import org.coffeeservice.exceptions.CoffeeOrderException;
import org.coffeeservice.models.DelaySimulator;
import org.coffeeservice.models.Order;
import org.coffeeservice.models.OrderNote;
import org.coffeeservice.services.CoffeeService;
import org.coffeeservice.services.MenuService;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CoffeeServiceTest {
    MenuService mockMenuService = mock(MenuService.class);
    DelaySimulator mockDelaySimulator = mock(DelaySimulator.class);

    private CoffeeService coffeeService;
    private Order order;

    @Before
    public void setUp() throws Exception {
        coffeeService = new CoffeeService(mockMenuService, mockDelaySimulator);
        order = new Order("small", Arrays.asList("skim-milk", "sugar"), 4);
    }

    @Test
    public void shouldPlaceOrder() throws Exception {
        final String name = "latte";
        when(mockMenuService.exists(name)).thenReturn(true);

        OrderNote orderNote = coffeeService.order(name, order);
        assertTrue(orderNote.getOrder().contains("/order/"));
    }

    @Test(expected = CoffeeOrderException.class)
    public void shouldNotPlaceOrderIfCoffeeNoOnMenu() throws Exception {
        final String name = "kawa";
        when(mockMenuService.exists(name)).thenReturn(false);
        coffeeService.order(name, order);
    }
}

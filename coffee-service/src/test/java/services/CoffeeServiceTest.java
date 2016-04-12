package services;

import org.coffeeservice.exceptions.CoffeeOrderException;
import org.coffeeservice.models.Order;
import org.coffeeservice.models.OrderNote;
import org.coffeeservice.services.CoffeeService;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertTrue;

public class CoffeeServiceTest {
    private CoffeeService coffeeService;
    private Order order;

    @Before
    public void setUp() throws Exception {
        coffeeService = new CoffeeService();
        order = new Order("small", Arrays.asList("skim-milk", "sugar"), 4);

    }

    @Test
    public void shouldPlaceOrder() throws Exception {

        final String pathName = "latte";
        OrderNote orderNote = coffeeService.order(pathName, order);
        assertTrue(orderNote.getOrder().contains("/order/"));

    }

    @Test(expected = CoffeeOrderException.class)
    public void shouldNotPlaceOrderIfCoffeeNoOnMenu() throws Exception {
        coffeeService.order("kawa", order);
    }
}

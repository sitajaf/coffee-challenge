package services;

import org.coffeeservice.exceptions.CoffeeOrderException;
import org.coffeeservice.models.Order;
import org.coffeeservice.models.OrderNote;
import org.coffeeservice.services.CoffeeService;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

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

        final String coffeeName = "latte";
        OrderNote orderNote = coffeeService.order(coffeeName, order);
        assertThat(orderNote, is(new OrderNote(coffeeName, 5)));

    }

    @Test(expected = CoffeeOrderException.class)
    public void shouldNotPlaceOrderIfCoffeeNoOnMenu() throws Exception {
        coffeeService.order("kawa", order);
    }
}

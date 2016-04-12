package services;

import org.coffeeservice.models.OrderNote;
import org.coffeeservice.services.CoffeeService;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class CoffeeServiceTest {
    @Test
    public void shouldPlaceOrder() throws Exception {
        CoffeeService coffeeService = new CoffeeService();

        final String coffeeName = "latte";
        OrderNote orderNote = coffeeService.order(coffeeName, null);
        assertThat(orderNote, is(new OrderNote(coffeeName, 5)));

    }
}

package controllers;

import org.coffeeservice.controllers.CoffeeController;
import org.coffeeservice.models.Order;
import org.coffeeservice.models.OrderNote;
import org.coffeeservice.services.CoffeeService;
import org.coffeeservice.services.MenuService;
import org.junit.Test;

import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

public class CoffeeControllerTest {

    private CoffeeService mockCoffeeService = mock(CoffeeService.class);
    private CoffeeController controller = new CoffeeController(mockCoffeeService);

    @Test
    public void shouldInvokeMenu() throws Exception {
        MenuService mockMenuService = mock(MenuService.class);

        controller.menu(mockMenuService);

        verify(mockMenuService).menu();
    }

    @Test
    public void shouldCreateAnOrder() throws Exception {

        HttpServletResponse mockHttpResponse = mock(HttpServletResponse.class);

        final String latte = "latte";
        OrderNote expectedNote = new OrderNote("/order/234", 5);
        Order order = new Order(latte, Arrays.asList("skim-milk"), 6);

        when(mockCoffeeService.order(latte, order)).thenReturn(expectedNote);

        OrderNote note = controller.order(latte, order, mockHttpResponse);

        assertThat(note, is(expectedNote));
        verify(mockHttpResponse).setStatus(HttpServletResponse.SC_CREATED);
    }
}
package controllers;

import org.coffeeservice.controllers.CoffeeController;
import org.coffeeservice.services.CoffeeService;
import org.coffeeservice.services.MenuService;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class CoffeeControllerTest {
    CoffeeController controller = new CoffeeController();

    @Test
    public void shouldInvokeMenu() throws Exception {
        MenuService mockMenuService = mock(MenuService.class);

        controller.menu(mockMenuService);

        verify(mockMenuService).menu();
    }

    @Test
    public void shouldInvokeOrder() throws Exception {
        CoffeeService mockCoffeeService = mock(CoffeeService.class);

        final String latte = "latte";
        controller.order(latte, mockCoffeeService);

        verify(mockCoffeeService).order(latte);
    }
}
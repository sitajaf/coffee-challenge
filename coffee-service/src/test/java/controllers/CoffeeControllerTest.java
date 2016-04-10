package controllers;

import org.coffeeservice.controllers.CoffeeController;
import org.coffeeservice.services.MenuService;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class CoffeeControllerTest {

    @Test
    public void shouldInvokeMenu() throws Exception {
        MenuService mockMenuService = mock(MenuService.class);
        CoffeeController controller = new CoffeeController();

        controller.menu(mockMenuService);

        verify(mockMenuService).menu();
    }
}
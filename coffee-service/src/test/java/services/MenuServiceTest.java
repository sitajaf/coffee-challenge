package services;

import org.coffeeservice.models.Coffee;
import org.coffeeservice.services.MenuService;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class MenuServiceTest {
    @Test
    public void shouldReturnMenu() throws Exception {
        List<Coffee> expectedMenu = Arrays.asList(
                new Coffee("long black", "/order/long-black", 3.0, 8, 0),
                new Coffee("flat white", "/order/flat-white", 3.5, 5, 2),
                new Coffee("latte", "/order/latte", 3.5, 5, 3),
                new Coffee("espresso", "/order/espresso", 2.0, 10, 0),
                new Coffee("machiato", "/order/machiato", 2.5, 10, 0.5)

        );
        MenuService menuService = new MenuService();

        List<Coffee> menu = menuService.menu();

        assertThat(menu, is(expectedMenu));

    }
}

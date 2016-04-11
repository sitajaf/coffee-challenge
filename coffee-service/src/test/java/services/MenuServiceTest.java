package services;

import org.coffeeservice.exceptions.CoffeeMenuException;
import org.coffeeservice.models.Coffee;
import org.coffeeservice.models.Menu;
import org.coffeeservice.services.MenuService;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class MenuServiceTest {
    @Test
    public void shouldReturnMenu() throws Exception {
        List<Coffee> coffees = Arrays.asList(
                new Coffee("long black", "/order/long-black", 3.0, 8, 0),
                new Coffee("flat white", "/order/flat-white", 3.5, 5, 2),
                new Coffee("latte", "/order/latte", 3.5, 5, 3),
                new Coffee("espresso", "/order/espresso", 2.0, 10, 0),
                new Coffee("machiato", "/order/machiato", 2.5, 10, 0.5)

        );
        Menu expectedMenu = new Menu();
        expectedMenu.setCoffees(coffees);
        MenuService menuService = new MenuService();

        Menu menu = menuService.menu();

        assertThat(menu, is(expectedMenu));
    }

    @Test(expected = CoffeeMenuException.class)
    public void shouldNotAllowNullOrMissingFields() throws Exception {
        MenuService menuService = new MenuService("missing-coffee-name.json");

        menuService.menu();
    }

    @Test(expected = CoffeeMenuException.class)
    public void shouldNotAllowEmptyFields() throws Exception {
        MenuService menuService = new MenuService("empty-coffee-order-path.json");

        menuService.menu();
    }

}

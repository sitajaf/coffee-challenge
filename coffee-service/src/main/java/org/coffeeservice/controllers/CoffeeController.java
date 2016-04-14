package org.coffeeservice.controllers;

import org.coffeeservice.exceptions.CoffeeMenuException;
import org.coffeeservice.exceptions.CoffeeOrderException;
import org.coffeeservice.models.Menu;
import org.coffeeservice.models.Order;
import org.coffeeservice.models.OrderNote;
import org.coffeeservice.services.CoffeeService;
import org.coffeeservice.services.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
public class CoffeeController {

    private CoffeeService coffeeService;

    @Autowired
    public CoffeeController(CoffeeService coffeeService) {
        this.coffeeService = coffeeService;
    }

    @RequestMapping(value = "/menu", produces = {"application/json"})
    public Menu menu(MenuService menuService) throws CoffeeMenuException {
        return menuService.menu();
    }

    @RequestMapping(value = "/order/{coffeeName}", method = RequestMethod.POST,
            consumes = {"application/json"}, produces = {"application/json"})
    public OrderNote order(@PathVariable String coffeeName,
                           @Validated @RequestBody Order order,
                           HttpServletResponse response) throws CoffeeOrderException {
        OrderNote orderNote = coffeeService.order(coffeeName, order);

        if (orderNote != null) {
            response.setStatus(HttpServletResponse.SC_CREATED);
        }
        return orderNote;
    }

}

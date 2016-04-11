package org.coffeeservice.controllers;

import org.coffeeservice.exceptions.CoffeeMenuException;
import org.coffeeservice.models.Menu;
import org.coffeeservice.services.CoffeeService;
import org.coffeeservice.services.MenuService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CoffeeController {

    @RequestMapping(value = "/menu", produces = {"application/json"})
    public Menu menu(MenuService menuService) throws CoffeeMenuException {
        return menuService.menu();
    }


    @RequestMapping(value="/order/{coffeeName}", method = RequestMethod.POST, consumes = {"application/json"})
    public void order(@PathVariable String coffeeName, CoffeeService coffeeService) {
        coffeeService.order(coffeeName);
    }


}

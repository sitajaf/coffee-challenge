package org.coffeeservice.controllers;

import org.coffeeservice.exceptions.CoffeeMenuException;
import org.coffeeservice.models.Coffee;
import org.coffeeservice.services.MenuService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CoffeeController {

    @RequestMapping(value = "/menu")
    public List<Coffee> menu(MenuService menuService) throws CoffeeMenuException {
        return menuService.menu();
    }

}

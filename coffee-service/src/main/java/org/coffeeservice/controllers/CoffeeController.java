package org.coffeeservice.controllers;

import org.coffeeservice.exceptions.CoffeeMenuException;
import org.coffeeservice.models.Coffee;
import org.coffeeservice.services.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CoffeeController {

    private MenuService menuService;

    @Autowired
    public CoffeeController(MenuService menuService) {
        this.menuService = menuService;
    }

    @RequestMapping(value = "/menu")
    public List<Coffee> menu() throws CoffeeMenuException {
        return menuService.menu();
    }

}

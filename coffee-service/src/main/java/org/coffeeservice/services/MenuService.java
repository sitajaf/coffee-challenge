package org.coffeeservice.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NoArgsConstructor;
import org.coffeeservice.exceptions.CoffeeMenuException;
import org.coffeeservice.models.Coffee;
import org.coffeeservice.models.Menu;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.List;

@NoArgsConstructor
@Service
public class MenuService {

    private String menuFile;

    public MenuService(String menuFile) {

        this.menuFile = menuFile;
    }

    public Menu menu() throws CoffeeMenuException {

        if (StringUtils.isEmpty(this.menuFile)) {
            this.menuFile = "coffee-menu.json";
        }

        ObjectMapper mapper = new ObjectMapper();
        try {
            Resource resource = new ClassPathResource(menuFile);
            List<Coffee> coffees = mapper.readValue(resource.getFile(), new TypeReference<List<Coffee>>() {
            });
            verify(coffees);
            Menu menu = new Menu();
            menu.setCoffees(coffees);
            return menu;
        } catch (IOException e) {
            throw new CoffeeMenuException("Error in menu. "+ e.getMessage());
        }
    }

    private void verify(List<Coffee> menu) throws CoffeeMenuException {
        for (Coffee coffee : menu) {
            if (StringUtils.isEmpty(coffee.getName()) || StringUtils.isEmpty(coffee.getOrderPath()) ||
                    StringUtils.isEmpty(coffee.getPrice()) || StringUtils.isEmpty(coffee.getCaffeineLevel()) ||
                    StringUtils.isEmpty(coffee.getMilkRatio())) {
                throw new CoffeeMenuException("Menu has some invalid fields. Ensure all fields have values!");
            }
        }
    }
}


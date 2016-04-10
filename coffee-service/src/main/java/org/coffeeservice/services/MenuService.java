package org.coffeeservice.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NoArgsConstructor;
import org.coffeeservice.exceptions.CoffeeMenuException;
import org.coffeeservice.models.Coffee;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.List;

@NoArgsConstructor
public class MenuService {

    private String menuFile;

    public MenuService(String menuFile) {

        this.menuFile = menuFile;
    }

    public List<Coffee> menu() throws Exception {

        if (StringUtils.isEmpty(this.menuFile)) {
            this.menuFile = "coffee-menu.json";
        }

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_MISSING_CREATOR_PROPERTIES, true);
        try {
            Resource resource = new ClassPathResource(menuFile);
            List<Coffee> menu = mapper.readValue(resource.getFile(), new TypeReference<List<Coffee>>() {
            });
            verify(menu);
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


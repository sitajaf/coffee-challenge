package org.coffeeservice.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.coffeeservice.models.Coffee;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.util.List;

public class MenuService {

    public List<Coffee> menu() throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        try {
            Resource resource = new ClassPathResource("coffee-menu.json");
            List<Coffee> menu = mapper.readValue(resource.getFile(), new TypeReference<List<Coffee>>() {});
            return menu;
        } catch (IOException e) {
            throw e;
        }
    }
}

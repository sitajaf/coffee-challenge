package org.coffeeservice.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class CoffeeController {

    private static final String template = "%d: Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/contracts")
    public String greeting(@RequestParam(value="name", defaultValue="World") String name) {
        return String.format(template, counter.incrementAndGet(), name);
    }

}

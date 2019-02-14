package com.example.hibernate.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rest/hello")
public class HelloResource {

    @GetMapping
    public String helloWorld() {
        return "Hello World";
    }

    @PostMapping("/add")
    public String helloAdd(@RequestBody final String hello) {
        return hello;
    }

    @PutMapping("/put")
    public String helloPut(@RequestBody final String hello) {
        return hello;
    }
}

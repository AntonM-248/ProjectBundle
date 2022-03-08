package com.springboot.springbootdemo.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
    @Value("${fistable}")
    String fistable;

    // Get http method
    // localhost:8080/hello-world
    @GetMapping("/hello-world")
    public String helloWorld() {
        return "Hello World!";
    }

    @GetMapping("/hello-world2")
    public String helloWorld2() {
        return "Hello World Again!";
    }

    @GetMapping("fistable")
    public String fistable() {
        return fistable;
    }
}

package com.example.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class HelloController {

    @GetMapping("/hello-controller/hello")
    String hello(@RequestParam(defaultValue = "world") String name) {
        return "Hello " + name;
    }

    @GetMapping("/hello-controller/do-exception")
    void doException(@RequestParam(defaultValue = "exception") String exceptionText) {
        throw new RuntimeException(exceptionText);
    }

    @GetMapping("/user/hello")
    public String helloUser() {
        return "Hello User";
    }

    @GetMapping("/admin/hello")
    public String helloAdmin() {
        return "Hello Admin";
    }



}

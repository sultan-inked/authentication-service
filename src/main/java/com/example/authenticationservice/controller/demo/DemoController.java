package com.example.authenticationservice.controller.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/demo")
@RestController
public class DemoController {
    @GetMapping("/get")
    public String getMessage() {
        return "Demo message";
    }
}

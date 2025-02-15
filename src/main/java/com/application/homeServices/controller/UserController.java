package com.application.homeServices.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserController {


    @GetMapping("/user-data")
    public String userData() {
        return "This data is accessible to Users .";
    }
}
package com.spring.securityadvanced.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class UserController {
    @GetMapping("/user")
    public String getUser(){
        return "I am User on App";
    }
}

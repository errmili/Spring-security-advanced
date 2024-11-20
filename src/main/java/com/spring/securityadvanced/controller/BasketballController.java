package com.spring.securityadvanced.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("basketball")
public class BasketballController {
    @GetMapping("/start")
    public String start(){
        return "this show basketball controller";
    }
}

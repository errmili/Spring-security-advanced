package com.spring.securityadvanced.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("swimming")
public class SwimmingController {

    @GetMapping("/start")
    public String start(){
        return "this show swimming controller";
    }
}

package com.spring.securityadvanced.controller;


import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/football")
public class FootballController {

    @GetMapping("/start")
    public String start(){
        return "this show football controller";
    }
}

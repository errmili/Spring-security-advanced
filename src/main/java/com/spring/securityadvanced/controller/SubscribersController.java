package com.spring.securityadvanced.controller;


import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("subscribers")
public class SubscribersController {

    @GetMapping("/start")
    public String start(){
        return "this show subscribers controller";
    }
}

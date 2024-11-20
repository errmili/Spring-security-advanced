package com.spring.securityadvanced.controller;


import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("connect")
public class ConnectController {

    @GetMapping("/start")
    public String start(){
        return "this show connect controller";
    }
}

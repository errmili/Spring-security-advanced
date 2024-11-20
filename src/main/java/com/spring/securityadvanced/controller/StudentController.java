package com.spring.securityadvanced.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/student")
public class StudentController {
    @GetMapping("/hi")
    public String hiStudent(){
        return "HI STUDENT";
    }
}

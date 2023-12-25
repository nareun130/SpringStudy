package com.nareun.basic.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nareun.basic.service.HelloService;

@RestController
public class HelloController {

    HelloService helloService;

    public HelloController(HelloService helloService){
        this.helloService = helloService;
    }

    @GetMapping("/hello")
    public String hello(){
        return helloService.printHello();
    }
}

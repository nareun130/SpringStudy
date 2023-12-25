package com.nareun.basic.service;

import org.springframework.stereotype.Service;

@Service
public class HelloService {
    public String printHello(){
        return "Hello";
    }
}

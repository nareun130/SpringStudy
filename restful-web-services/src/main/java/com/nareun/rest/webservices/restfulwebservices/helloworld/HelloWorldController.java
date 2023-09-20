package com.nareun.rest.webservices.restfulwebservices.helloworld;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//REST API
@RestController
public class HelloWorldController {
    // /hello -world

    // @RequestMapping(method = RequestMethod.GET, path = "/hello-world")
    @GetMapping(path = "/hello-world") //~> RequestMapping보다 이걸 추천!
    public String helloworld() {
        return "Hello World";
    }
}

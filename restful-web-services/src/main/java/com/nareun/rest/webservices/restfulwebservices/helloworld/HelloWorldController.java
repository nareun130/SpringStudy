package com.nareun.rest.webservices.restfulwebservices.helloworld;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    

    @GetMapping(path = "/hello-world-bean")
    public HelloWorldBean helloWorldBean() {
        return new HelloWorldBean("Hello World");
    }
    
    //* Path Parameters
    // /users/{id}/todos/{id} => /users/2/todos/200
    // /hello-world/path-variable/{name}

    @GetMapping(path = "/hello-world/path-variable/{name}")
    public HelloWorldBean helloPathVariable(@PathVariable String name){
        return new HelloWorldBean(String.format("Hello World, %s", name));
    }
}

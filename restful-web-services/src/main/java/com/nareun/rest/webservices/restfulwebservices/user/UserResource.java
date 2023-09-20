package com.nareun.rest.webservices.restfulwebservices.user;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserResource {
    private UserDaoService service;

    public UserResource(UserDaoService service) {
        this.service = service;
    }

    // GET /users
    @GetMapping("/users")
    public List<User> retrieveAllUsers() {
        return service.findAll();
    }

    // GET /users/{id}
    @GetMapping("/users/{id}")
    public User retrieveUser(@PathVariable int id) {

        return service.findOne(id);

    }

    // POST /users 
    @PostMapping("/users")
    public void createUser(@RequestBody User user) {// * */ 요청 본문은 ReqeustBody에
        service.save(user);

        //! 리소스 생성할 때는 생성됐다는 상태를 응답으로 보내주는게 좋다.
    }
}

package com.nareun.rest.webservices.restfulwebservices.user;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import java.net.URI;
import java.util.List;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import jakarta.validation.Valid;

@RestController
public class UserResource {

    // !REST API를 만들 때는 항상 소비자 입장에서 만들어야 한다.

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
    // * HATEOAS :데이터 외에도 몇 개의 링크를 반환하여 사용자에게 후속작업을 수행하는 방법을 알려줌.
    // 1. EntityModel : HATEOAS를 사용하여 링크를 추가하려고 EntityModel로 감싸준다.
    // 2. WebMvcLinkBuilder : EntityModel에 링크를 달아주기 위해서
    // http://localhost:8080/users
    @GetMapping("/users/{id}")
    public EntityModel<User> retrieveUser(@PathVariable int id) {
        User user = service.findOne(id);

        if (user == null)
            throw new UserNotFoundException("id : " + id);

        EntityModel<User> entityModel = EntityModel.of(user);

        //~> 이 클래스의 메서드에 해당하는 링크를 붙여준다.
        WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrieveAllUsers());
        entityModel.add(link.withRel("all-usrers"));//링크에 대한 관계 설정

        return entityModel;

    }

    // DELETE /users/{id}
    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id) {
        service.deleteById(id);
    }

    // POST /users
    @PostMapping("/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {// * */ 요청 본문은 ReqeustBody에
        User savedUser = service.save(user);

        // /users/4 -> /users/{id}, user.getId()
        // ? Response에 location 헤더를 붙여준다. -> 저장한 결과를 확인할 수 있는 URL을 준다.
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();
        // ! 리소스 생성할 때는 생성됐다는 상태를 응답으로 보내주는게 좋다.
        return ResponseEntity.created(location).build();
    }
}

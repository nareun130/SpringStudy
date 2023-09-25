package com.nareun.rest.webservices.restfulwebservices.user;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;
import java.util.Optional;

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

import com.nareun.rest.webservices.restfulwebservices.jpa.PostRepository;
import com.nareun.rest.webservices.restfulwebservices.jpa.UserRepository;

import jakarta.validation.Valid;

@RestController
public class UseJpaResource {

    private UserRepository repository;

    private PostRepository postRepository;

    public UseJpaResource(UserRepository repository, PostRepository postRepository) {
        this.repository = repository;
        this.postRepository = postRepository;
    }

    // GET /users
    @GetMapping("/jpa/users")
    public List<User> retrieveAllUsers() {
        // return service.findAll();
        return repository.findAll();
    }

    // GET /users/{id}
    // * HATEOAS :데이터 외에도 몇 개의 링크를 반환하여 사용자에게 후속작업을 수행하는 방법을 알려줌.
    // 1. EntityModel : HATEOAS를 사용하여 링크를 추가하려고 EntityModel로 감싸준다.
    // 2. WebMvcLinkBuilder : EntityModel에 링크를 달아주기 위해서
    // http://localhost:8080/users
    @GetMapping("/jpa/users/{id}")
    public EntityModel<User> retrieveUser(@PathVariable int id) {

        Optional<User> user = repository.findById(id);

        if (user.isEmpty()) // * Optional식 null 체크
            throw new UserNotFoundException("id : " + id);

        EntityModel<User> entityModel = EntityModel.of(user.get());

        // ~> 이 클래스의 메서드에 해당하는 링크를 붙여준다.
        WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrieveAllUsers());
        entityModel.add(link.withRel("all-usrers"));// 링크에 대한 관계 설정

        return entityModel;

    }

    @GetMapping("/jpa/users/{id}/posts/{postId}")
    public EntityModel<Post> retrievePost(@PathVariable int id, @PathVariable int postId) {

        Post post = postRepository.findById(postId).get();
        if (post.getUser().getId() != id)
            throw new RuntimeException("해당 게시물이 존재하지 않습니다 : " + postId);

        EntityModel<Post> entityModel = EntityModel.of(post);

        // ~> 이 클래스의 메서드에 해당하는 링크를 붙여준다.
        WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrievePostsForUser(id));
        entityModel.add(link.withRel("all-posts-user"));// 링크에 대한 관계 설정

        return entityModel;

    }

    // DELETE /users/{id}
    @DeleteMapping("/jpa/users/{id}")
    public void deleteUser(@PathVariable int id) {
        // service.deleteById(id);
        repository.deleteById(id);
    }

    @GetMapping("/jpa/users/{id}/posts")
    public List<Post> retrievePostsForUser(@PathVariable int id) {
        Optional<User> user = repository.findById(id);

        if (user.isEmpty())
            throw new UserNotFoundException("id : " + id);

        return user.get().getPosts();
    }

    // POST /users
    @PostMapping("/jpa/users")
    public ResponseEntity<User> createUser(@RequestBody @Valid User user) {// * 요청 본문은 ReqeustBody에
        User savedUser = repository.save(user);

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

    @PostMapping("/jpa/users/{id}/posts")
    public ResponseEntity<Object> createPostForUser(@PathVariable int id, @Valid @RequestBody Post post) {
        Optional<User> user = repository.findById(id);

        if (user.isEmpty())
            throw new UserNotFoundException("id : " + id);

        post.setUser(user.get());
        Post savedPost = postRepository.save(post);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedPost.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }
}

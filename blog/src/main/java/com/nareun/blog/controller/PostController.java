package com.nareun.blog.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nareun.blog.model.Post;
import com.nareun.blog.service.PostService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor // ? lombok쓰면 생성자 방식 없이 이렇게 적용 가능
public class PostController {

    private final PostService postService;

    // public PostController(PostService postService) {
    // this.postService = postService;
    // }

    @GetMapping("/list")
    public List<Post> postList(@RequestParam(value = "title") String title) {
        // value 안 달아주면 에러 발생 
        return postService.postList(title);
    }

    @GetMapping("/{postId}")
    public Post postDetail(@PathVariable String postId) {
        return new Post(UUID.randomUUID(), "title", "contents1", postId, LocalDateTime.now());
    }

    @GetMapping("/update") // update는 put을 씀
    public String updatePost(@RequestParam String postId) {
        return postId + "블로그 수정 페이지";
    }

}

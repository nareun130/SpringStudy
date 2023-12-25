package com.nareun.basic.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nareun.basic.model.Post;

@RestController
@RequestMapping("/posts")
public class PostController {

    @GetMapping("/list")
    public List<Post> postLists() {
        // return "포스트 목록";
        return List.of(
                new Post("1", "title1", "contents1", "", LocalDateTime.now()),
                new Post("2", "title2", "contents2", "", LocalDateTime.now()),
                new Post("3", "title3", "contents3", "", LocalDateTime.now()));
    }

    @GetMapping("/{postId}")
    public Post postDetail(@PathVariable String postId) {
        return new Post("1", "title", "contents1", postId, LocalDateTime.now());
    }

    @GetMapping("/update") // update는 put을 씀
    public String updatePost(@RequestParam String postId) {
        return postId + "블로그 수정 페이지";
    }

}

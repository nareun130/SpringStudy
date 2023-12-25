package com.nareun.blog.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.nareun.blog.model.Post;
import com.nareun.blog.repository.PostRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostService {
	
    private final PostRepository postRepository;
    public List<Post> postList() {
         return postRepository.findAll();
    }
}

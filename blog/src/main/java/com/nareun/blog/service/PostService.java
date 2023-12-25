package com.nareun.blog.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.nareun.blog.mapper.PostMapper;
import com.nareun.blog.model.Post;
import com.nareun.blog.repository.PostRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostService {
	
    private final PostRepository postRepository;
    private final PostMapper postMapper;
    public List<Post> postList() {
        //  return postRepository.findAll();
        return postMapper.listPost();
        //! Jpa와 mybatis를 섞어 쓸 수는 있지만 트랜잭션 생각해야함. -> 고급과정 
    }
}

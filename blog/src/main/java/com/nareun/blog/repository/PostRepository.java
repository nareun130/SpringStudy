package com.nareun.blog.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nareun.blog.model.Post;


@Repository// JpaRepository구현하면 생략 가능
public interface PostRepository extends JpaRepository<Post,UUID>{
    
}

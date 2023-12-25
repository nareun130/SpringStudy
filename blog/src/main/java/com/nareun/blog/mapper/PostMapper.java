package com.nareun.blog.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

//post.xml의 resultType의 패키지명을 참조
import com.nareun.blog.model.Post;

@Mapper
public interface PostMapper { // post.xml의 namespace 맞춰주기
    // 메서드명이 post.xml의 select문 id를 참조
    public List<Post> listPost();
}
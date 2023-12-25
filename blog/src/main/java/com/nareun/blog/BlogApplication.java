package com.nareun.blog;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.nareun.blog.model.Post;
import com.nareun.blog.repository.PostRepository;

import lombok.RequiredArgsConstructor;

@SpringBootApplication
@RequiredArgsConstructor
public class BlogApplication implements CommandLineRunner {

	private final PostRepository postRepository;

	public static void main(String[] args) {
		SpringApplication.run(BlogApplication.class, args);
	}

	//* Command LineRunner를 구현 함으로써 서버 시작 전 세팅 가능
	@Override
	public void run(String... args) throws Exception {
		List<Post> postList = List.of(
				new Post(UUID.randomUUID(), "title1", "contents1", "", LocalDateTime.now()),
				new Post(UUID.randomUUID(), "title2", "contents2", "", LocalDateTime.now()),
				new Post(UUID.randomUUID(), "title3", "contents3", "", LocalDateTime.now()));
		postRepository.saveAll(postList);
	}

}

package com.nareun.rest.webservices.todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nareun.rest.webservices.todo.Todo;
import java.util.List;


public interface TodoRepository extends JpaRepository<Todo, Integer> {
    List<Todo> findByUsername(String username);
}

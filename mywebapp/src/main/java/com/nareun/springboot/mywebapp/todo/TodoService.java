package com.nareun.springboot.mywebapp.todo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class TodoService {
    private static List<Todo> todos = new ArrayList<>();

    // ! 정적 변수를 초기화 하기 위해 static 블럭이 필요 -> 딱 한 번 수행
    static {
        todos.add(new Todo(1, "nareun", "Learn AWS", LocalDate.now().plusYears(1), false));
        todos.add(new Todo(1, "nareun", "Learn DevOps", LocalDate.now().plusYears(2), false));
        todos.add(new Todo(1, "nareun", "Learn Fullstack DEvelopment", LocalDate.now().plusYears(3), false));
    }

    public List<Todo> findByUserName(String username) {

        return todos;
    }

}

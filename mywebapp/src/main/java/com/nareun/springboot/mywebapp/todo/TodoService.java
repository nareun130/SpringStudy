package com.nareun.springboot.mywebapp.todo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Service;

@Service
public class TodoService {
    private static List<Todo> todos = new ArrayList<>();

    private static int todosCount = 0;

    // ! 정적 변수를 초기화 하기 위해 static 블럭이 필요 -> 딱 한 번 수행
    static {
        todos.add(new Todo(++todosCount, "nareun", "Learn AWS", LocalDate.now().plusYears(1), false));
        todos.add(new Todo(++todosCount, "nareun", "Learn DevOps", LocalDate.now().plusYears(2), false));
        todos.add(new Todo(++todosCount, "nareun", "Learn Fullstack DEvelopment", LocalDate.now().plusYears(3), false));
    }

    public List<Todo> findByUserName(String username) {

        return todos;
    }

    public void addTodo(String username, String description, LocalDate targetDate, boolean done) {
        Todo todo = new Todo(++todosCount, username, description, targetDate, done);
        todos.add(todo);
    }

    public void deleteById(int id) {
        // * predicate는 조건 -> todo.getId()==id */
        // ? 람다식 : todo -> todo.getId() == id
        Predicate<? super Todo> predicate = todo -> todo.getId() == id;
        todos.removeIf(predicate);
    }
}

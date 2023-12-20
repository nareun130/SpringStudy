package com.example.todo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.todo.domain.Todo;
import com.example.todo.repository.TodoRepository;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class TodoController {

    private final TodoRepository todoRepository;

    @GetMapping("/")
    public String index(Model model){
        List<Todo> todos = todoRepository.findAll();
        model.addAttribute("todos",todos);
        return "todos";
    }

    @PostMapping("/addTodo")
    public String addTodo(@RequestParam("todo" ) String todo){

        Todo toDo = new Todo();
        toDo.setTodo(todo);
        todoRepository.save(toDo);
        return "redirect:/";
    }
}

package com.nareun.springboot.mywebapp.todo;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("name")
public class TodoController {

    private TodoService todoService;

    public TodoController(TodoService todoService) {
        super();
        this.todoService = todoService;
    }

    // /list-todos
    @RequestMapping("list-todos")
    public String listAllTodos(ModelMap model) {
        List<Todo> todos = todoService.findByUserName("nareun");
        model.addAttribute("todos", todos);
        return "listTodos";
    }
}

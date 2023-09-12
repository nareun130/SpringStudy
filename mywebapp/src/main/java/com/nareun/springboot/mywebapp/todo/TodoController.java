package com.nareun.springboot.mywebapp.todo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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

    // GET,POST ~> POST
    @RequestMapping(value = "add-todo", method = RequestMethod.GET)
    public String showNewTodoPage() {
        return "todo";
    }

    @RequestMapping(value = "add-todo", method = RequestMethod.POST)
    public String addNewTodoPage(@RequestParam String description, ModelMap model) {
        String username = (String) model.get("name");
        todoService.addTodo(username, description, LocalDate.now().plusYears(1), false);

        // ? 여기서 Model을 받아서 todo에 추가하고 listTodos를 호출하면 아무런 model이 없는 채로 view만 호출
        // * */-> 리디렉션 사용
        return "redirect:list-todos";
        // return "listTodos";
    }
}

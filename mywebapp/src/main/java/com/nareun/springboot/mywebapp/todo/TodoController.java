package com.nareun.springboot.mywebapp.todo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import jakarta.validation.Valid;

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

    // GET,POST ~> GET
    @RequestMapping(value = "add-todo", method = RequestMethod.GET)
    public String showNewTodoPage(ModelMap model) {
        // * */todo등록 페이지에서 todo객체가 있어야 등록이 가능하므로 임시객체를 만들어서 Model에 넣어준다.
        String username = (String) model.get("name");
        // Todo //여기서 username과 false만 직접 만들어준 값이 들어가지 않아서 form에 hiddend로 무조건 넣어줘야 하나?
        // ! Http 포로토콜의 비연결성 특성 때문 : 이전 요청처리에 대한 값을 다음 요청에서도 연속성을 갖기 위해 사용. -> 세션 등으로 대체
        // 가능
        Todo todo = new Todo(0, username, "Default Desc", LocalDate.now().plusYears(1), false);
        model.put("todo", todo);
        return "todo";
    }

    @RequestMapping(value = "add-todo", method = RequestMethod.POST) 
    public String addNewTodo(ModelMap model, @Valid Todo todo, BindingResult result) {// ! jsp에서도 양식 보조객체를 설정해 줘야 한다.
        //* */ 윻성 검사를 위해 @Valid를 추가, 유효성 검사에 걸릴 때 BindingResult에 error를 담아줌.
        if (result.hasErrors()) {
            return "todo";
        }

        // * */ todo 등록 페이지에 필드가 많으면 계속 RequestParam으로 가져와서 필드를 검사해야한다.
        // ~> Todo에 직접 바인딩? description값을 직접 Todo.description에 바인딩,
        // ~> localdate나 done같은 필드를 추가하게 되어도 Bean에 바운딩 될 것이다.
        // ~> 커맨드 빈 (Todo또는 Todo Bean을 보조객체 or 커맨드 빈으로 사용)

        String username = (String) model.get("name");
        todoService.addTodo(username, todo.getDescription(), LocalDate.now().plusYears(1), false);

        // ? 여기서 Model을 받아서 todo에 추가하고 listTodos를 호출하면 아무런 model이 없는 채로 view만 호출
        // * */-> 리디렉션 사용
        return "redirect:list-todos";
        // return "listTodos";
    }
}

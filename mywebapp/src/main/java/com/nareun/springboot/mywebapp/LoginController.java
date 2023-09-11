package com.nareun.springboot.mywebapp;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    // / login => com.nareun.springboot.mywebapp.login.LoginController =>login.jsp

    // http:localhost:8080/login?name=nareun
    //* */ Model에다 param값을 넣어서 뷰에서 다룰 것이다.
    @RequestMapping("login")
    public String gotoLoginPage(@RequestParam String name, ModelMap model) {
        model.put("name", name);
        System.out.println("Request param is " + name);//! 프로덕션 어플리케이션에서는 System.out을 쓰지 말자!! ~> 로깅으로 대체
        return "login";

    }
}

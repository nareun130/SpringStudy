package com.nareun.springboot.mywebapp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    // ~> 가장 좋은 방식은 Logger를 이용 -> 로그 수준 설정 가능

    private Logger logger = LoggerFactory.getLogger(getClass());

    // / login => com.nareun.springboot.mywebapp.login.LoginController =>login.jsp

    // http:localhost:8080/login?name=nareun
    // * */ Model에다 param값을 넣어서 뷰에서 다룰 것이다.
    @RequestMapping("login")
    public String gotoLoginPage(@RequestParam String name, ModelMap model) {
        model.put("name", name);
        logger.debug("Request param is {}", name); //level 1
        logger.info("I want this printed at info level", name); //level2
        logger.warn("I want this printed at warn level", name);  //level3
        System.out.println("Request param is " + name);// !권장 X : 프로덕션 어플리케이션에서는 System.out을 쓰지 말자!! -> 로그 수준이 제어 x

        return "login";

    }
}

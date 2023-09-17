package com.nareun.springboot.mywebapp.login;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("name") // * model에 들어간 name 값을 사용하기 위해 SessionAttributes에 name을 설정 ~> 이 값을 사용하는 모든
                           // 컨트롤러에 붙여야한다. */
public class WelcomeController {
    // login
    // GET, POST -> GET
    @RequestMapping(value = "/", method = RequestMethod.GET) // ~> GET 메서드만 처리
    public String gotoWelcomePage(ModelMap model) {

        model.put("name", "nareun");
        return "welcome";
    }

    //~> Spring Security로 로그인 로직 처리 ->기존 loginPage 삭제
    
}

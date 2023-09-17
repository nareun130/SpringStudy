package com.nareun.springboot.mywebapp.login;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
        // ! 만일 사용자가 메인에서 todolist를 들어가는 것이 아닌 list-todos라는 url을 입력하여 로그인 하면
        // ! 여기 로직을 타지 않으므로 list가 나오지 않는다. ~> @SessionAttributes("name") 때문에
        // ! => 항상 SpringSecurity로부터 직접 값을 받는 게 좋다!!
        model.put("name", getLoggedinUsername());
        return "welcome";
    }

    // ~> Spring Security로 로그인 로직 처리 ->기존 loginPage 삭제

    // ? 로그인한 사용자의 username 리턴
    private String getLoggedinUsername() {
        // * */ 인증된 주체를 가져옴.
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }
}

package com.nareun.springboot.mywebapp.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("name") // * model에 들어간 name 값을 사용하기 위해 SessionAttributes에 name을 설정 ~> 이 값을 사용하는 모든
                           // 컨트롤러에 붙여야한다. */
public class LoginController {

    private AuthenticationService authenticationService;

    // * */ = new AuthenticationService(); -> 이 방식 대신 생성자 주입 방식으로
    public LoginController(AuthenticationService authenticationService) {
        super();
        this.authenticationService = authenticationService;
    }

    // login
    // GET, POST -> GET
    @RequestMapping(value = "login", method = RequestMethod.GET) // ~> GET 메서드만 처리
    public String gotoLoginPage() {
        return "login";
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String gotoWelcomePage(@RequestParam String name, @RequestParam String password, ModelMap map) {

        // 인증 ~> 성공하면 welcome페이지, 실패면 로그인 페이지로 리디렉션
        // name - nareun
        // pasword - dummy
        // ! 단일 책임 원칙으로 별도의 인증 클래스 생성해준다.
        if (authenticationService.authenticate(name, password)) {
            map.put("name", name);

            return "welcome";
        }
        map.put("errorMessage", "Invalid Credentials! Please try again!");
        return "login";
    }
}

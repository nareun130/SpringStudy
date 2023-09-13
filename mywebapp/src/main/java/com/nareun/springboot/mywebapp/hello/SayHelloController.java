package com.nareun.springboot.mywebapp.hello;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SayHelloController {

    // "say-hello" => "Hello! What are you learning today?"
    @RequestMapping("say-hello")
    @ResponseBody // *메시지가 리턴한 것을 그대로 브라우저에 리턴 */
    public String sayHello() {

        // ~> SpringMVC는 문자열 리턴 시 기본적으로 뷰를 검색한다.
        return "Hello! What are you learning today?";
    }

    @RequestMapping("say-hello-html")
    @ResponseBody // *메시지가 리턴한 것을 그대로 브라우저에 리턴 */
    public String sayHelloHtml() {
        StringBuilder sb = new StringBuilder();
        sb.append("<html>");
        sb.append("<head>");
        sb.append("<title>MyHtmlPage-Changed</title>");
        sb.append("</head>");
        sb.append("<body>");
        sb.append("My html page with body-Changed ");
        sb.append("</body>");
        sb.append("</html>");

        return sb.toString();
    }

    // say-hello-jsp => sayHello.jsp
    // /src/main/resources/META-INF/resources/WEB-INF/jsp/sayHello.jsp
    // /src/main/resources/META-INF/resources/WEB-INF/jsp/welcome.jsp
    // /src/main/resources/META-INF/resources/WEB-INF/jsp/login.jsp
    // /src/main/resources/META-INF/resources/WEB-INF/jsp/todos.jsp
    //~> 일정한 pattern을 application.properties에 설정
    @RequestMapping("say-hello-jsp")
    // @ResponseBody//* view를 보여주려면 없어야 한다. */
    public String sayHelloJsp() {
        return "sayHello";
    }
}

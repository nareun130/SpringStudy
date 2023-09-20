package com.nareun.rest.webservices.restfulwebservices.helloworld;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

//REST API
@RestController
public class HelloWorldController {

    private MessageSource messageSource;

    public HelloWorldController(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    // @RequestMapping(method = RequestMethod.GET, path = "/hello-world")
    @GetMapping(path = "/hello-world") // ~> RequestMapping보다 이걸 추천!
    public String helloworld() {
        return "Hello World";
    }

    @GetMapping(path = "/hello-world-bean")
    public HelloWorldBean helloWorldBean() {
        return new HelloWorldBean("Hello World");
    }

    // * Path Parameters
    // /users/{id}/todos/{id} => /users/2/todos/200
    // /hello-world/path-variable/{name}

    @GetMapping(path = "/hello-world/path-variable/{name}")
    public HelloWorldBean helloPathVariable(@PathVariable String name) {
        return new HelloWorldBean(String.format("Hello World, %s", name));
    }

    // ? 국제화된 값을 호출하는 메서드
    @GetMapping(path = "/hello-world-internationalized")
    public String helloworldInternationalized() {

        // *현재 스레드와 관련된 로케일(헤더에서 추출) 반환, 그렇지 않으면 시스템 기본 로케일 반환 */
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage("good.morning.message", null, "Default Message", locale);
        // return "Hello World V2";
    }

    // 1: good.morning.message - messages.properties
    // 2: messages.properties의 파일에다 각 나라의 값을 설정
}

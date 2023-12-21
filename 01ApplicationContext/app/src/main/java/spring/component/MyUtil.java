package spring.component;

import org.springframework.stereotype.Component;

//? AnnotationConfigApplicationContext가 자동으로 스캔
@Component(value = "myUtil")// 이게 id가 된다.
public class MyUtil {
    public MyUtil() {
        System.out.println("MyUtil()");
    }

    public void print() {
        System.out.println("MyUtiil.Print()");
    }
}

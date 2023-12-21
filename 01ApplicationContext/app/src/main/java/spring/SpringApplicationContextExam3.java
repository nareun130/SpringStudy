package spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import exam.Book;
import spring.component.MyDao;
import spring.component.MyService;
import spring.component.MyUtil;

public class SpringApplicationContextExam3 {
    public static void main(String[] args) {
        // ! 명시해준 package이하를 전부 스캔
        //AnnotationConfigApplicationContext : @Component가 붙은 클래스들을 스캔
        ApplicationContext context = new AnnotationConfigApplicationContext("spring");
        Book book1 = context.getBean("book1", Book.class);
        book1.setTitle("즐거운 SpringBoot");
        book1.setPrice(5000);

        System.out.println(book1.getTitle());
        System.out.println(book1.getPrice());

        Book book11 = context.getBean("book1", Book.class);
        System.out.println(book11.getTitle());
        System.out.println(book11.getPrice());

        Book book2 = context.getBean("book2", Book.class);
        System.out.println(book2.getTitle());
        System.out.println(book2.getPrice());

        MyDao myDao = context.getBean("myDao", MyDao.class);
        myDao.get();

        MyService myService = context.getBean("myService2", MyService.class);
        myService.service();

        // MyUtil myUtil = context.getBean(MyUtil.class);
        MyUtil myUtil = context.getBean("myUtil", MyUtil.class);
        myUtil.print();
    }
}

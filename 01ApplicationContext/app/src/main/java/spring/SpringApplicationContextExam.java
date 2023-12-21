package spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import exam.Book;

public class SpringApplicationContextExam {
    public static void main(String[] args) {
        // 인터페이스 타입으로 인스턴스 참조
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        Book book1 = (Book) context.getBean("book1");
        book1.setTitle("즐거운 Spring Boot");
        book1.setPrice(5000);

        System.out.println(book1.getTitle());
        System.out.println(book1.getPrice());

        //* 강제 형변환 없이도 객체 불러오기 가능
        // Book book2 = context.getBean("book1",Book.class);
        Book book2 = context.getBean("book2",Book.class);
        System.out.println(book2.getTitle());
    }
}

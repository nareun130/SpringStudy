package spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import exam.Book;
import spring.component.MyDao;
import spring.component.MyService;
import spring.component.MyUtil;

//Java Config설정 : AnnotationConfigApplicationContext가 읽어들임
//ApplicationConfig 인스턴스를 만든다.
@Configuration // @Componet를 내장함. -> 나중에 직접 어노테이션을 제작하여 관리하는 경우도 있음.
public class ApplicationConfig {

    public ApplicationConfig() {
        System.out.println("ApplicationConfig()");
    }

    // <bean id="book1" class="exam.Book"></bean>
    // 메소드 이름 : id
    @Bean
    public Book book1() {
        return new Book();// exam.Book
    }

    /*
     * <bean id="book2" class="exam.Book">
     * <property name="title" value="즐거운 자바"></property>
     * <property name="price" value="5000"></property>
     * </bean>
     */
    @Bean
    public Book book2() {
        Book book = new Book();
        book.setTitle("즐거운 자바");
        book.setPrice(9000);
        return book;
    }

    // <bean id="myDao" class="spring.component.MyDao"></bean>
    @Bean
    public MyDao myDao() {
        return new MyDao();
    }

    /*
     * <bean id="myService" class="spring.component.MyService">
     * <!-- name : setMyDao ref: 밑의 myDao bean -->
     * <property name="myDao" ref="myDao"></property>
     * </bean>
     * <bean id="myDao" class="spring.component.MyDao"></bean>
     */
    @Bean(name = "myService2") // ~> myService2가 아이디가 됨.
    public MyService myService(MyDao myDao) {
        MyService myService = new MyService();
        myService.setMyDao(myDao);
        return myService;
    }

    // @Bean
    // public MyUtil myUtil() {
    //     return new MyUtil();
    // }
}

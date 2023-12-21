package spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import exam.Book;
import spring.component.MyDao;
import spring.component.MyService;

public class SpringApplicationContextExam2 {
    public static void main(String[] args) {
        //classpath로부터 설정을 가져옴.
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        MyService myService = context.getBean("myService", MyService.class);
        MyDao myDao = context.getBean("myDao", MyDao.class);
        myService.setMyDao(myDao);
        myService.service();
    }
}

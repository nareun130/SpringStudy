## Spring framework 아키텍처 다이어그램

![width:500px](image-1.png)
가장 핵심은 Core Container

## ApplicationContext
- 스프링의 핵심 인터페이스 
- 대표적인 구현 클래스로 ClassPathXmlApplicationContext
    - classpath로부터 설정을 가져옴. -> classpath는 main의 java와 resources 폴더

## build.gradle 추가

-   implementation group: 'org.springframework', name: 'spring-context',version: '6.1.2'

위에 것만 추가해주면 Core Container의 모든 것을 가져온다.

## applicationContext.xml을 이용한 bean 등록


개발자가 직접 setter를 이용하여 주입
```
MyService myService = context.getBean("myService", MyService.class);
        MyDao myDao = context.getBean("myDao", MyDao.class);
        myService.setMyDao(myDao);
```
아래와 같음.

Spring 설정으로 인한 주입
~~~
    <bean id="myService" class="spring.component.MyService">
        <!-- setMyDao -->
        <property name="myDao" ref="myDao"></property>
    </bean>
    <bean id="myDao" class="spring.component.MyDao"></bean>
~~~

# Annontation을 이용한 Container
xml의 불편함 -> annotation을 이용한 컨테이너방식 등장

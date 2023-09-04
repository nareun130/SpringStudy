package com.in28minutes.learnspringframework.test;

import java.io.Serializable;

class Pojo {
    //? POJO :일반적인 오래된 Java 객체 -> 모든 SpringBean과 classs는 POJO 기반
    private String text;

    private int number;

    public String toString() {
        return text + ":" + number;
    }
}
//3. Serializable을 구현해야 함.
class JavaBean implements Serializable{// EJB때문에 조금 복잡함.
    private String text;
    private int number;
    //?  JavaBean 3가지 조건
    //1. 인수생성자 X
    public JavaBean(){

    }
    //2. getter와 setter가 있어야 한다.
    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }
    public int getNumber() {
        return number;
    }
    public void setNumber(int number) {
        this.number = number;
    }
    
}

public class SpringBeanVsJavaBean {
    public static void main(String[] args) {
        Pojo pojo = new Pojo();

        System.out.println(pojo);
    }
}

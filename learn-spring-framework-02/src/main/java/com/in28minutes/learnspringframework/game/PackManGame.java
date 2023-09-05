package com.in28minutes.learnspringframework.game;

import org.springframework.stereotype.Component;

@Component //* Spring이 자동으로 Bean 생성을 할 수 있도록 어노테이션 추가 -> Spring에게 이 컴포넌트를 찾는 곳을 알려줘야함. */
public class PackManGame implements GamingConsole {

    @Override
    public void up() {
        System.out.println("up");
    }

    @Override
    public void down() {
        System.out.println("down");
    }

    @Override
    public void left() {
        System.out.println("left");

    }

    @Override
    public void right() {
        System.out.println("right");

    }

}

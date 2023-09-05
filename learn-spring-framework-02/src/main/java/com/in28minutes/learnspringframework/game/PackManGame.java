package com.in28minutes.learnspringframework.game;

import org.springframework.stereotype.Component;

@Component //* Spring이 자동으로 Bean 생성을 할 수 있도록 어노테이션 추가 -> Spring에게 이 컴포넌트를 찾는 곳을 알려줘야함. */
//~> 여러 GamingConsole구현 클래스에 @Component가 있으면 에러 => Unique한 Bean이 없어서 scan에서 package를 기준으로 찾기 때문.
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

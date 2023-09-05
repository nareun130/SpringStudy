package com.in28minutes.learnspringframework.game;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
// @Qualifier("SuperContraGameQualifier")//~> Mario에 @Primary가 적용되어 있으므로 SuperContraGame을 실행시키기 위해
public class SuperContraGame implements GamingConsole{ 
    //? @Qualifier가 없으면 주입 하는 부분에서 @Qualififer("bean 이름으로 호출 가능")


    public void up() {
        System.out.println("Up");
    }

    public void down() {
        System.out.println("Sit Down");
    }

    public void left() {
        System.out.println("Go back");
    }

    public void right() {
        System.out.println("Shoot a Bullet");
    }


}

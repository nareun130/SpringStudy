package com.in28minutes.learnspringframework.game;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class GameRunner {

    GamingConsole game;

    //~> @Qualifier로 특정 bean을 주입.
    // public GameRunnder(@Qualifier("SuperContraGameQualifier") GamingConsole game) {
    public GameRunner(@Qualifier("superContraGame") GamingConsole game) {
        //? bean 클래스에 @Qualifier가 없다면 bean 이름으로 사용가능
        this.game = game;
    }

    public void run() {
        System.out.println("Running game: " + game);
        game.up();
        game.down();
        game.left();
        game.right();
    }

}

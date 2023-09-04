package com.in28minutes.learnspringframework;

import com.in28minutes.learnspringframework.game.GameRunnder;
import com.in28minutes.learnspringframework.game.PackManGame;

public class App01GamingBasicJava {
    public static void main(String[] args) { 
        var game = new PackManGame(); //1. 객체 생성
        var gameRunnder = new GameRunnder(game);// 2. 객체 생성 + 의존성 연결 
        //? -> PackManGame이 객체가 생성되고 GameRunner 객체에 주입되거나 결합
        //* */ GameConsole이 GameRunner의 의존성
        gameRunnder.run();
    }

}
 
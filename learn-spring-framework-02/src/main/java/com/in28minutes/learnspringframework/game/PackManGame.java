package com.in28minutes.learnspringframework.game;

import com.in28minutes.learnspringframework.game.GamingConsole;

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

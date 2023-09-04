package com.in28minutes.learnspringframework;

public class AppGamingBasicJava {
    public static void main(String[] args) {

        var marioGame = new MarioGame();
        var gameRunnder = new GameRunnder(marioGame);
        gameRunnder.run();
    }

}

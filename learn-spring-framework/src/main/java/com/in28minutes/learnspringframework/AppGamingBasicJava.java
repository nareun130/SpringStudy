package com.in28minutes.learnspringframework;

public class AppGamingBasicJava {
    public static void main(String[] args) {

        // var marioGame = new MarioGame();
        var superContraGame = new SuperContraGame();
        var gameRunnder = new GameRunnder(superContraGame );
        gameRunnder.run();
    }

}
 
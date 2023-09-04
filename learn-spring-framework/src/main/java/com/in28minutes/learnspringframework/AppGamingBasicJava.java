package com.in28minutes.learnspringframework;

import com.in28minutes.learnspringframework.game.GameRunnder;
import com.in28minutes.learnspringframework.game.SuperContraGame;

public class AppGamingBasicJava {
    public static void main(String[] args) {

        // var marioGame = new MarioGame();
        var superContraGame = new SuperContraGame();
        var gameRunnder = new GameRunnder(superContraGame );
        gameRunnder.run();
    }

}
 
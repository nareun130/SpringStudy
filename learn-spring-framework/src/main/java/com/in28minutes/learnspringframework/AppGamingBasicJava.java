package com.in28minutes.learnspringframework;

import com.in28minutes.learnspringframework.game.GameRunnder;
import com.in28minutes.learnspringframework.game.MarioGame;
import com.in28minutes.learnspringframework.game.SuperContraGame;

public class AppGamingBasicJava {
    public static void main(String[] args) { 
        var game = new MarioGame();
        // var game = new SuperContraGame();
        var gameRunnder = new GameRunnder(game);
        gameRunnder.run();
    }

}
 
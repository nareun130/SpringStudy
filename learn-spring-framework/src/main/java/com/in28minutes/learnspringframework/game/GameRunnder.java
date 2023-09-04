package com.in28minutes.learnspringframework.game;

import com.in28minutes.learnspringframework.GamingConsole;

public class GameRunnder {
    // MarioGame game;
    GamingConsole game;

    // public GameRunnder(MarioGame game) {
    public GameRunnder(GamingConsole game) {
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

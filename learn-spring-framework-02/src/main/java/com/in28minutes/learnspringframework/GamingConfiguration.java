package com.in28minutes.learnspringframework;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.in28minutes.learnspringframework.game.GameRunnder;
import com.in28minutes.learnspringframework.game.GamingConsole;
import com.in28minutes.learnspringframework.game.PackManGame;

@Configuration
public class GamingConfiguration {

    @Bean
    public GamingConsole game(){
        var game = new PackManGame(); //* packman뿐만이 아닌 mario나 다른 게임도 받아 주려면 어떻게 해야할까? */
        return game;
    }

    // @Bean
    // @Primary
    // public GameRunnder gameRunnder(GamingConsole game){
    //     var gameRunner = new GameRunnder(game); 
    //     return gameRunner;
    // }
    
    //* */ game 메서드를 직접 호출도 가능 
    @Bean
    public GameRunnder gameRunnerMethod(){
        return new GameRunnder(game());
    }
}

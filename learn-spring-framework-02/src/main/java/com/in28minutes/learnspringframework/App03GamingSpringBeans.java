package com.in28minutes.learnspringframework;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.in28minutes.learnspringframework.game.GameRunnder;
import com.in28minutes.learnspringframework.game.GamingConsole;
import com.in28minutes.learnspringframework.game.PackManGame;

@Configuration
class GamingConfiguration {
    //!수동으로 Bean 생성 -> Spring이 자동으로 Bean 생성하도록 변환!
    @Bean
    public GamingConsole game(){
        var game = new PackManGame(); //* packman뿐만이 아닌 mario나 다른 게임도 받아 주려면 어떻게 해야할까? */
        return game;
    }

    @Bean
    public GameRunnder gameRunnder(GamingConsole game){
        var gameRunnder = new GameRunnder(game);
        return gameRunnder;
    
    }
}

public class App03GamingSpringBeans {
    public static void main(String[] args) {

        try (var context = new AnnotationConfigApplicationContext(GamingConfiguration.class)) {
            context.getBean(GamingConsole.class).up();

            context.getBean(GameRunnder.class).run();
        }

        
    }

}

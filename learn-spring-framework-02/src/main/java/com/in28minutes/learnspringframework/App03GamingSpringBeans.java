package com.in28minutes.learnspringframework;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.in28minutes.learnspringframework.game.GameRunnder;
import com.in28minutes.learnspringframework.game.GamingConsole;
import com.in28minutes.learnspringframework.game.PackManGame;

@Configuration
public class App03GamingSpringBeans { 
    //?App 자체를 Config와 합침. -> 여기까지도 수동 Bean 생성
    //~> 이제 한 번 특정 클래스의 인스턴스 생성을 Spring에 요청하도록 바꿔보자.
    @Bean
    public GamingConsole game(){
        var game = new PackManGame();
        return game;
    }

    @Bean
    public GameRunnder gameRunnder(GamingConsole game){
        var gameRunnder = new GameRunnder(game);
        return gameRunnder;
    
    }
    public static void main(String[] args) {

        try (var context = new AnnotationConfigApplicationContext(App03GamingSpringBeans.class)) {
            context.getBean(GamingConsole.class).up();

            context.getBean(GameRunnder.class).run();
        }

        
    }

}

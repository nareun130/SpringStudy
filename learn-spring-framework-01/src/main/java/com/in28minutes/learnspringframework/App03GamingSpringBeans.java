package com.in28minutes.learnspringframework;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.in28minutes.learnspringframework.game.GameRunnder;
import com.in28minutes.learnspringframework.game.GamingConsole;

public class App03GamingSpringBeans {
    public static void main(String[] args) {

        try (var context = new AnnotationConfigApplicationContext(GamingConfiguration.class)) {
            context.getBean(GamingConsole.class).up();

            context.getBean(GameRunnder.class).run();
        }

        
    }

}

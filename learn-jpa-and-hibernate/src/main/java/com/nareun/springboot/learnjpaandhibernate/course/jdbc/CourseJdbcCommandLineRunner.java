package com.nareun.springboot.learnjpaandhibernate.course.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

//? 앱 시작 시 실행하게 해주기 위해서 CommandLineRunner 구현
@Component
public class CourseJdbcCommandLineRunner implements CommandLineRunner{

    @Autowired
    private CourseJdbcRepository repository;
    @Override
    public void run(String... args) throws Exception {
        repository.insert();
    }
    
}

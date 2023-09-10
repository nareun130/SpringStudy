package com.nareun.springboot.learnjpaandhibernate.course.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

//? 앱 시작 시 실행하게 해주기 위해서 CommandLineRunner 구현
@Component
public class CourseJdbcCommandLineRunner implements CommandLineRunner {

    @Autowired
    private CourseJdbcRepository repository;

    @Override
    public void run(String... args) throws Exception {
        repository.insert(new Course(1, "Learn AWS Now!", "nareun"));
        repository.insert(new Course(2, "Learn Azure Now!", "nareun"));
        repository.insert(new Course(3, "Learn DevOps Now!", "nareun"));

        repository.delete(1);

        System.out.println(repository.findById(2));
        System.out.println(repository.findById(3));
    }

}

package com.nareun.springboot.learnjpaandhibernate.course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.nareun.springboot.learnjpaandhibernate.course.jdbc.CourseJdbcRepository;
import com.nareun.springboot.learnjpaandhibernate.course.jpa.CourseJpaRepository;

//? 앱 시작 시 실행하게 해주기 위해서 CommandLineRunner 구현
@Component
public class CourseCommandLineRunner implements CommandLineRunner {

    // @Autowired
    // private CourseJdbcRepository repository;
    @Autowired
    private CourseJpaRepository repository;

    @Override
    public void run(String... args) throws Exception {
        //! EntityManger가 insert하기 전에 selectById로 실행해서 존재하는 지 체크 한 후 삽입하고 삭제한다.
        repository.insert(new Course(1, "Learn AWS JPA!", "nareun"));
        repository.insert(new Course(2, "Learn Azure JPA!", "nareun"));
        repository.insert(new Course(3, "Learn DevOps JPA!", "nareun"));

        // repository.delete(1);
        repository.deleteById(1);

        System.out.println(repository.findById(2));
        System.out.println(repository.findById(3));
    }

}

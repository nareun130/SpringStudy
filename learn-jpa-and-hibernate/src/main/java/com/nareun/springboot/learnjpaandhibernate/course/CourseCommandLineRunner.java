package com.nareun.springboot.learnjpaandhibernate.course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.nareun.springboot.learnjpaandhibernate.course.jdbc.CourseJdbcRepository;
import com.nareun.springboot.learnjpaandhibernate.course.jpa.CourseJpaRepository;
import com.nareun.springboot.learnjpaandhibernate.course.springdatajpa.CourseSpringDataJpaRepository;

//? 앱 시작 시 실행하게 해주기 위해서 CommandLineRunner 구현
@Component
public class CourseCommandLineRunner implements CommandLineRunner {

    // @Autowired
    // private CourseJdbcRepository repository;
    // @Autowired
    // private CourseJpaRepository repository;
    @Autowired
    private CourseSpringDataJpaRepository repository;

    @Override
    public void run(String... args) throws Exception {
        //! EntityManger가 insert하기 전에 selectById로 실행해서 존재하는 지 체크 한 후 삽입하고 삭제한다.
        repository.save(new Course(1, "Learn AWS JPA!", "nareun"));
        repository.save(new Course(2, "Learn Azure JPA!", "nareun"));
        repository.save(new Course(3, "Learn DevOps JPA!", "nareun"));

        // repository.delete(1);
        repository.deleteById(1l);//* */ JpaRepository에서 Long을 예상하기 때문에 l 붙인다.

        System.out.println(repository.findById(2l));
        System.out.println(repository.findById(3l));

        System.out.println(repository.findAll());
        System.out.println(repository.count());

        System.out.println(repository.findByAuthor("nareun"));
        System.out.println(repository.findByAuthor(""));

        System.out.println(repository.findByName("Learn DevOps JPA!"));


    }

}

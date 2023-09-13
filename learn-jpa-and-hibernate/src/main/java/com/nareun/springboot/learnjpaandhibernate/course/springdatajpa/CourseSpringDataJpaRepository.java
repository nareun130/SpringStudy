package com.nareun.springboot.learnjpaandhibernate.course.springdatajpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nareun.springboot.learnjpaandhibernate.course.Course;
import java.util.List;


public interface CourseSpringDataJpaRepository extends JpaRepository<Course,Long>{//~>Course엔티티의 기본키가 long타입이므로 참조타입인 Long을 작성
    //? 커스텀 메서드도 정의 가능!
    List<Course> findByAuthor(String author);

    List<Course> findByName(String name);
}

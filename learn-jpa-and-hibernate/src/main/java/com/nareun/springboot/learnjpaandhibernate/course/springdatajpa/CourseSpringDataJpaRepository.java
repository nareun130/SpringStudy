package com.nareun.springboot.learnjpaandhibernate.course.springdatajpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nareun.springboot.learnjpaandhibernate.course.Course;

public interface CourseSpringDataJpaRepository extends JpaRepository<Course,Long>{//~>Course엔티티의 기본키가 long타입이므로 참조타입인 Long을 작성
    
}

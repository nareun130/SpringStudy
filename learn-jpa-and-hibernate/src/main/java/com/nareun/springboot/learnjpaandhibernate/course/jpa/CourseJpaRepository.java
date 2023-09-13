package com.nareun.springboot.learnjpaandhibernate.course.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nareun.springboot.learnjpaandhibernate.course.Course;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Repository
@Transactional //! JPA로 쿼리 실행 시 마다 트랜잭션을 허용해야 함!
public class CourseJpaRepository {

    // @Autowired
    @PersistenceContext //* container관리형 엔티티 매니저 및 그에 연결된 영속성 컨텍스트와 종속성을 나타냄 -> 좀 더 구체적인 어노테이션 */
    private EntityManager entityManager;

    //insert method -> merge
    public void insert(Course course){
        entityManager.merge(course);
    }

    //select 
    public Course findById(long id){
        return entityManager.find(Course.class, id);
    }

    //delete
    public void deleteById(long id){
        Course course = entityManager.find(Course.class,id);
        entityManager.remove(course);
    } 
}

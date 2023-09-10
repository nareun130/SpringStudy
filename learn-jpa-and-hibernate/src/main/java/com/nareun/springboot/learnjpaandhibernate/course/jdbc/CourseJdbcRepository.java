package com.nareun.springboot.learnjpaandhibernate.course.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CourseJdbcRepository {

    @Autowired
    private JdbcTemplate springJdbcTemplate;

    private static String INSERT_QUERY = 
    """
        insert into course (id, name, author)
            values(1, 'Learn AWS', 'nareun');
    """;

    //* 앱 시작 시 insert쿼리를 시작시키려면 ? ->SpringBoot의 CommandLineRunner를 사용  */
    public void insert(){
        springJdbcTemplate.update(INSERT_QUERY);
    }
}

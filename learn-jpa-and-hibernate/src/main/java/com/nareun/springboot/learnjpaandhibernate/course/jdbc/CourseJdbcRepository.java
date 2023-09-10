package com.nareun.springboot.learnjpaandhibernate.course.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CourseJdbcRepository {

    @Autowired
    private JdbcTemplate springJdbcTemplate;

    private static String INSERT_QUERY = """
                insert into course (id, name, author)
                    values(?, ?, ?);
            """;

    private static String DELETE_QUERY = """
                delete from course
                    where id = ? ;
            """;

    private static String SELECT_QUERY = """
                select * from course
                    where id = ?
            """;

    // * 앱 시작 시 insert쿼리를 시작시키려면 ? ->SpringBoot의 CommandLineRunner를 사용 */
    public void insert(Course course) {
        springJdbcTemplate.update(INSERT_QUERY, course.getId(), course.getName(), course.getAuthor());
    }

    public void delete(long id) {
        springJdbcTemplate.update(DELETE_QUERY, id);
    }

    public Course findById(long id) {
        // !특정 상황에서 테이블에 있는 열의 이름은 Bean에 있는 이름과 정확히 짝을 이룬다!!!
        return springJdbcTemplate.queryForObject(SELECT_QUERY, new BeanPropertyRowMapper<>(Course.class), id);
        // * */ 2번째 파라미터 ResultSet을 Bean에 매핑 => Row Mapper : 결과 세트의 각 행을 특정 Bean에 연결
        // 3 번째 파라미터 : id
        //~> Course클래스에 setter가 없으면 null값이 나온다.

    }
}

create table course
(
    -- bigint -> java의 long
    id bigint not null,
    name varchar(255) not null,
    author varchar(255) not null,
    -- table 정의 시에는 항상 기본키 정의!
    primary key (id)
);

-- Spring Data JPA Starter를 활용할 때마다 자동으로 schema.sql파일을 가져와서 h2테이블을 생성
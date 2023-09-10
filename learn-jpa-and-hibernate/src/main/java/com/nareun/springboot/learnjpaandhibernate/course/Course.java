package com.nareun.springboot.learnjpaandhibernate.course;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

// @Entity(name="Course_Details") -> name을 줘서 이렇게 다른 이름의 테이블 매핑도 가능
@Entity
//~> Java Bean과 테이블 사이에 매핑을 생성하고 그 매핑을 이용해서 값을 삽입하고, 값을 검색하고 수정
public class Course {

    @Id
    private long id;
    @Column(name="name")
    private String name;
    @Column(name="author")
    private String author;

    //constructor
    public Course(){

    }

    public Course(long id, String name, String author){
        super();
        this.id = id;
        this.name = name;
        this.author = author;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Course [id=" + id + ", name=" + name + ", author=" + author + "]";
    }

    
}

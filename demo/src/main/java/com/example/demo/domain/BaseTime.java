package com.example.demo.domain;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;

@Getter
@MappedSuperclass // * 부모클래스 선언. 자식 클래스는, 해당 클래스의 필드를 Column으로 생성
@EntityListeners(AuditingEntityListener.class) // Auditing기능 추가
public class BaseTime {

	@CreatedDate // 엔티티 생성 시, 당시 시각을 자동 기입
	private LocalDateTime createdAt;

	@LastModifiedDate // 엔티티 수 시, 당시 시각을 자동 기입
	private LocalDateTime updatedAt;
}

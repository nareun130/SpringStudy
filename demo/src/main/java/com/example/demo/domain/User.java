package com.example.demo.domain;

import com.example.demo.dto.UserRequestDto;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class User extends BaseTime {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 생성 방법 DB에 위임
	private Long id;

	@Column(length = 50, nullable = false, unique = true)
	private String email;

	@Column(length = 50, nullable = false, unique = true)
	private String contact;

	@Column(length = 50, nullable = false, unique = true)
	private String username;

	@Column(length = 100, nullable = false)
	private String password;

	// enum이름을 칼럼에 저장
	@Enumerated(EnumType.STRING)
	private Role role;

	@OneToOne(mappedBy = "user", cascade = CascadeType.REMOVE)
	private Auth auth;

	@Builder
	public User(String email, String contact, String username, String password, Role role) {
		this.role = role;
		this.email = email;
		this.contact = contact;
		this.username = username;
		this.password = password;
	}

	public void update(UserRequestDto requestDto) {
		this.role = requestDto.getRole();
		
	}

}

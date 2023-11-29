package com.example.demo.dto;

import com.example.demo.domain.Role;
import com.example.demo.domain.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//회원가입 및 로그인에 사용되는 객체 
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDTO {
	private Role role;
	private String email;
	private String contact;
	private String username;
	private String password;
	
	public User toEntity() {
		return User.builder()
				.role(this.role)
				.email(this.email)
				.contact(this.contact)
				.username(this.username)
				.password(this.password)
				.build();
	}
	
}

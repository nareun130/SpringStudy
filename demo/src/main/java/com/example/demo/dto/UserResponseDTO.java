package com.example.demo.dto;

import com.example.demo.domain.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponseDTO {
	private Long id;
	private String role;
	private String email;
	private String contact;
	private String username;
	
	public UserResponseDTO(User entity) {
		this.id = entity.getId();
		this.email = entity.getEmail();
		this.contact = entity.getEmail();
		this.username = entity.getUsername();
		//Enum -> String 
		this.role = entity.getRole().name();
	}
}

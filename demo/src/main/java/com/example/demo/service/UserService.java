package com.example.demo.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.User;
import com.example.demo.dto.UserRequestDto;
import com.example.demo.dto.UserResponseDto;
import com.example.demo.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	// User 조회
	@Transactional
	public UserResponseDto findById(Long id) {
		User user = this.userRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("해당 유저를 찾을 수 없습니다. user_id = " + id));
		return new UserResponseDto(user);
	}

	// User 수정
	@Transactional
	public void update(Long id, UserRequestDto requestDto) {
		User user = this.userRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("해당 유저를 찾을 수 없습니다. user_id = " + id));
		//TODO 
		user=requestDto.toEntity();
		this.userRepository.save(user);
	}

	// User 삭제
	@Transactional
	public void delete(Long id) {
		User user = this.userRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("해당 유저를 찾을 수 없습니다. user_id = " + id));
		this.userRepository.delete(user);

	}
}

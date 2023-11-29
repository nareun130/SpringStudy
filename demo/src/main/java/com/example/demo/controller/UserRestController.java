package com.example.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.config.JwtTokenProvider;
import com.example.demo.dto.UserRequestDto;
import com.example.demo.dto.UserResponseDto;
import com.example.demo.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class UserRestController {
	private final UserService userService;
	private final JwtTokenProvider jwtTokenProvider;

	// 회원정보 조회
	@GetMapping("/api/v1/user")
	public ResponseEntity<?> findUser(@RequestHeader("Authorization") String accessToken) {
		Long id = this.jwtTokenProvider.getUserIdFromToken(accessToken.substring(7));
		UserResponseDto userResponseDto = this.userService.findById(id);
		return ResponseEntity.status(HttpStatus.OK).body(userResponseDto);
	}

	// 회원정보 수정
	@PutMapping("/api/v1/user")
	public ResponseEntity<?> updateUser(@RequestHeader("Authorization") String accessToken,
			@RequestBody UserRequestDto requestdto) {
		Long id = this.jwtTokenProvider.getUserIdFromToken(accessToken.substring(7));
		this.userService.update(id, requestdto);
		return ResponseEntity.status(HttpStatus.OK).body(null);
	}

	// 회원 정보 삭제
	@DeleteMapping("/api/v1/user")
	public ResponseEntity<?> deleteUser(@RequestHeader("Authorization") String accessToken) {
		Long id = this.jwtTokenProvider.getUserIdFromToken(accessToken.substring(7));
		this.userService.delete(id);
		return ResponseEntity.status(HttpStatus.OK).body(null);
	}

}

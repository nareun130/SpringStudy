package com.example.demo.dto;

import com.example.demo.domain.Auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponseDTO {
	private String tokenType;
	private String accessToken;
	private String refreshToken;

	//* 인증 요청에 대해 User정보와 함께 Token 정보를 응답으로 반환 
	@Builder
	public AuthResponseDTO(Auth entity) {
		this.tokenType = entity.getTokenType();
		this.accessToken = entity.getAccessToken();
		this.refreshToken = entity.getRefreshToken();
	}

}

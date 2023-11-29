package com.example.demo.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter 
@Entity
public class Auth extends BaseTime {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String tokenType;//JWT 사용 시추후 Bearer타입 가지게 됨. 

	@Column(nullable = false)
	private String accessToken;// 액세스 요청을 위한 수명이 짧은 토큰 

	@Column(nullable = false)
	private String refreshToken;// 새 accessToken을 발급받기 위한 수명이 긴 토큰 

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;

	@Builder
	public Auth(User user, String tokenType, String accessToken, String refreshToken) {
		this.user = user;
		this.tokenType = tokenType;
		this.accessToken = accessToken;
		this.refreshToken = refreshToken;
	}

}

package com.example.demo.service;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.config.CustomUserDetails;
import com.example.demo.config.JwtTokenProvider;
import com.example.demo.domain.Auth;
import com.example.demo.domain.Role;
import com.example.demo.domain.User;
import com.example.demo.dto.AuthRequestDto;
import com.example.demo.dto.AuthResponseDto;
import com.example.demo.dto.UserRequestDto;
import com.example.demo.repository.AuthRepository;
import com.example.demo.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {
	private final UserRepository userRepository;
	private final AuthRepository authRepository;
	private final PasswordEncoder passwordEncoder;
	private final JwtTokenProvider jwtTokenProvider;

	// 로그인
	@Transactional
	public AuthResponseDto login(AuthRequestDto requestDto) {
		// username and password check
		User user = this.userRepository.findByUsername(requestDto.getUsername()).orElseThrow(
				() -> new UsernameNotFoundException("해당 유저를 찾을 수 없습니다. username : " + requestDto.getUsername()));
		if (!passwordEncoder.matches(requestDto.getPassword(), user.getPassword())) {
			throw new IllegalArgumentException("비밀번호가 일치하지 않습니다. username = " + requestDto.getUsername());
		}
		// 액세스 토큰 and 리프레시 토큰 생성
		String accessToken = this.jwtTokenProvider.generateAccessToken(
				new UsernamePasswordAuthenticationToken(new CustomUserDetails(user), user.getPassword()));
		String refreshToken = this.jwtTokenProvider.generateRefreshToken(
				new UsernamePasswordAuthenticationToken(new CustomUserDetails(user), user.getPassword()));

		// auth 엔티티가 존재하면 토큰 업데이트
		if (this.authRepository.existsByUser(user)) {
			user.getAuth().setAccessToken(accessToken);
			user.getAuth().setRefreshToken(refreshToken);
			return new AuthResponseDto(user.getAuth());
		}

		Auth auth = this.authRepository.save(Auth.builder().user(user).tokenType("Bearer").accessToken(accessToken)
				.refreshToken(refreshToken).build());
		return new AuthResponseDto(auth);
	}

	// 회원가입
	@Transactional
	public void signup(UserRequestDto requestDto) {
		// user 엔티티 저장
		requestDto.setRole(Role.ROLE_USER);
		requestDto.setPassword(passwordEncoder.encode(requestDto.getPassword()));
		this.userRepository.save(requestDto.toEntity());
	}

	// token갱신
	@Transactional
	public String refreshToken(String refreshToken) {
		// 리프레시 토큰이 유효한지 확인, 액세스 토큰을 업데이트 하고 반환
		if (this.jwtTokenProvider.validateToken(refreshToken)) {
			Auth auth = this.authRepository.findByRefreshToken(refreshToken)
					.orElseThrow(() -> new IllegalArgumentException(
							"해당 RefreshToken을 찾을 수 없습니다. \n REFRESH_TOKEN = " + refreshToken));
			String newAccessToken = this.jwtTokenProvider.generateAccessToken(new UsernamePasswordAuthenticationToken(
					new CustomUserDetails(auth.getUser()), auth.getUser().getPassword()));
			// TODO
			auth.setAccessToken(newAccessToken);

			return newAccessToken;
		}
		
		//리프레시 토큰이 유효하지 않으면(만료되면) , 액세 토큰과 리프레시 토큰을 재생성  
		//이때 유저는 재로그인 해야 한다.
		return null;
	}

}

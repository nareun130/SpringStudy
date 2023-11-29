package com.example.demo.config;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtTokenFilter extends OncePerRequestFilter {
	// * OncePerRequestFilter : Http Request의 한 번의 요청에 대해 한 번만 실행하는 filter+

	private final JwtTokenProvider jwtTokenProvider;
	private final CustomUserDetailsService customUserDetailsService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String accessToken = getTokenFromRequest(request);
		if(accessToken!= null && jwtTokenProvider.validateToken(accessToken)) {
			UsernamePasswordAuthenticationToken authentication = getAuthenticationFromToken(accessToken); 
		}
	}

	private UsernamePasswordAuthenticationToken getAuthenticationFromToken(String accessToken) {
		// TODO Auto-generated method stub
		return null;
	}

	private String getTokenFromRequest(HttpServletRequest request) {
		String bearerToken = request.getHeader("Authorization");
		//값이 있으면 true, null or blank -> false 
		if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
			return bearerToken.substring(7);
		}
		return null;
	}

}

package com.example.demo.config;

import java.security.SignatureException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.SecurityException;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenProvider {
	@Value("${jwt.secret}")
	private String jwtSecretKey;

	@Value("${jwt.accessTokenExpirationTime}")
	private Long jwtAccessTokenExpirationTime;

	@Value("${jwt.refreshTokenExpirationTime}")
	private Long jwtRefreshTokenExpirationTime;

	public String generateAccessToken(Authentication authentication) {
		CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
		Date expiryDate = new Date(new Date().getTime() + jwtAccessTokenExpirationTime);
		return Jwts.builder().setSubject(customUserDetails.getUsername()).claim("user-id", customUserDetails.getId())
				.claim("user-email", customUserDetails.getEmail()).setIssuedAt(new Date()).setExpiration(expiryDate)
				.signWith(SignatureAlgorithm.HS512, jwtSecretKey).compact();
	}

	public String generateRefreshToken(Authentication authentication) {
		CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
		Date expiryDate = new Date(new Date().getTime() + jwtRefreshTokenExpirationTime);
		return Jwts.builder().setSubject(customUserDetails.getUsername()).claim("user-id", customUserDetails.getId())
				.claim("user-email", customUserDetails.getEmail()).setIssuedAt(new Date()).setExpiration(expiryDate)
				.signWith(SignatureAlgorithm.HS512, jwtSecretKey).compact();
	}

	// ~> 토큰으로 부터 값 가져오는 메서드
	public Long getUserIdFromToken(String token) {
		return Jwts.parser().setSigningKey(jwtSecretKey).build().parseClaimsJws(token).getBody().get("user-id",
				Long.class);
	}

	public String getUsernameFromToken(String token) {
		return Jwts.parser().setSigningKey(jwtSecretKey).build().parseClaimsJws(token).getBody().getSubject();
	}

	public String getUserEmailFromToken(String token) {
		return Jwts.parser().setSigningKey(jwtSecretKey).build().parseClaimsJws(token).getBody().get("user-email",
				String.class);
	}

	public Date getExpirationFromToken(String token) {
		return Jwts.parser().setSigningKey(jwtSecretKey).build().parseClaimsJws(token).getBody().getExpiration();
	}

	// ~> 토큰 유효성 검사
	public Boolean validateToken(String token) {
		try {
			Jwts.parser().setSigningKey(jwtSecretKey).build().parseClaimsJws(token);
			return true;
		} catch (SecurityException ex) {
			System.out.println("Invalid JWT signature");
		} catch (MalformedJwtException ex) {
			System.out.println("Invalid JWT token");
		} catch (ExpiredJwtException ex) {
			System.out.println("Expired JWT token");
		} catch (UnsupportedJwtException ex) {
			System.out.println("Unsupported JWT token");
		} catch (IllegalArgumentException ex) {
			System.out.println("JWT claims string is empty");
		}
		return false;

	}
}

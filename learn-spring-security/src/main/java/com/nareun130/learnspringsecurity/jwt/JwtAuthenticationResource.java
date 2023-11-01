package com.nareun130.learnspringsecurity.jwt;

import java.time.Instant;
import java.util.stream.Collectors;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class JwtAuthenticationResource {

	private JwtEncoder jwtEncoder;

	public JwtAuthenticationResource(JwtEncoder jwtEncoder) {
		this.jwtEncoder = jwtEncoder;
	}

	@PostMapping("/authenticate")
	public JwtResponse authenticate(Authentication authentication) {
		
		return new JwtResponse(createToken(authentication));
	}

	private String createToken(Authentication authentication) { 
		var claims = JwtClaimsSet.builder()
		// 발행자 
		.issuer("self")
		// 발행 시간 
		.issuedAt(Instant.now())
		// 만료 시간 
		.expiresAt(Instant.now().plusSeconds(60*30))
		// 주제 
		.subject(authentication.getName())
		// 특정한 유저가 가진 권한을 claim의 scope에 넣는다. 
		.claim("scope", createScope(authentication))
		.build();
		
		return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue(); 
		
	}

	private String createScope(Authentication authentication) {
		return authentication.getAuthorities().stream()
		.map(a -> a.getAuthority())
		// 공백으로 구분해줌.
		.collect(Collectors.joining(" "));
		
	}

}

record JwtResponse(String token) {}
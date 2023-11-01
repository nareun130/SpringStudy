package com.nareun130.learnspringsecurity.jwt;

import static org.springframework.security.config.Customizer.withDefaults;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPublicKey;
import java.util.List;
import java.util.UUID;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.KeySourceException;
import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSelector;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;

@Configuration
public class JwtSecurityConfiguration {
	/*
	 * JWT 설정 1. 키 쌍 생성 2. 키 쌍을 이용한 RSA 키 생성 3. JWK(Json Wek Key Source) 생성 4. 디코딩을
	 * 위한 RSA키 이용 5. 인코딩을 위한 JWKSource 이용
	 */

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(auth -> auth.anyRequest().authenticated());
		http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		http.httpBasic(withDefaults());
		http.csrf(csrf -> csrf.disable());
		http.headers(header -> header.frameOptions(options -> options.sameOrigin()));

		http.oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt);
		// Resouce 서버가 jwt토큰을 받으면 디코딩해야한다. -> 디코더 필요

		return http.build();
	}

	@Bean
	public DataSource dataSource() {
		return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2)
				.addScript(JdbcDaoImpl.DEFAULT_USER_SCHEMA_DDL_LOCATION).build();
	}

	// ? dataSource를 사용한 보안 설
	@Bean
	public UserDetailsService userDetailsService(DataSource dataSource) {
		var user = User.withUsername("nareun130")
//				.password("{noop}1234")
				.password("1234").passwordEncoder(str -> passwordEncoder().encode(str)).roles("USER").build();

		var admin = User.withUsername("admin")
//				.password("{noop}1234")
				.password("1234")
				// ? password 암호화 설정
				.passwordEncoder(str -> passwordEncoder().encode(str))
				// ? 여러 역할지정 가능
				.roles("ADMIN", "USER").build();

		var jdbcUserDeatilsManager = new JdbcUserDetailsManager(dataSource);
		jdbcUserDeatilsManager.createUser(user);
		jdbcUserDeatilsManager.createUser(admin);

		return jdbcUserDeatilsManager;
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public KeyPair keyPair() {
		// * 1. 키 쌍 생성
		try {
			var keyPairGenerator = KeyPairGenerator.getInstance("RSA");
			keyPairGenerator.initialize(2048);
			return keyPairGenerator.generateKeyPair();
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}

	}

	@Bean
	public RSAKey rsaKey(KeyPair keyPair) {

		// * 2.RSA 키 객체 생성
		return new RSAKey
				// 공개키 설정
				.Builder((RSAPublicKey) keyPair.getPublic())
				// 개인 키 설정
				.privateKey(keyPair.getPrivate())
				// 키 ID 생성
				.keyID(UUID.randomUUID().toString()).build();
	}

	@Bean
	public JWKSource<SecurityContext> jwkSource(RSAKey rsaKey) {
		// * 3. JWKSource 생성
		// ~> JWKSet을 만들고 JWKSource를 만든다.

		// ?RSAKey를 이용해 JWKSet 생성
		var jwkSet = new JWKSet(rsaKey);

//		var jwkSource = new JWKSource() {
//
//			@Override
//			public List get(JWKSelector jwkSelector, SecurityContext context) throws KeySourceException {
//
//				return jwkSelector.select(jwkSet);
//			}
//
//		};

		// ~> 이렇게람다로 간단히 표현 가능
		return (jwkSelector, context) -> jwkSelector.select(jwkSet);

	}

	@Bean
	public JwtDecoder jwtDecoder(RSAKey rsaKey) throws JOSEException {
		// * 4. Decoding을 위한 RSA키 이용

		return NimbusJwtDecoder.withPublicKey(rsaKey.toRSAPublicKey()).build();
	}

	@Bean
	// * 5. Encoding을 위한 JWKSource 이용
	public JwtEncoder jwtEncoder(JWKSource<SecurityContext> jwkSource) {
		return new NimbusJwtEncoder(jwkSource);
	}

}

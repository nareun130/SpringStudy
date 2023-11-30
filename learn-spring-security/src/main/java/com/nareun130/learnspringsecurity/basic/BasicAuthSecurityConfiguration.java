package com.nareun130.learnspringsecurity.basic;

import static org.springframework.security.config.Customizer.withDefaults;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

@Configuration
@EnableMethodSecurity (jsr250Enabled = true, securedEnabled = true)//2. 메서드 보안 
public class BasicAuthSecurityConfiguration {
	
	//* Spring Security의 보안 2가지 1. 전역, 2. 메서드 

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		// HttpSecurity : 특정 HTTP 요청에대해 웹 기반 보안 설정 가능
		// ? 1. authorizeHttpRequests :전역 보안 
		http.authorizeHttpRequests(auth -> auth
				//users url로 접속하면 USER의 권한을 가져야함. 
				.requestMatchers(new AntPathRequestMatcher("/users")).hasRole("USER")
				//admin url로 접속하면 ADMIN의 권한을 가져야함.
				.requestMatchers(new AntPathRequestMatcher("/admin/**")).hasRole("ADMIN")
				.anyRequest()
				.authenticated());
		// 기본 formLogin사용x
//		http.formLogin(withDefaults()); 

		// * 세션 사용 해제 -> STATELES하게 만들어 준다.
		http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		http.httpBasic(withDefaults());
		// * csrf 사용 해제 -> session을 사용하지 않을거기 때문
		http.csrf(csrf -> csrf.disable());
		
		//h2-console은 기본적으로 frame태그를 사용. springSecurit는 이걸 막고 있어서 해제시켜준다. 
		http.headers(header -> header.frameOptions(options -> options.sameOrigin()));
		return http.build();
	}

	//? 인메모리 보안 설정 
//	@Bean
//	public UserDetailsService userDetailsService() {
//
//		var user = User.withUsername("nareun130")
//				//{noop}은 인코딩 x
//				.password("{noop}1234")
//				.roles("USER").build();
	
//		var admin = User.withUsername("admin")
//				.password("{noop}1234")
//				.roles("ADMIN").build();
//		return new InMemoryUserDetailsManager(user, admin);
//	}
	
	@Bean
	public DataSource dataSource() {
		
		return new EmbeddedDatabaseBuilder()
				.setType(EmbeddedDatabaseType.H2)
				//dataBase생성 시 실행할 스크립트 설정	
				.addScript(JdbcDaoImpl.DEFAULT_USER_SCHEMA_DDL_LOCATION)
				.build();
	}
	
	//? dataSource를 사용한 보안 설정 
	@Bean
	public UserDetailsService userDetailsService(DataSource dataSource) {
		var user = User.withUsername("nareun130")
//				.password("{noop}1234")
				.password("1234")
				.passwordEncoder(str -> passwordEncoder().encode(str))
				.roles("USER").build();
		
		var admin = User.withUsername("admin")
//				.password("{noop}1234")
				.password("1234")
				//? password 암호화 설정 
				.passwordEncoder(str -> passwordEncoder().encode(str))
				//? 여러 역할지정 가능 
				.roles("ADMIN","USER").build();
		
		var jdbcUserDeatilsManager = new JdbcUserDetailsManager(dataSource);
		jdbcUserDeatilsManager.createUser(user);
		jdbcUserDeatilsManager.createUser(admin);
		
		return jdbcUserDeatilsManager;
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}


	
}

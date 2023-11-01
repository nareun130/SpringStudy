package com.nareun130.learnspringsecurity.basic;

import static org.springframework.security.config.Customizer.withDefaults;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

//@Configuration
public class BasicAuthSecurityConfiguration {

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		// HttpSecurity : 특정 HTTP 요청에대해 웹 기반 보안 설정 가능
		http.authorizeHttpRequests(auth -> auth.anyRequest().authenticated());
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
	
	//? dataSource를 사용한 보안 설
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

package com.nareun.rest.webservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class RestWebServicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestWebServicesApplication.class, args);
	}

	/*
	 * 현재 todo-app(http://localhost:3000)에서 서버 포트로 요청을하면
	 * Cross Orgigin Request가 걸린다.
	 * http://localhost:3000에서 오는 모든 요청을 허용해주도록 설정해야 한다.
	 */
	// ~> WebMvcConfigurer를 구현해주어야 한다.
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			public void addCorsMappings(CorsRegistry registry) {
				// http://localhost:3000으로 오는 모든 매핑과 메서드를 허용
				registry.addMapping("/**")
						.allowedMethods("*")
						.allowedOrigins("http://localhost:3000");
			}

		};
	}
}

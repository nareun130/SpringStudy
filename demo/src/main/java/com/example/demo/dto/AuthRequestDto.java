package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthRequestDto {
	//클라이언트에서 서버로 API요청을 보낼 때 사용되는 DTO
	/*서버측 API는 사용자 인증하고 accessToken을 생성위해 username,password를 사용할 것이기 때문에
	 * AuthRequestDTO에 username, password 필드가 존재해야 함. 
	 */
	private String username;
	private String password;
}

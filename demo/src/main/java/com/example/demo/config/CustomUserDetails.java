package com.example.demo.config;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.demo.domain.User;

//* User.java에 Authority가 추가된 CustomUser Class 느낌 
public class CustomUserDetails implements UserDetails{

	private final User user;
	
	public CustomUserDetails(User user) {
		this.user = user;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Collections.singleton(new SimpleGrantedAuthority(user.getRole().name()));
	}

	//~> user정보 얻는 메서드 
	public Long getId() {
		return user.getId();
	}
	
	public String getEmail() {
		return user.getEmail();
	}
	
	public String getContact() {
		return user.getContact();
	}
	
	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}

	
	//~> 인증 관련 메서드 
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}

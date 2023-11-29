package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.domain.Auth;
import com.example.demo.domain.User;

public interface AuthRepository extends JpaRepository<Auth, Long> {

	// TODO
	boolean existsByUser(User user);

	Optional<Auth> findByRefreshToken(String refreshToken);

}

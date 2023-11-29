package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.domain.Auth;

public interface AuthRepository extends JpaRepository<Auth, Long>{
	
}

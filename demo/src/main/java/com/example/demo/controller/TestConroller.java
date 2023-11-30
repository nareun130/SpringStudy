package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestConroller {
	@GetMapping("/test/String")
	public String getString() {
		System.err.println("탔나?");
		return "Hello";
	}
}

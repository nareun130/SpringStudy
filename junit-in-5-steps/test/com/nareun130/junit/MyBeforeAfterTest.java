package com.nareun130.junit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MyBeforeAfterTest {
	@BeforeAll //BeforeAll과 AfterAll은 정적 실
	static void beforeAll() {
		System.out.println("beforeAll");
	}
	
	
	@BeforeEach // 모든 테스트 전 실행
	void beforeEach() {
		System.out.println("BeforeEach");
	}
	
	//! junit은 테스트 실행 순서를 보장해주지 않는다. 
	//! 즉 test1, test2, test3 이 순서가 x
	@Test
	void test1() {
		System.out.println("test1");
	}

	@Test
	void test2() { 
		System.out.println("test2");
	}

	@Test
	void test3() {
		System.out.println("test3");
	}
	
	@AfterEach// 모든 테스트 후 실행 
	void afterEach() {
		System.out.println("afterEach");
	}
	
	@AfterAll 
	static void afterAll() {
		System.out.println("afterAll");
	}
}

package com.nareun130.junit;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

class MyAssertTest {

	List<String> todos = Arrays.asList("AWS", "Azure", "DevOps");

	@Test
	void test() {
		boolean test = todos.contains("AWS");
		boolean test2 = todos.contains("GCP");

		assertEquals(true, test);
//		assertTrue(!test,"Something went wrong"); //~> 이렇게 오류 메시지를 줄 수도 있다.
		assertFalse(test2);
//		assertNull, assertNotNull

		assertArrayEquals(new int[] { 1, 2 }, new int[] { 2 });

		assertEquals(3, todos.size(), "Error Message");

	}

}

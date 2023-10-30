package com.nareun130.mockito.mockitodemo.list;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class ListTest {

	@Test
	void simpleTest() {
		List listMock = mock(List.class);

//		listMock.size() =>  3
		when(listMock.size()).thenReturn(3); // 반환값을 하나만 설정하면 몇 번 메소드를 호출해도 같은 값이 나옴.

		assertEquals(3, listMock.size());
		assertEquals(3, listMock.size());
		assertEquals(3, listMock.size());
		assertEquals(3, listMock.size());

	}

	@Test
	void multipleReturns() {
		List listMock = mock(List.class);

		when(listMock.size()).thenReturn(1).thenReturn(2);
		assertEquals(1, listMock.size());
		assertEquals(2, listMock.size());
		assertEquals(2, listMock.size());
		assertEquals(2, listMock.size());

	}

	@Test
	void specificParameters() {
		List listMock = mock(List.class);

		when(listMock.get(0)).thenReturn("Some String");
		assertEquals("Some String", listMock.get(0));
		// mockito에서 기본적으로 null 반환
		assertEquals(null, listMock.get(1));
	}

	@Test
	void genericParameters() {
		List listMock = mock(List.class);
		
		//get(10)이든, get(20)이든 Some String 반환 
		when(listMock.get(Mockito.anyInt())).thenReturn("Some String");
		assertEquals("Some String", listMock.get(10));
		assertEquals("Some String", listMock.get(20));
	}
}

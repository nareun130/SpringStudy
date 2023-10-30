package com.nareun130.mockito.mockitodemo.bussiness;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)// @Mock, @InjectMocks를 사용하기 위한 어노테이
class  SomeBusinessImplMockTest{

	//dataServiceMock을 생성하여 SomeBusinessImpl에 주입 
	@Mock
	private DataService dataServiceMock;
	
	@InjectMocks
	private SomeBusinessImpl businessImpl; 
	
	@Test
	void findTheGreatestFromAllData_basicScenario() {
		
		
		//mock데이터가 메서드 실행시 새로운 배열 리턴하도록 설정  
		when(dataServiceMock.retrieveAllData()).thenReturn(new int[] {25,15,5});
		
 		
		assertEquals(25, businessImpl.findTheGreatestFromAllData());
	} 
	
	//* mock의 이점 : 새 시나리오를 만들고 싶을 때 새 테스트를 작성하기만 하면된다.
	//* 또한 DataService에 새 메소드가 추가되어도 Mock은 영향x
	@Test
	void findTheGreatestFromAllData_OneValue() {
		
		when(dataServiceMock.retrieveAllData()).thenReturn(new int[] {35});
 		
		assertEquals(35, businessImpl.findTheGreatestFromAllData());
	} 
	
	@Test
	void findTheGreatestFromAllData_EmptyArray() {
		
		when(dataServiceMock.retrieveAllData()).thenReturn(new int[] {});
 		
		assertEquals(Integer.MIN_VALUE, businessImpl.findTheGreatestFromAllData());
	} 
}




package com.nareun130.mockito.mockitodemo.bussiness;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;

class  SomeBusinessImplMockTest{

	@Test
	void findTheGreatestFromAllData_basicScenario() {
		
		DataService dataServiceMock = mock(DataService.class);
		
		//mock데이터가 메서드 실행시 새로운 배열 리턴하도록 설정  
		when(dataServiceMock.retrieveAllData()).thenReturn(new int[] {25,15,5});
		
 		SomeBusinessImpl businessImpl = new SomeBusinessImpl(dataServiceMock);
 		
		assertEquals(25, businessImpl.findTheGreatestFromAllData());
	} 
	//* mock의 이점 : 새 시나리오를 만들고 싶을 때 새 테스트를 작성하기만 하면된다.
	//* 또한 DataService에 새 메소드가 추가되어도 Mock은 영향x
	@Test
	void findTheGreatestFromAllData_OneValue() {
		
		DataService dataServiceMock = mock(DataService.class);
		
		//mock데이터가 메서드 실행시 새로운 배열 리턴하도록 설정  
		when(dataServiceMock.retrieveAllData()).thenReturn(new int[] {35});
		
 		SomeBusinessImpl businessImpl = new SomeBusinessImpl(dataServiceMock);
 		
		assertEquals(35, businessImpl.findTheGreatestFromAllData());
	} 
}




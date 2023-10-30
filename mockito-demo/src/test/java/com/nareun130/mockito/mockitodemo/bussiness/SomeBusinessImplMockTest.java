package com.nareun130.mockito.mockitodemo.bussiness;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class  SomeBusinessImplMockTest{

	@Test
	void findTheGreatestFromAllData_basicScenario() {
		DataService dataServiceStub = new DataServiceStub1();
		SomeBusinessImpl businessImpl = new SomeBusinessImpl(dataServiceStub);
		int result = businessImpl.findTheGreatestFromAllData();
		assertEquals(25, result);
	}

	@Test
	void findTheGreatestFromAllData_withOneValue() {
		DataService dataServiceStub = new DataServiceStub2();
		SomeBusinessImpl businessImpl = new SomeBusinessImpl(dataServiceStub);
		int result = businessImpl.findTheGreatestFromAllData();
		assertEquals(35, result);
	}

}



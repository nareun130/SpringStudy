package com.nareun130.mockito.mockitodemo.bussiness;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class SomeBusinessImpStubTest {

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

/*
 * stub의 문제점. -> stub은 유지보수 어렵다.
 * 
 * 1.DataService의 메소드많아질 수록 stub에서 구현해야하는 것도 많아짐.
 * 
 * 2.stub 사용 시 많은 시나리오 테스트가 어렵다. ->stub에서 새 데이터 세트를 반환해야 때 마다 클래스를 계속 만들어야 함.
 * 
 */
class DataServiceStub1 implements DataService {
	@Override
	public int[] retrieveAllData() {
		return new int[] { 25, 15, 5 };
	}

}

class DataServiceStub2 implements DataService {
	@Override
	public int[] retrieveAllData() {
		return new int[] { 35 };
	}

}

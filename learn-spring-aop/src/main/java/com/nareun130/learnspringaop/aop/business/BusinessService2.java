package com.nareun130.learnspringaop.aop.business;

import java.util.Arrays;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.nareun130.learnspringaop.aop.data.DataService2;

@Service
public class BusinessService2 {

	private DataService2 DataService2;

	public BusinessService2(DataService2 DataService2) {
		this.DataService2 = DataService2;
	}

	public int calculateMin() {

		int[] data = DataService2.retrieveData();

		try {
			Thread.sleep(30);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

//		throw new RuntimeException("Something Went Wrong!");
		return Arrays.stream(data).min().orElse(0);
	}
	// Business Logic
	// Data

}

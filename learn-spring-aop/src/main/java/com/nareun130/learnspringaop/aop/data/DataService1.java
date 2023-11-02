package com.nareun130.learnspringaop.aop.data;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.nareun130.learnspringaop.aop.annotations.TrackTime;

@Repository
public class DataService1 {
	
	@TrackTime
	public int[] retrieveData() {
		try {
			Thread.sleep(30);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new int[] { 11, 22, 33, 44, 55 };
	}
}

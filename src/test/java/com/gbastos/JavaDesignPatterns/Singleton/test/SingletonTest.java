package com.gbastos.JavaDesignPatterns.Singleton.test;

import static org.junit.Assert.assertEquals;
import com.gbastos.JavaDesignPatterns.Singleton.DateUtil;

import org.junit.Test;

public class SingletonTest {
	
	@Test
	public void singletonTest() {
		DateUtil dateUtilOne = DateUtil.getInstante();
		DateUtil dateUtilTwo = DateUtil.getInstante();
		assertEquals(dateUtilOne, dateUtilTwo);
	}
}

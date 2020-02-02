package com.gbastos.JavaDesignPatterns.Singleton;

import static org.junit.Assert.assertEquals;
import com.gbastos.JavaDesignPatterns.Singleton.DateUtil;

import org.junit.Test;

public class SingletonTest {
	
	@Test
	public void singletonTest() {
		DateUtil dateUtilOne = DateUtil.getInstance();
		DateUtil dateUtilTwo = DateUtil.getInstance();
		assertEquals(dateUtilOne, dateUtilTwo);
	}
}

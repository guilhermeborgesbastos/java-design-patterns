package com.gbastos.JavaDesignPatterns.Singleton;

import java.io.Serializable;

public class DateUtil implements Serializable {

	private static final long serialVersionUID = 1L;
	private static DateUtil instante;
	
	private DateUtil() {
		
	}
	
	public static DateUtil getInstante() {
		if(instante == null) {
			instante = new DateUtil();
		}
		return instante;
	}

}

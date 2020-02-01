package com.gbastos.JavaDesignPatterns.Singleton;

import java.io.Serializable;

public class DateUtil implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/*
	 * Volatile keyword is related with the visibility of variables modified by multiple thread during
	 * concurrent programming.
	 * 
	 * For example:
	 * Every READ or WRITE of volatile variables will be stored into MAIN Memory and not in CPU cache.
	 */
	private static volatile DateUtil instante;
	
	private DateUtil() {
		
	}
	
	/*
	 * Since requiring a 'lock' it is a very expensive process, the synchronized
	 * block will be executed just in case of the singleton's instance is NULL.
	 */
	public static DateUtil getInstante() {
		if(instante == null) {
			synchronized(DateUtil.class) {
				instante = new DateUtil();
			}
		}
		return instante;
	}
	
	/*
	 * To solve the 'indentity' issue where the same serialized object when
	 * deserialized back to an Object is not longer equals we must implement the
	 * readResolve() method;
	 */
	protected Object readResolve() {
		return instante;
	}
}

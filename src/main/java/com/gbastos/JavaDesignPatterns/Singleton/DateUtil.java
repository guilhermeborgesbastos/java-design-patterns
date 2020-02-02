package com.gbastos.JavaDesignPatterns.Singleton;

import java.io.Serializable;

public class DateUtil implements Serializable {

	private static final long serialVersionUID = -7604766932017737115L;
	
	/*
	 * Volatile keyword is related with the visibility of variables modified by multiple thread during
	 * concurrent programming.
	 * 
	 * For example:
	 * Every READ or WRITE of volatile variables will be stored into MAIN Memory and not in CPU cache.
	 */
	private static volatile DateUtil instance;
	
	private DateUtil() {
		
	}
	
	/*
	 * Since requiring a 'lock' it is a very expensive process, the synchronized
	 * block will be executed just in case of the singleton's instance is NULL.
	 */
	public static DateUtil getInstance() {
		if(instance == null) {
			synchronized(DateUtil.class) {
				if(instance == null){
					instance = new DateUtil();					
				}
			}
		}
		return instance;
	}
	
	/*
	 * Sometimes in distributed systems, we need to implement Serializable interface
	 * in Singleton class so that we can store its state in the file system and
	 * retrieve it at a later point of time. Without proper handling this process
	 * can have 'indentity' issues where the same serialized object when
	 * deserialized back to an Object is not longer equals to the original object,
	 * in this case, we must implement the readResolve() method;
	 */
	protected Object readResolve() {
		return instance;
	}
}

package com.gbastos.JavaDesignPatterns.MultiThreading;

/*
 * Volatile is related with the visibility of variables modified by multiple thread during concurrent programming.
 * 
 *  For example:
 *  Every READ or WRITE of volatile variables will be stored into MAIN Memory and not in CPU cache.
 *  
 *  Volatile can also be use with STATIC keyword.
 *  
 *  In this example, we will execute the abstract method RUM from the Runnable interface until another method from
 *  another thread changes a boolean flag to FALSE. By doing this, we will be able to see the usage of volatile keyword.
 */
public class Volatile {
	
	private static volatile boolean active;
	
	public static void main(String... args) throws InterruptedException {
		// Main thread
		active = true;
		while (active) {
			System.out.println("Running...");
		}
		
		// Second thread
		Thread thread = new Thread(() -> {
			active = false;
			System.out.println("\nActive set to FALSE...");
		});
		thread.start();
	}
}

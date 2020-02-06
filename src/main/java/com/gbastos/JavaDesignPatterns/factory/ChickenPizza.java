package com.gbastos.JavaDesignPatterns.factory;

public class ChickenPizza implements Pizza {

	@Override
	public void prepare() {
		System.out.println("Preparing chicken pizza.");

	}

	@Override
	public void bake() {
		System.out.println("Baking Chicken pizza.");

	}

	@Override
	public void cut() {
		System.out.println("Cutting Chicken pizza.");
	}

}

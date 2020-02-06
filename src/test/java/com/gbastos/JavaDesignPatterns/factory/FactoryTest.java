package com.gbastos.JavaDesignPatterns.factory;

import org.junit.Test;

public class FactoryTest {

	@Test
	public void factoryTest() {
		PizzaStore pizzaStore = new PizzaStore();
		pizzaStore.orderPizza("chicken");
		
		/*
		 * See console log:
		 * - Preparing chicken pizza.
		 * - Baking Chicken pizza.
		 * - Cutting Chicken pizza.
		 */
	}
}

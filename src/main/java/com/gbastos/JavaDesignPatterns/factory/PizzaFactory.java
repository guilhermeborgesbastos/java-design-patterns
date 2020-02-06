package com.gbastos.JavaDesignPatterns.factory;

public class PizzaFactory {
	
	public static Pizza createPizza(String type) {
		Pizza pizza = null;
		
		if(type.equalsIgnoreCase("cheese")) {
			pizza = new CheesePizza();
		} else if(type.equalsIgnoreCase("chicken")) {
			pizza = new ChickenPizza();
		} else if(type.equalsIgnoreCase("veggie")) {
			pizza = new VeggiePizza();
		}
		
		return pizza;
	}
}

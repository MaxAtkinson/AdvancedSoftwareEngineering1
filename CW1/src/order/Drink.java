package order;

import exceptions.InvalidProductIdentifierException;

public class Drink extends Product {

	public Drink(String name, String desc, float price, String cat, String id) throws InvalidProductIdentifierException {
		super(name, desc, price, cat, id);
		if (!(id.startsWith("BEV"))) {
			throw new InvalidProductIdentifierException(String.format("Invalid Product Identifier %s specified.", id));
		}
	}

}

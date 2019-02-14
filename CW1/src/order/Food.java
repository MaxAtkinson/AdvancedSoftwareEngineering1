package order;

import exceptions.InvalidProductIdentifierException;

public class Food extends Product {

	public Food(String name, String desc, float price, String cat, String id) throws InvalidProductIdentifierException {
		super(name, desc, price, cat, id);
		if (!(id.startsWith("FOOD"))) {
			throw new InvalidProductIdentifierException(String.format("Invalid Product Identifier %s specified.", id));
		}
	}
	
}

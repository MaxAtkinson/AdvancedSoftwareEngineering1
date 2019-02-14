package order;

import exceptions.InvalidProductIdentifierException;

public class Memoribilia extends Product {

	public Memoribilia(String name, String desc, float price, String cat, String id) throws InvalidProductIdentifierException {
		super(name, desc, price, cat, id);
		if (!(id.startsWith("MEM"))) {
			throw new InvalidProductIdentifierException(String.format("Invalid Product Identifier %s specified.", id));
		}
	}

}

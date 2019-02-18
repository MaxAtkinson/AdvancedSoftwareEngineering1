package order;
import customExceptions.InvalidProductIdentifierException;
import customExceptions.InvalidProductPriceException;

public class Drink extends Product {

	public Drink(String name, String desc, float price, String id) throws InvalidProductPriceException, InvalidProductIdentifierException {
		super(name, desc, price, id);
		if(!(id.startsWith("BEV"))) {
			throw new InvalidProductIdentifierException(String.format("Invalid ID on product with name: %s", name));
		}
	}

}

package order;
import customExceptions.InvalidProductPriceException;

public class Drink extends Product {

	public Drink(String name, String desc, float price, String id) throws InvalidProductPriceException {
		super(name, desc, price, id);
		if(!(price == (float)price)) {
			throw new InvalidProductPriceException(String.format("Invalid price on product with ID number: %s", id));
		}
	}

}

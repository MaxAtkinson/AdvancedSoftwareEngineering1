package order;
import customExceptions.InvalidProductIdentifierException;
import customExceptions.InvalidProductPriceException;

public class Food extends Product {

	public Food(String name, String desc, float price, String id) throws InvalidProductPriceException, InvalidProductIdentifierException {
		super(name, desc, price, id);
		if(!(price == (float)price)) {
			throw new InvalidProductPriceException(String.format("Invalid price on product with ID number: %s", id));
		}
		if(!(id.startsWith("FOOD"))) {
			throw new InvalidProductIdentifierException(String.format("Invalid ID on product with name: %s", name));
		}
	}

}

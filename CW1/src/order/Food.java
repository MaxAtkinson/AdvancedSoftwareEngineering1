package order;
import customExceptions.InvalidProductIdentifierException;
import customExceptions.InvalidProductPriceException;

/**
 * Derived class of Product class.
 * Checks for valid ID on instantiation and throws custom exception if invalid.
 */
public class Food extends Product {

	public Food(String name, String desc, float price, String id) throws InvalidProductPriceException, InvalidProductIdentifierException {
		super(name, desc, price, id);
		if(!(id.startsWith("FOOD"))) {
			throw new InvalidProductIdentifierException(String.format("Invalid ID on product with name: %s", name));
		}
	}

}

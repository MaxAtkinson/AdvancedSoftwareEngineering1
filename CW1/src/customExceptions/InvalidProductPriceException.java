package customExceptions;

public class InvalidProductPriceException extends Exception {
	public InvalidProductPriceException(String message) {
		super(message);
	}
}


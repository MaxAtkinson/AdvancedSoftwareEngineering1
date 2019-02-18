package testing;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Before;
import org.junit.Test;

import customExceptions.InvalidProductIdentifierException;
import customExceptions.InvalidProductPriceException;
import fileManagerIO.FileManagerIO;
import order.Drink;

public class ExceptionsTests {
	FileManagerIO f;
	String productsFileName;
	String ordersFileName;
	String reportFileName;
	Path menuPath;
	Path ordersPath;
	long lineCount;
	long newLineCount;
	
	@Before
	public void setup() {
		f = FileManagerIO.getInstances();
		productsFileName = "ProductsTestException.csv";
		menuPath = Paths.get(productsFileName);
	}
	
	@Test(expected = InvalidProductPriceException.class)
	public void thorwsInvalidPriceException() throws InvalidProductPriceException, InvalidProductIdentifierException {
		f.readFromProductsFile(productsFileName);
		
	}
	
	@Test(expected = InvalidProductIdentifierException.class)
	public void throwsInvalidDrinkIdentifierException() throws InvalidProductPriceException, InvalidProductIdentifierException {
		Drink exception = new Drink("Exceptional Coke", "An Exceptional Coke", (float) 10.99, "TEST111");
		
	}
	
	@Test(expected = InvalidProductIdentifierException.class)
	public void throwsInvalidFoodIdentifierException() throws InvalidProductPriceException, InvalidProductIdentifierException {
		Drink exception = new Drink("Exceptional Cake", "An Exceptional Cake", (float) 10.99, "TEST111");
		
	}
	
	@Test(expected = InvalidProductIdentifierException.class)
	public void throwsInvalidMemoribiliaIdentifierException() throws InvalidProductPriceException, InvalidProductIdentifierException {
		Drink exception = new Drink("Exceptional Item", "An Exceptional Item", (float) 10.99, "TEST111");
		
	}
}

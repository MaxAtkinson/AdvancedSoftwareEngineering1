package testing;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Before;
import org.junit.Test;

import customExceptions.InvalidProductPriceException;
import fileManagerIO.FileManagerIO;

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
	public void thorwsInvalidPriceException() throws InvalidProductPriceException {
		f.readFromProductsFile(productsFileName);
		
	}
}

package testing;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import customExceptions.InvalidProductIdentifierException;
import customExceptions.InvalidProductPriceException;
import fileManagerIO.FileManagerIO;
import order.Order;
import order.Product;

/*
 * JUnit tests of FileManagerIO. Passes with 99% coverage.
 */
public class FileManagerIOTests {
	
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
		/* Create FileManagerIO singleton and set file paths here */
		f = FileManagerIO.getInstances();
		productsFileName = "Products.csv";
		ordersFileName = "Orders.csv";
		reportFileName = "Report.txt";
		menuPath = Paths.get(productsFileName);
		ordersPath = Paths.get(ordersFileName);
	}
	
	@Test
	public void testMenuSize() throws IOException, InvalidProductPriceException, InvalidProductIdentifierException {
		/* FileManagerIO reads menu file and adds products to arraylist. Verify size here. */
		lineCount = Files.lines(menuPath).count()-1; //subtract for headers
		f.readFromProductsFile(productsFileName);
		assertEquals(lineCount, f.getNumberOfProducts());
	}
	
	@Test
	public void testOrdersSize() throws IOException {
		/* FileManagerIO reads orders file and adds orders to arraylist. Verify size here. */
		lineCount = Files.lines(ordersPath).count()-1;
		f.readFromOrderFile(ordersFileName);
		assertEquals(lineCount, f.getSizeOfExistingOrders());
	}
	
	@Test
	public void testStore() throws IOException {
		/* Test for confirmed basket of items written to orders file */
		Order o = new Order((long) 1, new Product("Irn Bru","A classic scottish staple",(float) 1.99,"BEV112"), "CUS127");
		lineCount = Files.lines(ordersPath).count()-1;
		f.store(o);
		newLineCount = Files.lines(ordersPath).count()-1;
		assertEquals(lineCount+1, newLineCount);
	}
	
	@Test
	public void testBasketConfirmed() throws IOException {
		/* Test logic on confirming basket. CurrentOrders list should increase by num of products ordered.
		 * Each order should be appended the orders file */
		f.readFromOrderFile(ordersFileName); // required to fill existingOrders list to create new custIDs
		int existingSize = f.getSizeOfExistingOrders();
		lineCount = Files.lines(ordersPath).count()-1;
		Product p1 = new Product("Irn Bru","A classic scottish staple",(float) 1.99,"BEV112");
		Product p2 = new Product("Irn Bru 32","A not-so-classic scottish staple",(float) 2.99,"BEV113");
		ArrayList<Product> ap = new ArrayList<>();
		ap.add(p1);
		ap.add(p2);
		f.addCurrentOrder(ap);
		assertEquals(ap.size(),f.getSizeOfExistingOrders()-existingSize);
		newLineCount = Files.lines(ordersPath).count()-1;
		assertEquals(lineCount+ap.size(), newLineCount);
	}
	
	@Test
	public void testWriteReport() throws IOException, InvalidProductPriceException, InvalidProductIdentifierException {
		/* Check to see if report exists on program termination. */
		f.readFromProductsFile(productsFileName); // Products list must be initialised to iterate through.
		f.readFromOrderFile(ordersFileName); // Orders list needs to exist to have something to report on, even if empty.
		f.writeReport(reportFileName);
		File f = new File(reportFileName);
		assertEquals(true,f.exists());
	}
	
}

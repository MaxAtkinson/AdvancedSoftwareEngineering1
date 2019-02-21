package testing;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import customExceptions.InvalidProductIdentifierException;
import customExceptions.InvalidProductPriceException;
import fileManagerIO.FileManagerIO;
import order.Drink;

public class DrinkClassTests {
	
	private Drink d;
	FileManagerIO f;
	private String name = "Coke";
	private String desc = "An ice-cold coke";
	private float price =  (float )1.99;
	private String cat = "Beverage" ;
	private String id = "BEV111";

	
	@Before
	public void setup() throws InvalidProductPriceException, InvalidProductIdentifierException{
	d = new Drink(name, desc, price,id);
	}
	
	@Test
	public void getNamePass() {

		assertEquals(name,d.getName());
		
	}
	
	@Test
	public void getDescPass() {
		
		assertEquals(desc,d.getDesc());
		
	}
	
	@Test
	public void getPricePass() {
		
		assertEquals(price, d.getPrice(),0.01);
		
	}
	
	@Test
	public void getIdPass() {
		
		assertEquals(id,d.getId());
	};
	
}

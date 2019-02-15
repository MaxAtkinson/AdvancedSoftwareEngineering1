package testing;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import exceptions.InvalidProductIdentifierException;
import order.Food;
import order.Memoribilia;

public class MemoribiliaTest {

private Memoribilia m;
	
	private String name = "Loch Ness Monster";
	private String desc = "Ceramic Loch Ness monster";
	private float price =  (float )4.99;
	private String cat = "Memorabilia" ;
	private String id = "MEM111";

	
	@Before
	public void setup() throws InvalidProductIdentifierException{
		m = new Memoribilia(name, desc, price,cat,id);
	}
	
	@Test
	public void getNamePass() {

		assertEquals(name,m.getName());
		
	}
	
	@Test
	public void getDescPass() {
		
		assertEquals(desc,m.getDesc());
		
	}
	

	@Test
	public void getPricePass() {
		
		assertEquals(price, m.getPrice(),0.01);
		
	}

	
	@Test
	public void getCatPass() {
		
		assertEquals(cat,m.getCat());
	};
	
	@Test
	public void getIdPass() {
		
		assertEquals(id,m.getId());
	};

	@Test
	public void getMenuDisplayPass(){
		String x = name + " £" + price;
		assertEquals(x, m.getMenuDisplay());
		
	}
	
	@Test(expected = InvalidProductIdentifierException.class)
	public void throwsExceptionPass() throws InvalidProductIdentifierException {
		Memoribilia exceptionFood = new Memoribilia("An exceptional piece of memoribilia", "An exception Description", (float) 0.99, "Memoribilia", "DEV111");
		
	}


}

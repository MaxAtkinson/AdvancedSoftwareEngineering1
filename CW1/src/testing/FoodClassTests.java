package testing;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import exceptions.InvalidProductIdentifierException;
import order.Food;

public class FoodClassTests {
	
	private Food f;
	
	private String name = "Chicken and Ham pie";
	private String desc = "Chicken and Ham pie with a floating puff pastry top";
	private float price =  (float )12.99;
	private String cat = "Food" ;
	private String id = "FOOD124";

	
	@Before
	public void setup() throws InvalidProductIdentifierException{
		f = new Food(name, desc, price,cat,id);
	}
	
	@Test
	public void getNamePass() {

		assertEquals(name,f.getName());
		
	}
	
	@Test
	public void getDescPass() {
		
		assertEquals(desc,f.getDesc());
		
	}
	

	@Test
	public void getPricePass() {
		
		assertEquals(price, f.getPrice(),0.01);
		
	}

	
	@Test
	public void getCatPass() {
		
		assertEquals(cat,f.getCat());
	};
	
	@Test
	public void getIdPass() {
		
		assertEquals(id,f.getId());
	};

	@Test
	public void getMenuDisplayPass(){
		String x = name + " £" + price;
		assertEquals(x, f.getMenuDisplay());
		
	}

}

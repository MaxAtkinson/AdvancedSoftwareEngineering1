package testing;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import order.Product;

public class ProductClassTests {
	
	private Product p;
	
	private String name = "Cottage pie";
	private String desc = "Traditional scottish dish with mince filling and mashed potatoe topping";
	private float price =  (float) 12.99;
	private String cat = "Food" ;
	private String id = "FOOD123";
	

	@Before
	public void setup(){
	p = new Product(name, desc, price,id);
	}
	
	@Test
	public void getNamePass() {

		assertEquals("Cottage pie",p.getName());
		
	}
	
	@Test
	public void getDescPass() {
		
		assertEquals(desc,p.getDesc());
		
	}
	

	@Test
	public void getPricePass() {
		
		assertEquals(price, p.getPrice(),0.01);
		
	}

	@Test
	public void getIdPass() {
		
		assertEquals(id,p.getId());
	};

	@Test
	public void getMenuDisplayPass(){
		String x = name + " £" + price;
		assertEquals(x, p.getMenuDisplay());
		
	}
	
	@Test
	public void toStringIsCorrectPass() {
		assertEquals("Cottage pie", p.toString());
	}
	
}
package testing;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import order.Order;
import order.Product;

public class OrderClassTests {
	
	private Order o;
	private Product p;
	
	private long timestamp = 19011535;
	private Product product = p;
	private String custID= "CUS111" ;
	
	@Before
	public void setup(){
	o = new Order (timestamp, product, custID);
	}
	
	@Test
	public void getTimestampPass() {
		
		assertEquals(timestamp,o.getTimestamp());
	};
	
	@Test
	public void getProductPass() {
		
		assertEquals(p,o.getProduct());
	};
	@Test
	public void getCustIdPass() {
		
		assertEquals(custID,o.getCustID());
	};
}

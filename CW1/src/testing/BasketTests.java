package testing;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import customExceptions.InvalidProductPriceException;
import order.Basket;
import order.Drink;
import order.Food;
import order.Memoribilia;
import order.Product;

public class BasketTests {
	private Basket basket = null;
	
	@Before
	public void setup(){
		basket = null;
	}

	@Test
	public void testCalculateTotalPricePass() throws InvalidProductPriceException {
		ArrayList<Product> products = new ArrayList<Product>() {{
			 add(new Food("Test Food 1", "A tasty bit of testing", 2.10f, "FOOD123"));
			 add(new Drink("Test Drink 1", "A tasty bit of testing", 1.10f, "DRINK123"));
			 add(new Memoribilia("Test Memoribilia 1", "A tasty bit of testing", 1.00f, "MEM123"));
		 }};
		 basket = new Basket();
		 
		 assertEquals(4.20f, Basket.calculateTotalPrice(products), 0.00);
	}

	@Test
	public void testCalculateTotalPriceEmptyProductsPass() {
		 ArrayList<Product> products = new ArrayList<Product>();
		 basket = new Basket();
		 assertEquals(0.0f, Basket.calculateTotalPrice(products), 0.00);
	}

	@Test
	public void testCalculateDiscountsPass() throws InvalidProductPriceException {
		ArrayList<Product> products = new ArrayList<Product>() {{
			 add(new Food("Test Food 1", "A tasty bit of testing 1", 2.10f, "FOOD123"));
			 add(new Food("Test Food 2", "A tasty bit of testing 2", 2.10f, "FOOD124"));
			 add(new Food("Test Food 3", "A tasty bit of testing 3", 2.10f, "FOOD125"));
			 add(new Drink("Test Drink 1", "A tasty bit of testing", 1.10f, "DRINK123"));
			 add(new Memoribilia("Test Memoribilia 1", "A tasty bit of testing", 1.00f, "MEM123"));
			 add(new Memoribilia("Test Memoribilia 2", "A tasty bit of testing", 1.00f, "MEM124"));
		}};
		basket = new Basket();
		float expected = Basket.calculateTotalPrice(products) * 0.8f;
		assertEquals(expected, Basket.calculateDiscountedTotal(products), 0.01);
	}
	
	@Test
	public void testCalculateDiscountsDoesntGiveDiscountWhenLessThanTwoMemAndOneDrinkPass() throws InvalidProductPriceException {
		ArrayList<Product> products = new ArrayList<Product>() {{
			 add(new Food("Test Food 1", "A tasty bit of testing 1", 2.10f, "FOOD123"));
			 add(new Food("Test Food 2", "A tasty bit of testing 2", 2.10f, "FOOD124"));
			 add(new Food("Test Food 3", "A tasty bit of testing 3", 2.10f, "FOOD125"));
			 add(new Memoribilia("Test Memoribilia 1", "A tasty bit of testing", 1.00f, "MEM123"));
		}};
		basket = new Basket();

		float expected = Basket.calculateTotalPrice(products);
		assertEquals(expected, Basket.calculateDiscountedTotal(products), 0.00);
	}
	
	@Test
	public void getProductsReturnsCorrectNumberOfProductsPass() throws InvalidProductPriceException {
		ArrayList<Product> products = new ArrayList<Product>() {{
			 add(new Food("Test Food 1", "A tasty bit of testing 1", 2.10f, "FOOD123"));
		}};
		basket = new Basket();
	    for (Product p: products) {
		    basket.addProduct(p);
	    }
		assertEquals(1, basket.getProducts().size());
	}
	
	@Test
	public void clearBasketClearsProductsListPass() throws InvalidProductPriceException {
		ArrayList<Product> products = new ArrayList<Product>() {{
			 add(new Food("Test Food 1", "A tasty bit of testing 1", 2.10f, "FOOD123"));
		}};
		basket = new Basket();
	    for (Product p: products) {
		    basket.addProduct(p);
	    }
	    basket.clearBasket();
		assertEquals(0, basket.getProducts().size());
	}
	
	@Test
	public void removeProductRemovesProductsFromListPass() throws InvalidProductPriceException {
		Product product = new Food("Test Food 1", "A tasty bit of testing 1", 2.10f, "FOOD123");
		ArrayList<Product> products = new ArrayList<Product>() {{
			 add(product);
		}};
		basket = new Basket();
	    for (Product p: products) {
		    basket.addProduct(p);
	    }
	    basket.removeProduct(product);
		assertEquals(0, basket.getProducts().size());
	}
	

}
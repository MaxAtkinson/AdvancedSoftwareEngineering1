package testing;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

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
	public void testCalculateTotalPricePass() {
		@SuppressWarnings("serial")
		ArrayList<Product> products = new ArrayList<Product>() {{
			 add(new Food("Test Food 1", "A tasty bit of testing", 2.10f, "", "FOOD123"));
			 add(new Drink("Test Drink 1", "A tasty bit of testing", 1.10f, "", "DRINK123"));
			 add(new Memoribilia("Test Memoribilia 1", "A tasty bit of testing", 1.00f, "", "MEM123"));
		 }};
		 basket = new Basket(products);
		 
		 assertEquals(4.20f, basket.calculateTotalPrice(), 0.00);
	}

	@Test
	public void testCalculateTotalPriceEmptyProductsPass() {
		 ArrayList<Product> products = new ArrayList<Product>();
		 basket = new Basket(products);
		 assertEquals(0.0f, basket.calculateTotalPrice(), 0.00);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testConstructorThrowsWhenNullPass() {
		 basket = new Basket(null);
	}

	@Test
	public void testCalculateDiscountsPass() {
		@SuppressWarnings("serial")
		ArrayList<Product> products = new ArrayList<Product>() {{
			 add(new Food("Test Food 1", "A tasty bit of testing 1", 2.10f, "", "FOOD123"));
			 add(new Food("Test Food 2", "A tasty bit of testing 2", 2.10f, "", "FOOD124"));
			 add(new Food("Test Food 3", "A tasty bit of testing 3", 2.10f, "", "FOOD125"));
			 add(new Drink("Test Drink 1", "A tasty bit of testing", 1.10f, "", "DRINK123"));
			 add(new Memoribilia("Test Memoribilia 1", "A tasty bit of testing", 1.00f, "", "MEM123"));
			 add(new Memoribilia("Test Memoribilia 2", "A tasty bit of testing", 1.00f, "", "MEM124"));
		}};
		basket = new Basket(products);
		float expected = basket.calculateTotalPrice() * 0.8f;
		assertEquals(expected, basket.calculateDiscounts(), 0.01);
	}
	
	@Test
	public void testCalculateDiscountsDoesntGiveDiscountWhenLessThanTwoMemAndOneDrinkPass() {
		@SuppressWarnings("serial")
		ArrayList<Product> products = new ArrayList<Product>() {{
			 add(new Food("Test Food 1", "A tasty bit of testing 1", 2.10f, "", "FOOD123"));
			 add(new Food("Test Food 2", "A tasty bit of testing 2", 2.10f, "", "FOOD124"));
			 add(new Food("Test Food 3", "A tasty bit of testing 3", 2.10f, "", "FOOD125"));
			 add(new Memoribilia("Test Memoribilia 1", "A tasty bit of testing", 1.00f, "", "MEM123"));
		}};
		basket = new Basket(products);
		float expected = basket.calculateTotalPrice();
		assertEquals(expected, basket.calculateDiscounts(), 0.00);
	}
	
	@Test
	public void getProductsReturnsCorrectNumberOfProductsPass() {
		ArrayList<Product> products = new ArrayList<Product>() {{
			 add(new Food("Test Food 1", "A tasty bit of testing 1", 2.10f, "", "FOOD123"));
		}};
		basket = new Basket(products);
		assertEquals(1, basket.getProducts().size());
	}
	

}
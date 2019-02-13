package order;

import java.util.ArrayList;

public class Basket {
	private ArrayList<Product> products;
	
	
	public Basket(ArrayList<Product> products) {
		if (products == null) throw new IllegalArgumentException("Products cannot be null.");
		this.products = products;
	}
	
	public ArrayList<Product> getProducts() {
		return this.products;
	}
	
	public float calculateDiscounts() {
		float result = calculateTotalPrice();
		int countMemoribilia = 0;
		int countDrinks = 0;
		
		for (Product p: products) {
			if (p instanceof Memoribilia) countMemoribilia += 1;
			else if (p instanceof Drink) countDrinks += 1;
		}
		
		if (countMemoribilia >= 2 && countDrinks >= 1) {
			result -= (0.2 * result);
		}
		
		return result;
	}
	
	public float calculateTotalPrice() {
		float total = 0;
		
		for (Product p: products) {
			total += p.getPrice();
		}
		
		return total;
	}
}
package order;

import java.util.ArrayList;

public class Basket {
	private ArrayList<Product> products;
	
	public Basket() {
		products = new ArrayList<>();
	}
	
	public ArrayList<Product> getProducts() {
		return this.products;
	}
	
	public void addProduct(Product p) {
		this.products.add(p);
	}
	
	public void removeProduct(Product p) {
		this.products.remove(p);
	}
	
	public void clearBasket() {
		this.products.clear();
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
package order;

import java.util.ArrayList;

public class Basket {
	private ArrayList<Product> products;
	
	// Constructor
	public Basket() {
		products = new ArrayList<>();
	}
	
	// Gets Products
	public ArrayList<Product> getProducts() {
		return products;
	}
	
	// Adds Products to underlying list
	public void addProduct(Product p) {
		products.add(p);
	}
	
	// Removes Products from underlying list
	public void removeProduct(Product p) {
		products.remove(p);
	}
	
	// Clears products list
	public void clearBasket() {
		products.clear();
	}
	
	// Calculates the discounted total
	public static float calculateDiscountedTotal(ArrayList<Product> pList) {
		float result = calculateTotalPrice(pList);
		int countMemoribilia = 0;
		int countDrinks = 0;
		
		// Count up instances of Drink and Mem
		for (Product p: pList) {
			if (p instanceof Memoribilia) countMemoribilia += 1;
			else if (p instanceof Drink) countDrinks += 1;
		}
		
		// If 2+ Mem and 1+ Drink, 20% off
		if (countMemoribilia >= 2 && countDrinks >= 1) {
			result -= (0.2 * result);
		}
		
		return result;
	}

	// Calculates the total before discounts
	public static float calculateTotalPrice(ArrayList<Product> pList) {
		float total = 0;
		
		// Total up the prices from products
		for (Product p: pList) {
			total += p.getPrice();
		}
		
		return total;
	}
}
package order;

import java.util.ArrayList;

public class Basket {
	private ArrayList<Product> products;
	
	public Basket() {
		products = new ArrayList<>();
	}
	
	public ArrayList<Product> getProducts() {
		return products;
	}
	
	public void addProduct(Product p) {
		products.add(p);
	}
	
	public void removeProduct(Product p) {
		products.remove(p);
	}
	
	public void clearBasket() {
		products.clear();
	}
	
	public static float calculateDiscountedTotal(ArrayList<Product> pList) {
		float result = calculateTotalPrice(pList);
		int countMemoribilia = 0;
		int countDrinks = 0;
		
		for (Product p: pList) {
			if (p instanceof Memoribilia) countMemoribilia += 1;
			else if (p instanceof Drink) countDrinks += 1;
		}
		
		if (countMemoribilia >= 2 && countDrinks >= 1) {
			result -= (0.2 * result);
		}
		
		return result;
	}
	
	public static float calculateTotalPrice(ArrayList<Product> pList) {
		float total = 0;
		
		for (Product p: pList) {
			total += p.getPrice();
		}
		
		return total;
	}
}
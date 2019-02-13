package order;

import java.util.ArrayList;

public class Basket {
	private ArrayList<Product> products;
	
	
	public Basket(ArrayList<Product> products) {
		if (products == null) throw new IllegalArgumentException("Products cannot be null.");
		this.products = products;
	}
	
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
		float total = 0;
		int countMemoribilia = 0;
		int countDrinks = 0;
		
		for (Product p: products) {
			total += p.getPrice();
			if (p.getId().contains("MEM")) countMemoribilia += 1;
			else if (p.getId().contains("BEV")) countDrinks += 1;
		}
		
		if (countMemoribilia >= 2 && countDrinks >= 1) {
			return (float) (0.2 * total);
		}
		
		return 0;
	}
	
	public float calculateTotalPrice() {
		float total = 0;
		
		for (Product p: products) {
			total += p.getPrice();
		}
		
		return total-calculateDiscounts();
	}
}
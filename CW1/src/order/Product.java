package order;

/**
 * This class is a base class for all products in a menu file.
 * Derived classes Food, Drink, and Memorabilia extend from this.
 */
public class Product {

	private String name;
	private String desc;
	private float price;
	private String id;
	
	public Product(String name, String desc, float price, String id) {
		this.name = name;
		this.desc = desc;
		this.price = price;
		this.id = id;
	}
	
	/* Getters */
	
	public String getName() {
		return name;
	}

	public String getDesc() {
		return desc;
	}

	public float getPrice() {
		return price;
	}

	public String getId() {
		return id;
	}
	
	//added in for GUI
	public String toString() { 
	    return name;   		
	} 
}

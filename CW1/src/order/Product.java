package order;


public class Product {

	private String name;
	private String desc;
	private float price;
	private String id;
	
	public Product(String name, String desc, float price2, String id) {
		this.name = name;
		this.desc = desc;
		this.price = price2;
		this.id = id;
	}

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
	
	public String getMenuDisplay() {
		return name + " £" + price;	
	}
	
	//added in for GUI
	public String toString() { 
	    return name;
	    		
	} 
	
	
}

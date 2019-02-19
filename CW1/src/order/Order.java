package order;

/**
 * This class holds a single order which consists of one product
 * of type Food, Drink, or Memorabilia, a timestamp, and a customer ID.
 */
public class Order {

	private long timestamp;
	private Product product;
	private String custID;
	
	public Order(long timestamp, Product product, String custID) {
		this.timestamp = timestamp;
		this.product = product;
		this.custID = custID;
	}
	
	/* Getters */
	
	public long getTimestamp() {
		return timestamp;
	}

	public Product getProduct() {
		return product;
	}

	public String getCustID() {
		return custID;
	}
	
}

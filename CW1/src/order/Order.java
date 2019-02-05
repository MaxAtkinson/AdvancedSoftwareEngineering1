package order;

public class Order {

	private long timestamp;
	private Product product;
	private String custID;
	
	public Order(long timestamp, Product product, String custID) {
		this.timestamp = timestamp;
		this.product = product;
		this.custID = custID;
	}
	


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

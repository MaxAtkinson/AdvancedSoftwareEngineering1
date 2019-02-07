package fileManagerIO;

import java.util.*;
import java.util.Date;

import order.Order;
import order.Product;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class FileManagerIO {
	//Making this a singleton class
	private static FileManagerIO firstInstance = null;
	private FileManagerIO() {}
	public static FileManagerIO getInstances() {
		if(firstInstance == null) {
			firstInstance = new FileManagerIO();
		}
		return firstInstance;
	}
	
	ArrayList<Order> currentOrders;
	ArrayList<Order> existingOrders;
	TreeSet<Product> products;
	private int rows = 0;

	public int getSizeOfCurrentOrder() 
	{
		int size  = currentOrders.size();
		return size;
	}

	public int getSizeOfExistingOrder() 
	{
		int size  = existingOrders.size();
		return size;
	}

	public int getNumberOfProducts() 
	{
		int size  = products.size();
		return size;
	}

	//reads each line of a file that's passed to it
	public void readFromProductsFile(String fileName) 
	{
		File file = new File(fileName);
		try {
			Scanner scanner = new Scanner(file);	
			while (scanner.hasNextLine()) {
				String inputLine = scanner.nextLine();
				processMenuLine(inputLine);
			}
		}
		catch (FileNotFoundException e) {
			System.out.print("File: " + fileName + " cannot be found.");
		}
	}
	//processes each line of the Products file.
	private void processMenuLine(String inputLine) {
		String part[] = inputLine.split(",");
		String name = part[0];
		String desc = part[1];
		float price = Float.parseFloat(part[2]);
		String cat = part[3];
		String id = part[4];
		Product thisProduct = new Product(name, desc, price, cat, id);
		this.addProduct(thisProduct);
	}

	//reads each line of a file that's passed to it
	public void readFromOrderFile(String fileName) 
	{
		File file = new File(fileName);
		try {
			Scanner scanner = new Scanner(file);	
			while (scanner.hasNextLine()) {
				String inputLine = scanner.nextLine();
				processOrderLine(inputLine);
			}
		}
		catch (FileNotFoundException e) {
			System.out.print("File: " + fileName + " cannot be found.");
		}
	}
	//processes each line of the Products file.
	private void processOrderLine(String inputLine) {
		String part[] = inputLine.split(",");
		long timeStamp = Integer.parseInt(part[0]);
		Product product = findProduct(part[2]);
		String custID = part[1];
		Order a = new Order(timeStamp, product, custID);
		this.addExistingOrder(a);
		rows = rows + 1;

	}		

	//adds products from file to an array list
	private void addProduct(Product p) {
		products.add(p);
	}

	//adds orders from file to existing orders list
	private void addExistingOrder(Order o) {
		existingOrders.add(o);
	}

	private String createCustomerID() {
		Order lastOrder = existingOrders.get(existingOrders.size()-1);
		String lastCustomerID = lastOrder.getCustID();
		int lastCustomerNum = Integer.parseInt(lastCustomerID.substring(2));
		int newCustomerNum = lastCustomerNum + 1;
		String newCustomerStr = Integer.toString(newCustomerNum);
		String newCustomerID = "CUS" + newCustomerStr;
		return newCustomerID;
	}

	//adds new orders (from the GUI) to current orders
	public void addCurrentOrder(Product p) throws IOException {
		Date date = new Date();
		long timeStamp = date.getTime();
		String customerID = createCustomerID();
		Order o = new Order(timeStamp, p, customerID);
		store(o);
		currentOrders.add(o);
	}

	//checks if a string is a number - used in process line
	public static boolean isNumber(String c){
		boolean isNumber = true;
		try {
			Integer.parseInt(c);
		}
		catch (NumberFormatException e){
			isNumber = false;
		}
		return isNumber;
	}

	//for creating existing orders. Finds Product objects from product ID
	private Product findProduct(String productID) {
		Product thisProduct = null;
		for (Product a : products) {
			if (a.getId() == productID) {
				thisProduct = a;
			}
		}
		return thisProduct;
	}
	public void store(Order o) throws IOException {
		FileWriter writer = new FileWriter("Orders.csv");
		String timestamp = Long.toString(o.getTimestamp());
		String customerID = o.getCustID();
		Product product = o.getProduct();
		String productID = product.getId();
		writer.append(timestamp);
		writer.append(",");
		writer.append(customerID);
		writer.append(",");
		writer.append(productID);
		writer.append("\n");
	}
	private int timesProductWasOrdered(Product p) {
		int timesOrdered = 0;
		for(Order o: currentOrders) {
			if(o.getProduct() == p) {
				timesOrdered = timesOrdered + 1;
			}
		}
		for(Order o: existingOrders) {
			if(o.getProduct() == p) {
				timesOrdered = timesOrdered + 1;
			}
		}
		return timesOrdered;
	}
	private double totalIncome() {
		double totalIncome = 0;
		for(Order o: currentOrders) {
			Product p = o.getProduct();
			double price = p.getPrice();
			totalIncome = totalIncome + price;
		}
		for(Order o: existingOrders) {
			Product p = o.getProduct();
			double price = p.getPrice();
			totalIncome = totalIncome + price;
		}
		
		return totalIncome;
	}
	public void writeReport(String filename) throws IOException {
		FileWriter fw = new FileWriter (filename); {
			fw.write("These are all the products on offer:\n");
			for (Product p: products) {
				fw.write(p.getMenuDisplay() + " " + p.getDesc() + ". This item was ordered a total of " + timesProductWasOrdered(p) + " times.\n");
			}
			fw.write("The total income was: " + totalIncome() + "\n");
			fw.close();
		}
	}
}
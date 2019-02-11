package fileManagerIO;

import java.util.*;
import java.util.Date;

import order.Order;
import order.Product;
import utils.ProductComparator;

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
	
	private ArrayList<Order> currentOrders = new ArrayList<>();
	private ArrayList<Order> existingOrders = new ArrayList<>();
	private TreeSet<Product> products = new TreeSet<Product>(new ProductComparator());

	public int getSizeOfCurrentOrders() 
	{
		int size  = currentOrders.size();
		return size;
	}

	public int getSizeOfExistingOrders() 
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
			String inputLine = scanner.nextLine(); // skip headers line
			// reset products list to prevent repeating info on each file read
			products = new TreeSet<Product>(new ProductComparator());
			while (scanner.hasNextLine()) {
				inputLine = scanner.nextLine();
				processMenuLine(inputLine);
			}
			scanner.close();
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
		Product p = new Product(name, desc, price, cat, id);
		products.add(p);
	}

	//reads each line of a file that's passed to it
	public void readFromOrderFile(String fileName) 
	{
		File file = new File(fileName);
		try {
			Scanner scanner = new Scanner(file);
			String inputLine = scanner.nextLine(); // skip headers line
			// reset list of orders to prevent repeating info on each file read
			existingOrders = new ArrayList<>();
			while (scanner.hasNextLine()) {
				inputLine = scanner.nextLine();
				processOrderLine(inputLine);
			}
			scanner.close();
		}
		catch (FileNotFoundException e) {
			System.out.print("File: " + fileName + " cannot be found.");
		}
	}
	
	//processes each line of the Products file.
	private void processOrderLine(String inputLine) {
		String part[] = inputLine.split(",");
		long timeStamp = Long.parseLong(part[0]);
		Product product = findProduct(part[2]);
		String custID = part[1];
		Order o = new Order(timeStamp, product, custID);
		existingOrders.add(o);

	}		

	private String createCustomerID() {
		// TODO handle exception if no existing orders (i.e. no orders in file)
		Order lastOrder = existingOrders.get(existingOrders.size()-1);
		String lastCustomerID = lastOrder.getCustID();
		long lastCustomerNum = Long.parseLong(lastCustomerID.substring(3));
		long newCustomerNum = lastCustomerNum + 1;
		String newCustomerStr = Long.toString(newCustomerNum);
		String newCustomerID = "CUS" + newCustomerStr;
		return newCustomerID;
	}

	//adds new orders (from the GUI) to current orders
	public void addCurrentOrder(ArrayList<Product> pList) {
		Date date = new Date();
		long timeStamp = date.getTime();
		String customerID = createCustomerID();
		for (Product p : pList) {
			Order o = new Order(timeStamp, p, customerID);
			try {
				store(o);
				currentOrders.add(o);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	//for creating existing orders. Finds Product objects from product ID
	private Product findProduct(String productID) {
		Product thisProduct = null;
		for (Product a : products) {
			if (a.getId().equals(productID)) {
				return a;
			}
		}
		return thisProduct;
	}
	
	public void store(Order o) throws IOException {
		FileWriter writer = new FileWriter("Orders.csv", true);
		String timestamp = Long.toString(o.getTimestamp());
		String customerID = o.getCustID();
		Product product = o.getProduct();
		String productID = product.getId();
		writer.write(timestamp);
		writer.write(",");
		writer.write(customerID);
		writer.write(",");
		writer.write(productID);
		writer.write("\n");
		writer.close();
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
	
	private float totalIncome() {
		float totalIncome = 0;
		for(Order o: currentOrders) {
			Product p = o.getProduct();
			float price = p.getPrice();
			totalIncome = totalIncome + price;
		}
		for(Order o: existingOrders) {
			Product p = o.getProduct();
			String s = p.getCat();
			float price = p.getPrice();
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
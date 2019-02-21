package fileManagerIO;

import java.util.*;
import java.util.Date;

import customExceptions.InvalidProductIdentifierException;
import customExceptions.InvalidProductPriceException;
import order.Basket;
import order.Drink;
import order.Food;
import order.Memoribilia;
import order.Order;
import order.Product;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
/**
 * This class is responsible for reading in CSV files and creating objects used throughout
 * the rest of the program.  
 */
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

	private ArrayList<Order> existingOrders = new ArrayList<>();
	private Set<Product> products = new HashSet<Product>();

	/* Getters are used in Junit tests to ensure the effectiveness of private methods elsewhere in the class. */
	public int getSizeOfExistingOrders() 
	{
		return existingOrders.size();
	}

	public int getNumberOfProducts() 
	{
		return products.size();
	}

	public Set<Product> getProducts() {
		return products;
	}
	 	
	/* Used to read in a CSV with product information. Passes this read information to the processMenuLine() method.*/
	public void readFromProductsFile(String fileName) throws InvalidProductPriceException, InvalidProductIdentifierException
	{
		File file = new File(fileName);
		try {
			Scanner scanner = new Scanner(file);	
			String inputLine = scanner.nextLine(); // skip headers line
			// reset products list to prevent repeating info on each file read
			products = new HashSet<Product>();
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
	
	/* Takes in lines from the CSV and creates objects of type Drink, Food, or Memorabilia */
	private void processMenuLine(String inputLine) throws NumberFormatException, InvalidProductPriceException, InvalidProductIdentifierException {
		String part[] = inputLine.split(",");
		String id = part[part.length-1];
		String name = part[0];
		String desc = part[1];
		try {
		float price = Float.parseFloat(part[2]);
		String cat = part[3];
		if (cat.contentEquals("Food")) {
			Food p = new Food(name, desc, price, id);
			products.add(p);
		} else if (cat.contentEquals("Beverage")) {
			Drink p = new Drink(name, desc, price, id);
			products.add(p);
		} else if (cat.contentEquals("Memorabilia")) {
			Memoribilia p = new Memoribilia(name, desc, price, id);
			products.add(p);
			// no else for readability
		}
		} catch(NumberFormatException a) {
			throw new InvalidProductPriceException("Product ID " + id + " has an invalid price");
			
		}
	}
	/* Used to read in a CSV with previous orders information. Passes this read information to the processOrderLine() method.*/	
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
	
	/* Takes in lines from the CSV and creates objects of type Order placing them in the ArrayList existing orders.*/
	private void processOrderLine(String inputLine) {
		try {
			String part[] = inputLine.split(",");
			long timeStamp = Long.parseLong(part[0]);
			Product product = findProduct(part[2]);
			String custID = part[1];
			Order o = new Order(timeStamp, product, custID);
			existingOrders.add(o);
		}catch(NumberFormatException ex) {
			ex.printStackTrace();
		}
	}
	
	/* Creates a new customer idea for new orders passed from the GUI.*/
	private String createCustomerID() {
		if (existingOrders.size()==0) {
			return "CUS" + 1;
		}
		Order lastOrder = existingOrders.get(existingOrders.size()-1);
		String lastCustomerID = lastOrder.getCustID();
		long lastCustomerNum = Long.parseLong(lastCustomerID.substring(3));
		long newCustomerNum = lastCustomerNum + 1;
		String newCustomerStr = Long.toString(newCustomerNum);
		return "CUS" + newCustomerStr;
	}
	
	/* Adds new orders to the existing orders array list passed from the GUI*/
	public void addCurrentOrder(ArrayList<Product> pList) {
		Date date = new Date();
		long timeStamp = date.getTime();
		String customerID = createCustomerID();
		for (Product p : pList) {
			Order o = new Order(timeStamp, p, customerID);
			try {
				store(o);
				existingOrders.add(o);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/* Used to find the Product objects which are stored in orders*/
	private Product findProduct(String productID) {
		Product thisProduct = null;
		for (Product a : products) {
			if (a.getId().equals(productID)) {
				return a;
			}
		}
		return thisProduct;
	}
	
	/* Writes new orders to the Orders.csv*/
	public void store(Order o) throws IOException {
		FileWriter fw = new FileWriter("Orders.csv", true);
		String timestamp = Long.toString(o.getTimestamp());
		String customerID = o.getCustID();
		Product product = o.getProduct();
		String productID = product.getId();
		fw.write(timestamp + "," + customerID + "," + productID + "\n");
		fw.close();
	}

	/* Used by writeReport() to find the number of times a product was ordered*/
	private int timesProductWasOrdered(Product p) {
		int timesOrdered = 0;
		for(Order o: existingOrders) {
			if(o.getProduct() == p) {
				timesOrdered++;
			}
		}
		return timesOrdered;
	}
	
	/* Used by writeReport() to calculate the total income.*/
	private float totalIncome() {
		float totalIncome = 0;
		ArrayList<Product> oneCustomer = new ArrayList<>();
		String custID = "";
		for(int i=0; i < existingOrders.size(); i++) {
			Order o = existingOrders.get(i);
			if (o.getCustID().equals(custID)) {
				oneCustomer.add(o.getProduct());
				if (i == existingOrders.size()-1) {
					totalIncome += Basket.calculateDiscountedTotal(oneCustomer);
				}
			} else {
				totalIncome += Basket.calculateDiscountedTotal(oneCustomer);
				oneCustomer.clear();
				oneCustomer.add(o.getProduct());
				custID = o.getCustID();
				if (i == existingOrders.size()-1) {
					totalIncome += o.getProduct().getPrice();
				}
			}
		}
		return totalIncome;
	}

	/* Writes the final report to file. Called by the GUI on clicking the 'QUIT' button.*/
	public void writeReport(String filename) throws IOException {
		FileWriter fw = new FileWriter (filename); {
			fw.write("These are all the products on offer:\n");
			for (Product p: products) {
				fw.write(p.getName() + "£" + p.getPrice() + " " + p.getDesc() + 
						". This item was ordered a total of " + timesProductWasOrdered(p) + " times.\n");
			}
			fw.write("The total income was: " + totalIncome() + "\n");
			fw.close();
		}
	}
}
package fileManagerIO;

import java.util.*;

import order.Order;
import order.Product;

import java.io.File;
import java.io.FileNotFoundException;

public class FileManagerIO {
	ArrayList<Order> currentOrders;
	ArrayList<Order> existingOrders;
	TreeSet<Product> products;

//reads each line of a file that's passed to it
	public void readFromFile(String fileName) 
	{
		File file = new File(fileName);
		try {
			Scanner scanner = new Scanner(file);	
			while (scanner.hasNextLine()) {
				String inputLine = scanner.nextLine();
				processLine(inputLine);
			}
		}
		catch (FileNotFoundException e) {
			System.out.print("File: " + fileName + " cannot be found.");
		}	
	}
//adds products from file to an array list
	private void addProduct(Product c) {
		products.add(c);
	}
//adds orders from file to existing orders list
	private void addExistingOrder(Order a) {
		existingOrders.add(a);
	}
//adds new orders (from the GUI) to current orders
	private void addCurrentOrder(Order a) {
		currentOrders.add(a);
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
//for creating existing orders. Finds Product obejects from product ID
	private Product findProduct(String productID) {
		Product thisProduct = null;
		for (Product a : products) {
			if (a.getId() == productID) {
				thisProduct = a;
			}
		}
		return thisProduct;
	}
//processes each line of the file.
	private void processLine(String inputLine) {
		String part[] = inputLine.split(",");
		if (isNumber(part[1]) == true) {
			int timeStamp = Integer.parseInt(part[1]);
			Product product = findProduct(part[3]);
			String custID = part[2];
			Order a = new Order(timeStamp, product, custID);
			this.addExistingOrder(a);
		}
		else {
			String name = part[1];
			String desc = part[2];
			float price = Float.parseFloat(part[3]);
			String cat = part[4];
			String id = part[5];
			Product thisProduct = new Product(name, desc, price, cat, id);
			this.addProduct(thisProduct);
		}
	}
}
package main;

import order.*;
import fileManagerIO.*;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;

public class Program extends JFrame {

	//Instance Variables
	static String productsFileName = "Products.csv";
	static String ordersFileName = "Orders.csv";
	static FileManagerIO f;
	Product p;
	ArrayList<Product> addToBasketArray = new ArrayList<Product>();
	Product[] arrayListToArray;
	private static String currentTreeSelection;
	private static Product curentListSelection;
	private static Basket currentBasket;

	// GUI Instance Variables
	private JButton buttonAdd, buttonRemove, buttonConfirm, buttonQuit, buttonCancel;
	private JList<Product> orderList;
	private JTree menuTree;
	private JScrollPane menuPane, orderPane;
	private static JLabel discount, total;
	
	
	
	
	
	

	public static void main(String[] args) {
		f = FileManagerIO.getInstances();
		f.readFromProductsFile(productsFileName);
		f.readFromOrderFile(ordersFileName);

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new Program().setVisible(true);
			}
		});
	}

	
	
	
	

	/** Initialising GUI Defaults */
	public Program() {

		createView();
		setTitle("ASE Coffee Shop");
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setSize(600, 425);
		setLocationRelativeTo(null);
		setResizable(false);
	}
	
	
	
	
	

	/** GUI Code */
	private void createView() {

		/** Creating Main GUI Panel */
		JPanel panelMain = new JPanel();
		getContentPane().add(panelMain);

		/**
		 * Creating GUI pane upon which additional functionality can be added
		 */
		JPanel panelForm = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		panelMain.add(panelForm);

		/**
		 * Here we use "c" and the coordinate system implemented by
		 * GridBagConstriants to specify the location of our buttons and
		 * JScrollPanes ect.
		 */
		
		
		
		
		

		/**JTREE*/
		DefaultMutableTreeNode root = new DefaultMutableTreeNode("Products");
		createNodes(root);
		menuTree = new JTree(root);
		menuPane = new JScrollPane(menuTree);

		// enforcing single item selection
		menuTree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);

		// Adding Tree Selection listener
		menuTree.addTreeSelectionListener(new TreeSelectionListener() {
			public void valueChanged(TreeSelectionEvent e) {
				DefaultMutableTreeNode node = (DefaultMutableTreeNode) menuTree.getLastSelectedPathComponent();

				/* if nothing is selected */
				if (node == null)
					return;
				
				/* retrieve the node that was selected and store string in static varable */
				currentTreeSelection = menuTree.getLastSelectedPathComponent().toString();
			}
		});

		//formatting
		menuPane.setPreferredSize(new Dimension(200, 250));
		menuPane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Menu",
				TitledBorder.LEFT, TitledBorder.TOP));
		c.anchor = GridBagConstraints.LINE_START;
		c.insets = new Insets(0, 0, 10, 0);
		c.gridheight = 6;
		c.gridx = 0;
		c.gridy = 0;
		panelForm.add(menuPane, c);
		c.insets = new Insets(0, 0, 0, 0);
		
		
		
		
		
		/**JList*/
		orderList = new JList<Product>();
		orderPane = new JScrollPane(orderList);

		// enforcing single item selection
		orderList.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		// Adding List Selection listener
		orderList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {

				orderList.getSelectedValue();
				curentListSelection = orderList.getSelectedValue();

			}
		});

		//formatting
		orderPane.setPreferredSize(new Dimension(300, 200));
		orderPane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Order",
				TitledBorder.LEFT, TitledBorder.TOP));
		c.anchor = GridBagConstraints.PAGE_START;
		c.insets = new Insets(0, 15, 0, 0);
		c.gridheight = 3;
		c.gridx = 3;
		c.gridy = 0;
		panelForm.add(orderPane, c);
		c.insets = new Insets(0, 0, 0, 0);

		
		
		
		

		/**DISCOUNT JLABLE*/
		discount = new JLabel("Discounts: ");
		c.gridx = 3;
		c.gridy = 3;
		c.anchor = GridBagConstraints.LINE_START;
		c.insets = new Insets(0, 15, 10, 0);
		panelForm.add(discount, c);
		c.insets = new Insets(0, 0, 0, 0);
		
		
		
		
		

		/**TOTAL JLABLE*/
		total = new JLabel("Total: ");
		c.gridx = 3;
		c.gridy = 3;
		c.anchor = GridBagConstraints.LAST_LINE_START;
		c.insets = new Insets(0, 15, 10, 0);
		panelForm.add(total, c);
		c.insets = new Insets(0, 0, 0, 0);
		
		
		
		
		

		/**CANCEL BUTTON*/
		buttonCancel = new JButton("Cancel Order");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.LAST_LINE_START;
		c.gridx = 0;
		c.gridy = 9;

		// Following methods executed  when the button is pressed
		buttonCancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				addToBasketArray.clear();
				Basket y = new Basket(addToBasketArray);
				total.setText("Total: £ " + Math.round((y.calculateTotalPrice()) * 100.00) / 100.00);
				discount.setText("Discount: £ " + Math.round((y.calculateDiscounts()) * 100.00) / 100.00);

				Product[] array = addToBasketArray.toArray(new Product[addToBasketArray.size()]);
				orderList.setListData(array);

				JOptionPane.showMessageDialog(null, "Order has been sucessfully cancelled");
				return;
			}
		});
		panelForm.add(buttonCancel, c);
		c.fill = GridBagConstraints.NONE;
		
		
		
		

		/**CONFIRM BUTTON*/
		//format
		buttonConfirm = new JButton("      Confirm Order      ");
		c.anchor = GridBagConstraints.LINE_END;
		c.gridx = 3;
		c.gridy = 6;

		//  following executed methods when the button is pressed
		buttonConfirm.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				f.addCurrentOrder(currentBasket.getProducts());
				JOptionPane.showMessageDialog(null, "Order Confirmed");

				addToBasketArray.clear();
				Basket y = new Basket(addToBasketArray);
				total.setText("Total: £ " + Math.round((y.calculateTotalPrice()) * 100.00) / 100.00);
				discount.setText("Discount: £ " + Math.round((y.calculateDiscounts()) * 100.00) / 100.00);

				Product[] array = addToBasketArray.toArray(new Product[addToBasketArray.size()]);
				orderList.setListData(array);

				return;
			}
		});
		panelForm.add(buttonConfirm, c);
		
		
		
		
		
		/**REMOVE BUTTON*/
		//format
		buttonAdd = new JButton("Add to Order");
		c.anchor = GridBagConstraints.LINE_START;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 6;

		//  Following methods executed when the button is pressed
		buttonAdd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				//If leaf node not selected, display message and return
				if (currentTreeSelection.contains("Products") || currentTreeSelection.contains("Food") || currentTreeSelection.contains("Drink")
						|| currentTreeSelection.contains("Memorobilia")) {
					JOptionPane.showMessageDialog(null, "Please select a product to add to the basket");
					return;
				}

				
				for (Product p : f.products) {

					if (p.getName().equals(currentTreeSelection)) {
						addToBasketArray.add(p);
						
					}

				}
				currentBasket = new Basket(addToBasketArray);
				total.setText("Total: £ " + Math.round((currentBasket.calculateTotalPrice()) * 100.00) / 100.00);
				discount.setText("Discount: £ " + Math.round((currentBasket.calculateDiscounts()) * 100.00) / 100.00);

				Product[] array = addToBasketArray.toArray(new Product[addToBasketArray.size()]);
				orderList.setListData(array);

				
				return;
			}
		});

		panelForm.add(buttonAdd, c);
		c.fill = GridBagConstraints.NONE;

		
		
		
		/**REMOVE BUTTON*/
		//Layout
		buttonRemove = new JButton("      Remove Item      ");
		c.gridx = 3;
		c.gridy = 6;
		c.anchor = GridBagConstraints.LINE_START;
		c.insets = new Insets(0, 15, 0, 0);

		//Following methods executed when the button is pressed
		buttonRemove.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (curentListSelection == null) {
					JOptionPane.showMessageDialog(null, "Please Select an Item to Remove");
					return;
				}

				addToBasketArray.remove(curentListSelection);
				currentBasket = new Basket(addToBasketArray);
				
				total.setText("Total: £ " + Math.round((currentBasket.calculateTotalPrice()) * 100.00) / 100.00);
				discount.setText("Discount: £ " + Math.round((currentBasket.calculateDiscounts()) * 100.00) / 100.00);
				
				Product[] array = addToBasketArray.toArray(new Product[addToBasketArray.size()]);
				orderList.setListData(array);
				return;
			}
		});

		panelForm.add(buttonRemove, c);
		c.insets = new Insets(0, 0, 0, 0);
		
		
		
		
		
		/**QUIT BUTTON*/
		//Format
		buttonQuit = new JButton("   Quit   ");
		c.gridx = 3;
		c.gridy = 9;
		c.insets = new Insets(35, 0, 0, 0);
		c.anchor = GridBagConstraints.LAST_LINE_END;

		//Following methods executed when the button is pressed
		buttonQuit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					f.writeReport("Report.txt");
					System.exit(0);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				return;
			}
		});
		panelForm.add(buttonQuit, c);

	}
	
	
	
	
	

	
	/**Method for creating JTree Nodes*/
	private void createNodes(DefaultMutableTreeNode root) {
		DefaultMutableTreeNode category = null;
		DefaultMutableTreeNode food = null;
		DefaultMutableTreeNode drink = null;
		DefaultMutableTreeNode mem = null;

		category = new DefaultMutableTreeNode("Food");
		root.add(category);

		for (Product p : f.products) {
			if (p.getId().contains("FOOD")) {
				food = new DefaultMutableTreeNode(p.getName());
				category.add(food);
			}
		}

		category = new DefaultMutableTreeNode("Drink");
		root.add(category);

		for (Product p : f.products) {
			if (p.getId().contains("BEV")) {
				drink = new DefaultMutableTreeNode(p.getName());
				category.add(drink);
			}
		}

		category = new DefaultMutableTreeNode("Memorobilia");
		root.add(category);

		for (Product p : f.products) {
			if (p.getId().contains("MEM")) {
				mem = new DefaultMutableTreeNode(p.getName());
				category.add(mem);
			}
		}

	}



}
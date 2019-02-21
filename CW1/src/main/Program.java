package main;

import order.*;
import fileManagerIO.*;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.DecimalFormat;
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

import customExceptions.InvalidProductIdentifierException;
import customExceptions.InvalidProductPriceException;


public class Program extends JFrame {

	//Instance Variables
	private static String productsFileName = "Products.csv";
	private static String ordersFileName = "Orders.csv";
	private static FileManagerIO f;
	private static String currentSetSelection;
	private static Product curentListSelection;
	private static Basket b;

	// GUI Instance Variables
	private JButton buttonAdd, buttonRemove, buttonConfirm, buttonQuit, buttonCancel;
	private JList<Product> orderList;
	private JTree menuTree;
	private JScrollPane menuPane, orderPane;
	private static JLabel discount, total;
	

	//Main method instantiating single instance of FileManagerIO and running GUI
	public static void main(String[] args) throws InvalidProductPriceException, InvalidProductIdentifierException {
		f = FileManagerIO.getInstances();
		f.readFromProductsFile(productsFileName);
		f.readFromOrderFile(ordersFileName);
		b = new Basket();
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
		initBtnActions();
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

		// adding Tree Selection listener
		menuTree.addTreeSelectionListener(new TreeSelectionListener() {
			public void valueChanged(TreeSelectionEvent e) {
				DefaultMutableTreeNode node = (DefaultMutableTreeNode) menuTree.getLastSelectedPathComponent();

				/* if nothing is selected */
				if (node == null)
					return;
				
				/* retrieve the node that was selected and store string in static varable */
				currentSetSelection = menuTree.getLastSelectedPathComponent().toString();
			}
		});

		//format
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

		// adding List Selection listener
		orderList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {

				/* retrieve the object that was selected*/
				orderList.getSelectedValue();
				curentListSelection = orderList.getSelectedValue();

			}
		});

		//format
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
		//format
		discount = new JLabel("Discounts: ");
		c.gridx = 3;
		c.gridy = 3;
		c.anchor = GridBagConstraints.LINE_START;
		c.insets = new Insets(0, 15, 10, 0);
		panelForm.add(discount, c);
		c.insets = new Insets(0, 0, 0, 0);
		
		/**TOTAL JLABLE*/
		//format
		total = new JLabel("Total: ");
		c.gridx = 3;
		c.gridy = 3;
		c.anchor = GridBagConstraints.LAST_LINE_START;
		c.insets = new Insets(0, 15, 10, 0);
		panelForm.add(total, c);
		c.insets = new Insets(0, 0, 0, 0);
		
		/**CANCEL BUTTON*/
		//format
		buttonCancel = new JButton("Cancel Order");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.LAST_LINE_START;
		c.gridx = 0;
		c.gridy = 9;
		panelForm.add(buttonCancel, c);
		c.fill = GridBagConstraints.NONE;

		/**CONFIRM BUTTON*/
		//format
		buttonConfirm = new JButton("      Confirm Order      ");
		c.anchor = GridBagConstraints.LINE_END;
		c.gridx = 3;
		c.gridy = 6;
		panelForm.add(buttonConfirm, c);

		/**REMOVE BUTTON*/
		//format
		buttonAdd = new JButton("Add to Order");
		c.anchor = GridBagConstraints.LINE_START;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 6;
		panelForm.add(buttonAdd, c);
		c.fill = GridBagConstraints.NONE;

		/**REMOVE BUTTON*/
		//Layout
		buttonRemove = new JButton("      Remove Item      ");
		c.gridx = 3;
		c.gridy = 6;
		c.anchor = GridBagConstraints.LINE_START;
		c.insets = new Insets(0, 15, 0, 0);
		panelForm.add(buttonRemove, c);
		c.insets = new Insets(0, 0, 0, 0);
		
		/**QUIT BUTTON*/
		//Format
		buttonQuit = new JButton("   Quit   ");
		c.gridx = 3;
		c.gridy = 9;
		c.insets = new Insets(35, 0, 0, 0);
		c.anchor = GridBagConstraints.LAST_LINE_END;
		panelForm.add(buttonQuit, c);

	}
	
	//initialising button actions
	private void initBtnActions() {
		
		// following methods executed when the CANCEL button is pressed
		buttonCancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				b.clearBasket();
				setDiscountAndTotal();
				displayBasket();

				JOptionPane.showMessageDialog(null, "Order has been sucessfully cancelled");;
			}
		});
		
		// following methods executed when the CONFIRM ORDER button is pressed
		buttonConfirm.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (b.getProducts().size()!=0) {
					f.addCurrentOrder(b.getProducts());
					JOptionPane.showMessageDialog(null, "Order Confirmed");

					setDiscountAndTotal();
					b.clearBasket();
					displayBasket();
				}
			}
		});
		
		// following methods executed when the CONFIRM ORDER button is pressed
		buttonAdd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				//If leaf node not selected, display message and return
				if (currentSetSelection.contains("Products") || currentSetSelection.contains("Food") || currentSetSelection.contains("Drink")
						|| currentSetSelection.contains("Memorabilia")) {
					JOptionPane.showMessageDialog(null, "Please select a product to add to the basket");
					return;
				}

				for (Product p : f.getProducts()) {
					if (p.getName().equals(currentSetSelection)) {
						b.addProduct(p);
					}
				}
				setDiscountAndTotal();
				displayBasket();
			}
		});
		
		// following methods executed when the REMOVE button is pressed
		buttonRemove.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (curentListSelection == null) {
					JOptionPane.showMessageDialog(null, "Please Select an Item to Remove");
					return;
				}

				b.removeProduct(curentListSelection);
				setDiscountAndTotal();
				displayBasket();
			}
		});
		
		// following methods executed when the QUIT button is pressed
		buttonQuit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) throws NullPointerException {
				try {
					f.writeReport("Report.txt");
					System.exit(0);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
	}
	
	/**Method for creating JTree Nodes*/
	private void createNodes(DefaultMutableTreeNode root) {
		Set<Product> products = f.getProducts();
		
		DefaultMutableTreeNode food = new DefaultMutableTreeNode("Food");
		DefaultMutableTreeNode drink = new DefaultMutableTreeNode("Drink");
		DefaultMutableTreeNode mem = new DefaultMutableTreeNode("Memoribilia");
		root.add(food);
		root.add(drink);
		root.add(mem);

		for (Product p : products) {
			DefaultMutableTreeNode menuItem = new DefaultMutableTreeNode(p.getName());
			if (p.getId().contains("FOOD")) {
				food.add(menuItem);
			} else if (p.getId().contains("BEV")){
				drink.add(menuItem);
			} else if (p.getId().contains("MEM")){
				mem.add(menuItem);
			} // no else for readability
		}

	}
	
	//sets total and discount JLable values
	private void setDiscountAndTotal() {
		ArrayList<Product> pl = b.getProducts();
		total.setText("Total: £" + roundTwoDP(Basket.calculateDiscountedTotal(pl)));
		discount.setText("Discount: -£" + roundTwoDP(Basket.calculateTotalPrice(pl)-Basket.calculateDiscountedTotal(pl)));
	}
	
	//displays products in basket JList
	private void displayBasket() {
		Product[] array = b.getProducts().toArray(new Product[b.getProducts().size()]);
		orderList.setListData(array);
	}
	
	//rounds figures to two decimal places
	private static float roundTwoDP(float d) { 
		DecimalFormat twoDForm = new DecimalFormat("#.##"); 
		return Float.valueOf(twoDForm.format(d)); }
}
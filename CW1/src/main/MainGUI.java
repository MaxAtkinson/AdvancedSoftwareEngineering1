package main;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import javax.swing.JScrollPane;
import java.awt.GridBagConstraints;
import javax.swing.JList;
import java.awt.Insets;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSplitPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.JSeparator;

public class MainGUI extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainGUI frame = new MainGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 475, 340);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{160, 0, 160, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 141, 0, 0, 46, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel lblShopName = new JLabel("ASE Coffee Shop");
		GridBagConstraints gbc_lblShopName = new GridBagConstraints();
		gbc_lblShopName.gridwidth = 3;
		gbc_lblShopName.insets = new Insets(0, 0, 5, 0);
		gbc_lblShopName.gridx = 0;
		gbc_lblShopName.gridy = 0;
		contentPane.add(lblShopName, gbc_lblShopName);
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridheight = 3;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 2;
		contentPane.add(scrollPane, gbc_scrollPane);
		
		JLabel lblMenu = new JLabel("Menu");
		scrollPane.setColumnHeaderView(lblMenu);
		
		JTree tree = new JTree();
		tree.setRootVisible(false);
		tree.setModel(new DefaultTreeModel(
			new DefaultMutableTreeNode("Menu") {
				{
					DefaultMutableTreeNode node_1;
					DefaultMutableTreeNode node_2;
					node_1 = new DefaultMutableTreeNode("Food");
						node_2 = new DefaultMutableTreeNode("Main");
							node_2.add(new DefaultMutableTreeNode("main1"));
						node_1.add(node_2);
						node_2 = new DefaultMutableTreeNode("Snack");
							node_2.add(new DefaultMutableTreeNode("snack1"));
						node_1.add(node_2);
					add(node_1);
					node_1 = new DefaultMutableTreeNode("Drink");
						node_2 = new DefaultMutableTreeNode("Hot");
							node_2.add(new DefaultMutableTreeNode("hot1"));
						node_1.add(node_2);
						node_2 = new DefaultMutableTreeNode("Cold");
							node_2.add(new DefaultMutableTreeNode("cold1"));
						node_1.add(node_2);
					add(node_1);
					node_1 = new DefaultMutableTreeNode("Memorabilia");
						node_1.add(new DefaultMutableTreeNode("mem1"));
						node_1.add(new DefaultMutableTreeNode("mem2"));
					add(node_1);
				}
			}
		));
		scrollPane.setViewportView(tree);
		
		JSeparator separator = new JSeparator();
		GridBagConstraints gbc_separator = new GridBagConstraints();
		gbc_separator.gridheight = 4;
		gbc_separator.insets = new Insets(0, 0, 5, 5);
		gbc_separator.gridx = 1;
		gbc_separator.gridy = 2;
		contentPane.add(separator, gbc_separator);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_1 = new GridBagConstraints();
		gbc_scrollPane_1.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane_1.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_1.gridx = 2;
		gbc_scrollPane_1.gridy = 2;
		contentPane.add(scrollPane_1, gbc_scrollPane_1);
		
		JList list_1 = new JList();
		scrollPane_1.setViewportView(list_1);
		
		JLabel lblOrder = new JLabel("Order");
		scrollPane_1.setColumnHeaderView(lblOrder);
		
		JButton btnAddToOrder = new JButton("Add to Order");
		btnAddToOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		
		JLabel lblDiscounts = new JLabel("Discounts");
		GridBagConstraints gbc_lblDiscounts = new GridBagConstraints();
		gbc_lblDiscounts.fill = GridBagConstraints.BOTH;
		gbc_lblDiscounts.insets = new Insets(0, 0, 5, 0);
		gbc_lblDiscounts.gridx = 2;
		gbc_lblDiscounts.gridy = 3;
		contentPane.add(lblDiscounts, gbc_lblDiscounts);
		
		JLabel lblTotal = new JLabel("Total");
		GridBagConstraints gbc_lblTotal = new GridBagConstraints();
		gbc_lblTotal.fill = GridBagConstraints.BOTH;
		gbc_lblTotal.insets = new Insets(0, 0, 5, 0);
		gbc_lblTotal.gridx = 2;
		gbc_lblTotal.gridy = 4;
		contentPane.add(lblTotal, gbc_lblTotal);
		GridBagConstraints gbc_btnAddToOrder = new GridBagConstraints();
		gbc_btnAddToOrder.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnAddToOrder.insets = new Insets(0, 0, 5, 5);
		gbc_btnAddToOrder.gridx = 0;
		gbc_btnAddToOrder.gridy = 5;
		contentPane.add(btnAddToOrder, gbc_btnAddToOrder);
		
		JSplitPane splitPane = new JSplitPane();
		GridBagConstraints gbc_splitPane = new GridBagConstraints();
		gbc_splitPane.insets = new Insets(0, 0, 5, 0);
		gbc_splitPane.fill = GridBagConstraints.HORIZONTAL;
		gbc_splitPane.gridx = 2;
		gbc_splitPane.gridy = 5;
		contentPane.add(splitPane, gbc_splitPane);
		
		JButton btnRemoveItem = new JButton("Remove Item");
		btnRemoveItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		splitPane.setLeftComponent(btnRemoveItem);
		
		JButton btnConfirmOrder = new JButton("Confirm Order");
		splitPane.setRightComponent(btnConfirmOrder);
		
		JSeparator separator_1 = new JSeparator();
		GridBagConstraints gbc_separator_1 = new GridBagConstraints();
		gbc_separator_1.gridwidth = 3;
		gbc_separator_1.insets = new Insets(0, 0, 5, 0);
		gbc_separator_1.gridx = 0;
		gbc_separator_1.gridy = 6;
		contentPane.add(separator_1, gbc_separator_1);
		
		JButton btnQuit = new JButton("Quit");
		GridBagConstraints gbc_btnQuit = new GridBagConstraints();
		gbc_btnQuit.anchor = GridBagConstraints.SOUTHEAST;
		gbc_btnQuit.gridx = 2;
		gbc_btnQuit.gridy = 7;
		contentPane.add(btnQuit, gbc_btnQuit);
	}

}

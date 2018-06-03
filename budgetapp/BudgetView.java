package budgetapp;

import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JList;
import javax.swing.SpringLayout;

public class BudgetView extends JFrame{

	private JList<BudgetViewItem> expenseList;

	
	/**
	 * Create the application.
	 */
	public BudgetView(BudgetExpenseModel[] expenses) {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout());
		
		JPanel expenseListView = new JPanel();
		expenseListView.setLayout(new BorderLayout());
		expenseListView.setPreferredSize(new Dimension(600, 400));
		DefaultListModel<BudgetViewItem> list = new DefaultListModel<BudgetViewItem>();
		for(BudgetExpenseModel b : expenses){
			BudgetViewItem i = new BudgetViewItem(b);
			list.addElement(i);
		}
		expenseList = new JList<BudgetViewItem>(list);
		JScrollPane scrollExpense = new JScrollPane(expenseList);
		expenseListView.add(scrollExpense, BorderLayout.CENTER);
		getContentPane().add(expenseListView, BorderLayout.CENTER);
		
		JPanel expenseFormView = new JPanel();
		expenseFormView.setLayout(new GridBagLayout());
		expenseFormView.setPreferredSize(new Dimension(600, 50));
		GridBagConstraints gbc = new GridBagConstraints();
		
		Choice primaryCat = new Choice();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.weightx = 0.1;
		expenseFormView.add(primaryCat, gbc);
		
		Choice subCat = new Choice();
		gbc.gridx = 1;
		expenseFormView.add(subCat, gbc);
		
		JButton newExpenseBtn = new JButton("Add Expense");
		gbc.gridx = 5;
		expenseFormView.add(newExpenseBtn, gbc);
		
		gbc.ipady = 1;
		JTextField expenseAmount = new JTextField();
		gbc.gridx = 2;
		expenseFormView.add(expenseAmount, gbc);
		
		JTextField expenseSpender = new JTextField();
		gbc.gridx = 3;
		expenseFormView.add(expenseSpender, gbc);
		
		JTextField expenseDate = new JTextField();
		gbc.gridx = 4;
		expenseFormView.add(expenseDate, gbc);
		
		
		getContentPane().add(expenseFormView, BorderLayout.SOUTH);
		this.pack();
		this.setVisible(true);
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BudgetExpenseModel[] b = {};
					BudgetView window = new BudgetView(b);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}

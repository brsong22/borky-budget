package budgetapp;

import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Hashtable;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class BudgetView extends JFrame{

	private JTable expenseTable;
//	private Choice primaryCat;
//	private Choice subCat;
//	private JTextField expenseAmount;
//	private JTextField expenseSpender;
//	private Choice expenseDate;
	private JPanel expenseListView;
	private Hashtable<String, ArrayList<String>> categories;
	private String[] headers;
	private String[][] data;
	
	/**
	 * Create the application.
	 */
	public BudgetView(BudgetExpenseModel[] expenses) {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(new BorderLayout());
		
		this.categories = new BudgetExpenseCategory().getAllCategories();
		this.expenseListView = new JPanel();
		
		this.headers = BudgetExpenseModel.getHeaders();
		this.data = new String[expenses.length][headers.length];
		populateExpenseTableView(expenses);
		
		JPanel expenseFormView = new JPanel();
		populateExpenseFormView(expenseFormView);
		
		this.getContentPane().add(expenseListView, BorderLayout.CENTER);
		this.getContentPane().add(expenseFormView, BorderLayout.SOUTH);
		this.pack();
		this.setVisible(true);
	}
	
	private void populateExpenseTableView(BudgetExpenseModel[] expenses){
		expenseListView.setLayout(new BorderLayout());
		expenseListView.setPreferredSize(new Dimension(600, 400));

		int count = 0;
		for(BudgetExpenseModel b : expenses){
			data[count] = b.getData();
			count++;
		}
		expenseTable = new JTable(new DefaultTableModel(data, headers));
		JScrollPane scrollExpense = new JScrollPane(expenseTable);
		expenseTable.setFillsViewportHeight(true);
		expenseListView.add(scrollExpense, BorderLayout.CENTER);
	}
	
	private void populateExpenseFormView(JPanel expenseFormView){
		expenseFormView.setLayout(new GridBagLayout());
		expenseFormView.setPreferredSize(new Dimension(600, 50));
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(0, 1, 0, 1);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.weightx = 0.1;
		
		Border b = BorderFactory.createLineBorder(Color.GRAY, 1);
		
		Choice primaryCat = new Choice();
		for(String c : categories.keySet()){
			primaryCat.add(c);
		}
		
		expenseFormView.add(primaryCat, gbc);
		
		Choice subCat = new Choice();
		gbc.gridx = 1;
		expenseFormView.add(subCat, gbc);
		populateExpenseFormSubCat(primaryCat.getSelectedItem(), subCat);
		
		JButton newExpenseBtn = new JButton("Add Expense");
		gbc.gridx = 5;
		expenseFormView.add(newExpenseBtn, gbc);
		
		gbc.ipady = 1;
		JTextField expenseAmount = new JTextField("0.00");
		expenseAmount.setBorder(b);
		expenseAmount.setInputVerifier(new ExpenseFormVerifier("double"));
		gbc.gridx = 2;
		expenseFormView.add(expenseAmount, gbc);
		
		JTextField expenseSpender = new JTextField("Richard");
		expenseSpender.setBorder(b);
		expenseSpender.setInputVerifier(new ExpenseFormVerifier("string"));
		gbc.gridx = 3;
		expenseFormView.add(expenseSpender, gbc);
		
		Choice expenseDate = new Choice();
		populateExpenseFormDate(LocalDate.now(), expenseDate);
		gbc.gridx = 4;
		expenseFormView.add(expenseDate, gbc);
		
		primaryCat.addItemListener(new ItemListener(){
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED){
					populateExpenseFormSubCat(e.getItem().toString(), subCat);
				}
			}	
		});
		
		newExpenseBtn.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				BudgetController.createNewExpense(primaryCat.getSelectedItem(), subCat.getSelectedItem(), Double.parseDouble(expenseAmount.getText()), expenseSpender.getText(), LocalDate.parse(expenseDate.getSelectedItem()));
			}	
		});
	}
	
	private void populateExpenseFormSubCat(String primaryCat, Choice subCat){
		subCat.removeAll();
		for(String s : categories.get(primaryCat)){
			subCat.add(s);
		}
	}
	
	private void populateExpenseFormDate(LocalDate today, Choice expenseDate){
		int maxDays = 32;
		for(int i = 0; i < maxDays; i++){
			LocalDate prevDay = today.minus(i, ChronoUnit.DAYS);
			expenseDate.add(prevDay.toString());
		}
	}
	
	public void addNewExpense(BudgetExpenseModel expense){
		DefaultTableModel t = (DefaultTableModel) expenseTable.getModel();
		t.addRow(expense.getData());
	}
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					LocalDate today = LocalDate.now();
//					BudgetExpenseModel[] b = {new BudgetExpenseModel("Test", "test", 0.00, "richard", today), new BudgetExpenseModel("Test", "test", 5., "becky", today)};
//					BudgetView window = new BudgetView(b);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}
}

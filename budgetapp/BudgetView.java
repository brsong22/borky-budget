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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Hashtable;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class BudgetView extends JFrame{

	private JTable expenseTable;
	private JPanel expenseListView;
	private Hashtable<String, ArrayList<String>> categories;
	
	/**
	 * Create the application.
	 */
	public BudgetView() {
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				System.out.println("closing");
				if(BudgetController.saveExpenses()){
					dispose();
					System.exit(0);	
				}
			}
		});
		this.getContentPane().setLayout(new BorderLayout());
		
		this.categories = new BudgetExpenseCategory().getAllCategories();
		expenseListView = new JPanel();
		expenseListView.setLayout(new BorderLayout());
		expenseListView.setPreferredSize(new Dimension(600, 400));
		JPanel expenseFormView = new JPanel();
		populateExpenseFormView(expenseFormView);
		
		this.getContentPane().add(expenseListView, BorderLayout.CENTER);
		this.getContentPane().add(expenseFormView, BorderLayout.SOUTH);
	}
	
	public void populateExpenseTableView(BudgetExpenseModel[] expenses, String[] headers){
		String[][] data = new String[expenses.length][headers.length];
		
		int count = 0;
		for(BudgetExpenseModel b : expenses){
			data[count] = b.getData();
			count++;
		}
		expenseTable = new JTable(new DefaultTableModel(data, headers));
		expenseTable.setFillsViewportHeight(true);
		expenseTable.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseReleased(MouseEvent e){
				int row = expenseTable.rowAtPoint(e.getPoint());
				if(row >= 0 && row < expenseTable.getRowCount()){
					expenseTable.setRowSelectionInterval(row, row);
				}
				else{
					expenseTable.clearSelection();
				}
				if(expenseTable.getSelectedRow() < 0){
					return;
				}
				JPopupMenu popupMenu = new JPopupMenu();
				JMenuItem deleteExpense = new JMenuItem("Delete Expense");
				deleteExpense.addActionListener(new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent a){
						if(BudgetController.deleteExpense(expenseTable.getSelectedRow())){
							((DefaultTableModel)expenseTable.getModel()).removeRow(expenseTable.getSelectedRow());
						}
						System.out.println("delete clicked");
					}
				});
				popupMenu.add(deleteExpense);
				popupMenu.show(e.getComponent(), e.getX(), e.getY());
			}
		});
		JScrollPane scrollExpense = new JScrollPane(expenseTable);
		expenseListView.add(scrollExpense, BorderLayout.CENTER);
		this.pack();
		this.setVisible(true);
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
}

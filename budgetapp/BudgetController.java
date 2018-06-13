package budgetapp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.ArrayList;

public class BudgetController {
	
	private static BudgetView budgetView;
	private static ArrayList<BudgetExpenseModel> expenseBook = new ArrayList<>();
	
	public BudgetController(BudgetView view){
		BudgetExpenseModel[] expenses = getSavedExpenses();
		for(BudgetExpenseModel m : expenses){
			expenseBook.add(m);
		}
		BudgetController.budgetView = view;
		budgetView.populateExpenseTableView(expenses, getExpenseHeaders());
	}
	
	private static void setExpensePrimaryCategory(BudgetExpenseModel bm, String cat){
		bm.setPrimaryCategory(cat);
	}
	
	private static void setExpenseSubCategory(BudgetExpenseModel bm, String cat){
		bm.setSubCategory(cat);
	}
	
	private static void setExpenseAmount(BudgetExpenseModel bm, Double amt){
		bm.setAmount(amt);
	}
	
	private static void setExpenseSpender(BudgetExpenseModel bm, String name){
		bm.setSpender(name);
	}
	
	private static void setExpenseDate(BudgetExpenseModel bm, LocalDate d){
		bm.setDate(d);
	}
	
	public void viewAddExpense(BudgetExpenseModel expense){
		budgetView.addNewExpense(expense);
	}
	
	public static String[] getExpenseHeaders(){
		return BudgetExpenseModel.getHeaders();
	}
	
	public static void createNewExpense(String pCat, String sCat, Double amt, String spender, LocalDate date){
		BudgetExpenseModel newExpense = new BudgetExpenseModel();
		setExpensePrimaryCategory(newExpense, pCat);
		setExpenseSubCategory(newExpense, sCat);
		setExpenseAmount(newExpense, amt);
		setExpenseSpender(newExpense, spender);
		setExpenseDate(newExpense, date);
		expenseBook.add(newExpense);
		budgetView.addNewExpense(newExpense);
	}
	
	public static boolean saveExpenses(){
		StringBuilder sb = new StringBuilder();
		File f = new File("borkyexpenses.txt");
		for(BudgetExpenseModel m : expenseBook){
			sb.append(m.getPrimaryCategory() + ",");
			sb.append(m.getSubCategory() + ",");
			sb.append(m.getAmount() + ",");
			sb.append(m.getSpender() + ",");
			sb.append(m.getDate().toString() + ";");
		}
		try{
			FileWriter fw = new FileWriter(f, false);
			fw.write(sb.toString());
			fw.close();
			return true;
		} catch(IOException e){
			System.out.println(e);
			return false;
		}
	}
	
	public static boolean editExpense(){
		
		return false;
	}
	
	public static BudgetExpenseModel[] getSavedExpenses(){
		try{
			File f = new File("borkyexpenses.txt");
			boolean newFile = f.createNewFile();
			if(!newFile){
				InputStream is = new FileInputStream(f);
				BufferedReader in = new BufferedReader(new InputStreamReader(is));
				String savedExpenses = in.readLine();
				in.close();
				if(savedExpenses != null){
					String[] expenses = savedExpenses.split(";");
					ArrayList<BudgetExpenseModel> expensesList = new ArrayList<BudgetExpenseModel>();
					if(expenses.length > 0){
						for(String s : expenses){
							String[] details = s.split(",");
							BudgetExpenseModel e = new BudgetExpenseModel();
							e.setPrimaryCategory(details[0]);
							e.setSubCategory(details[1]);
							e.setAmount(Double.parseDouble(details[2]));
							e.setSpender(details[3]);
							e.setDate(LocalDate.parse(details[4]));
							expensesList.add(e);
						}
						BudgetExpenseModel[] b = expensesList.toArray(new BudgetExpenseModel[expensesList.size()]);
						return b;
					}
					else{
						BudgetExpenseModel[] b = {};
						return b;
					}
				}
			}
			else{
				BudgetExpenseModel[] b = {};
				return b;
			}
			
		} catch(IOException e){
			System.out.println("Error reading file: " + e);
			e.printStackTrace();
		}
		BudgetExpenseModel[] b = {};
		return b;
	}
}

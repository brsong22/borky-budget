package budgetapp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.ArrayList;

public class BudgetExpenseModel {
	
	private String primary;
	private String sub;
	private String amount = "0.00";
	private String spender;
	private LocalDate date = LocalDate.now();
	private static NumberFormat formatter = new DecimalFormat("#0.00");
	
//	BudgetExpenseModel(){};
//	BudgetExpenseModel(String p, String s, Double amt, String spender, LocalDate date){
//		
//		this.primary = p;
//		this.sub = s;
//		this.amount = formatter.format(amt);
//		this.spender = spender;
//		this.date = date;
//	}
	
	public void setPrimaryCategory(String cat){
		this.primary = cat;
	}
	
	public void setSubCategory(String cat){
		this.sub = cat;
	}
	
	public void setAmount(Double amt){
		this.amount = formatter.format(amt);
	}
	
	public void setSpender(String name){
		this.spender = name;
	}
	
	public void setDate(LocalDate d){
		this.date = d;
	}
	
	public String getPrimaryCategory(){
		return this.primary;
	}
	
	public String getSubCategory(){
		return this.sub;
	}
	
	public String getAmount(){
		return this.amount;
	}
	
	public String getSpender(){
		return this.spender;
	}
	
	public LocalDate getDate(){
		return this.date;
	}
	
	public String[] getData(){
		return new String[]{this.getPrimaryCategory(), this.getSubCategory(), this.getAmount().toString(), this.getSpender(), this.getDate().toString()};
	}
	
	public static String[] getHeaders(){
		return new String[]{"Primary Cat.", "Sub Cat.", "Amount", "Spender", "Date"};
	}
	
	public static BudgetExpenseModel[] getSavedExpenses(){
		try{
			InputStream is = BudgetExpenseModel.class.getResourceAsStream("borkyexpenses.txt");
			BufferedReader in = new BufferedReader(new InputStreamReader(is));
			String savedExpenses = in.readLine();
			in.close();
			String[] expenses = savedExpenses.split(";");
			ArrayList<BudgetExpenseModel> expensesList = new ArrayList<BudgetExpenseModel>();
			System.out.println(expenses.length);
			if(expenses.length > 1){
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
		} catch(IOException e){
			System.out.println("Error reading file: " + e);
			e.printStackTrace();
		}
		BudgetExpenseModel[] b = {};
		return b;
	}
}

package budgetapp;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDate;

public class BudgetExpenseModel {
	
	private String primary;
	private String sub;
	private String amount = "0.00";
	private String spender;
	private LocalDate date = LocalDate.now();
	private static NumberFormat formatter = new DecimalFormat("#0.00");
	
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
	
	public static String[] getHeaders(){
		return new String[]{"Primary Cat.", "Sub Cat.", "Amount", "Spender", "Date"};
	}
	
	public String[] getData(){
		return new String[]{this.getPrimaryCategory(), this.getSubCategory(), this.getAmount().toString(), this.getSpender(), this.getDate().toString()};
	}
}

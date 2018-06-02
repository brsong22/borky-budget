package budgetapp;

import java.time.LocalDate;

public class BudgetExpenseModel {
	
	private String primary;
	private String sub;
	private String amount = "0.00";
	private String spender;
	private LocalDate date = LocalDate.now();
	
	BudgetExpenseModel(BudgetExpenseCategory cats, String spender, LocalDate date){
		primary = cats.getPrimaryCategory();
		sub = cats.getSubCategory();
		this.spender = spender;
		this.date = date;
	}
	
	public void setPrimaryCategory(String cat){
		this.primary = cat;
	}
	
	public void setSubCategory(String cat){
		this.sub = cat;
	}
	
	public void setAmount(Double amt){
		this.amount = String.format("%.2f", amt);
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
}

package budgetapp;

import java.time.LocalDate;

public class BudgetExpenseModel {
	
	private String primary = "";
	private String sub = "";
	private String spender = "";
	private LocalDate date = LocalDate.now();
	
	BudgetExpenseModel(BudgetExpenseCategory cats, String spender, LocalDate date){
		primary = cats.getPrimaryCategory();
		sub = cats.getSubCategory();
		this.spender = spender;
		this.date = date;
	}
}

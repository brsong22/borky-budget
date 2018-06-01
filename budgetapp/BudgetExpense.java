package budgetapp;

import java.time.LocalDate;

public class BudgetExpense {
	
	private String primary = "";
	private String sub = "";
	private String spender = "";
	private LocalDate date = LocalDate.now();
	
	BudgetExpense(BudgetExpenseCategory cats, String spender, LocalDate date){
		primary = cats.getPrimaryCategory();
		sub = cats.getSubCategory();
		this.spender = spender;
		this.date = date;
	}
}

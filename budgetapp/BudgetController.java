package budgetapp;

import java.time.LocalDate;

public class BudgetController {
	
	private BudgetExpenseModel budgetModel;
	private BudgetView budgetView;
	
	public BudgetController(BudgetExpenseModel model, BudgetView view){
		this.budgetModel = model;
		this.budgetView = view;
	}
	
	public void setExpensePrimaryCategory(String cat){
		budgetModel.setPrimaryCategory(cat);
	}
	
	public void setExpenseSubCategory(String cat){
		budgetModel.setSubCategory(cat);
	}
	
	public void setExpenseAmount(Double amt){
		budgetModel.setAmount(amt);
	}
	
	public void setExpenseSpender(String name){
		budgetModel.setSpender(name);
	}
	
	public void setExpenseDate(LocalDate d){
		budgetModel.setDate(d);
	}
	
	public void viewAddExpense(BudgetExpenseModel expense){
		view.addExpense(expense);
	}
}

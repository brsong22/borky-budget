package budgetapp;

import java.time.LocalDate;

public class BudgetApp {

	private static BudgetExpenseModel[] getSavedExpenses(){
		LocalDate today = LocalDate.now();
		BudgetExpenseModel[] b = {new BudgetExpenseModel("Test", "test", 1.5, "richard", today), new BudgetExpenseModel("Test", "test", 5., "becky", today)};
		return b;
	}
	public static void main(String[] args) {
		BudgetExpenseModel[] savedExpenses = getSavedExpenses();
		BudgetView v = new BudgetView(savedExpenses);
		BudgetExpenseModel m = new BudgetExpenseModel();
		new BudgetController(m, v);
	}

}

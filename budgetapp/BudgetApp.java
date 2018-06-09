package budgetapp;

public class BudgetApp {


	public static void main(String[] args) {
		BudgetExpenseModel[] savedExpenses = BudgetExpenseModel.getSavedExpenses();
		BudgetView v = new BudgetView(savedExpenses);
		BudgetExpenseModel m = new BudgetExpenseModel();
		new BudgetController(m, v);
	}

}

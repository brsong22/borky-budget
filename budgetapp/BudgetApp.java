package budgetapp;

public class BudgetApp {


	public static void main(String[] args) {
		BudgetView v = new BudgetView();
		new BudgetController(v);
	}

}

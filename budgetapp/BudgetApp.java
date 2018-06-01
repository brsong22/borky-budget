package budgetapp;

import java.awt.EventQueue;

import javax.swing.JFrame;



public class BudgetApp {

	private JFrame frame;

	/**
	 * Create the application.
	 */
	public BudgetApp() {
		
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BudgetApp window = new BudgetApp();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}

package budgetapp;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.GridLayout;

public class BudgetViewItem extends JPanel {

	/**
	 * Create the panel.
	 */
	public BudgetViewItem(BudgetExpenseModel expense) {
		setBounds(0, 0, 585, 44);
		setLayout(new GridLayout(0, 5, 0, 0));
		
		JLabel label = new JLabel("New label");
		add(label);
		
		JLabel label_1 = new JLabel("New label");
		add(label_1);
		
		JLabel label_2 = new JLabel("New label");
		add(label_2);
		
		JLabel label_3 = new JLabel("New label");
		add(label_3);
		
		JLabel label_4 = new JLabel("New label");
		add(label_4);

	}
}

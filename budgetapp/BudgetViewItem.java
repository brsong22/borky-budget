package budgetapp;

import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

public class BudgetViewItem extends JPanel {

	/**
	 * Create the panel.
	 */
	public BudgetViewItem(BudgetExpenseModel expense, JPanel parent) {
		this.setLayout(new GridBagLayout());
		this.setPreferredSize(new Dimension((int)parent.getSize().getWidth(), 25));
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(2, 2, 2, 2);
		gbc.gridheight = 10;
		gbc.gridx = 0;
		gbc.gridy = 1;
		this.setSize(parent.getSize());
		
		JLabel pCat = new JLabel(expense.getPrimaryCategory());
		this.add(pCat, gbc);
		
		gbc.gridx = 1;
		JLabel sCat = new JLabel(expense.getSubCategory());
		this.add(sCat, gbc);
		
		gbc.gridx = 2;
		JLabel amt = new JLabel(expense.getAmount());
		this.add(amt, gbc);
		
		gbc.gridx = 3;
		JLabel spender = new JLabel(expense.getSpender());
		this.add(spender, gbc);
		
		gbc.gridx = 4;
		JLabel date = new JLabel(expense.getDate().toString());
		this.add(date, gbc);

	}
}

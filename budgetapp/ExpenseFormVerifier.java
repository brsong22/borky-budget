package budgetapp;

import java.awt.Color;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import javax.swing.BorderFactory;
import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class ExpenseFormVerifier extends InputVerifier{

	private String inputType;
	
	public ExpenseFormVerifier(String t){
		this.inputType = t;
	}
	@Override
	public boolean verify(JComponent input) {
		JTextField inputField = (JTextField) input;
		String inputText = inputField.getText();
		Border error = BorderFactory.createLineBorder(Color.RED, 1);
		Border ok = BorderFactory.createLineBorder(Color.GRAY, 1);
		boolean valid = false;
		switch(inputType){
			case "string":
				valid = true;
				inputField.setBorder(ok);
				break;
			case "int":
				try{
					Integer.parseInt(inputText);
					valid = true;
					inputField.setBorder(ok);
				} catch(NumberFormatException e){
					valid = false;
					inputField.setBorder(error);
				}
				break;
			case "double":
				try{
					Double.parseDouble(inputText);
					valid = true;
					inputField.setBorder(ok);
				} catch(NumberFormatException e){
					valid = false;
					inputField.setBorder(error);
				}
				break;
			case "date":
				try{
					LocalDate.parse(inputText);
					valid = true;
					inputField.setBorder(ok);
				} catch(DateTimeParseException e){
					valid = false;
					inputField.setBorder(error);
				}
				break;
			default:
				valid = false;
				inputField.setBorder(error);
				break;
		}
		return valid;
	}

}

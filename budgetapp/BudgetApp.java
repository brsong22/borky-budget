package budgetapp;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.Choice;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Calendar;

import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;

public class BudgetApp {

	private JFrame frame;
	private final String[] categories = {"Groceries", "Shopping", "Rent", "Utilities", "Dining", "Insurance", "Transportation", "Pets"};
	private JTextField textField;
	
	private Calendar cal = Calendar.getInstance();
	private final int month = cal.get(Calendar.MONTH)+1;
	private final int year = cal.get(Calendar.YEAR);
	
	private File filePath;
	private String filePathAndName;
	private final URL dir = BudgetApp.class.getProtectionDomain().getCodeSource().getLocation();

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

	/**
	 * Create the application.
	 */
	public BudgetApp() {
		initialize();
		loadExpenses();
	}

	private void loadExpenses() {
		
		String filename = "" + month + year + "_Budget.txt";
		try {
			filePath = Paths.get(dir.toURI()).toFile();
			filePathAndName = filePath + "/" + filename;
			System.out.println(filePathAndName);
		} catch (URISyntaxException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		boolean checkFile = new File(filePathAndName).exists();
		if(!checkFile){
			File budgetFile = new File(filePathAndName);
			try {
				budgetFile.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println(checkFile);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 475);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblGroceries = new JLabel("Groceries:");
		lblGroceries.setBounds(10, 11, 57, 21);
		frame.getContentPane().add(lblGroceries);
		
		JLabel lblShopping = new JLabel("Shopping:");
		lblShopping.setBounds(10, 32, 50, 21);
		frame.getContentPane().add(lblShopping);
		
		JLabel lblRent = new JLabel("Rent:");
		lblRent.setBounds(10, 53, 31, 21);
		frame.getContentPane().add(lblRent);
		
		JLabel lblUtilities = new JLabel("Utilities:");
		lblUtilities.setBounds(10, 74, 43, 21);
		frame.getContentPane().add(lblUtilities);
		
		JLabel lblDining = new JLabel("Dining:");
		lblDining.setBounds(10, 95, 43, 21);
		frame.getContentPane().add(lblDining);
		
		JLabel lblInsurance = new JLabel("Insurance:");
		lblInsurance.setBounds(10, 116, 57, 21);
		frame.getContentPane().add(lblInsurance);
		
		JLabel lblTransportation = new JLabel("Transportation:");
		lblTransportation.setBounds(188, 11, 76, 21);
		frame.getContentPane().add(lblTransportation);
		
		JLabel lblPets = new JLabel("Pets:");
		lblPets.setBounds(188, 32, 31, 21);
		frame.getContentPane().add(lblPets);
		
		JLabel lblGroceriesTotal = new JLabel("Groceries Total");
		lblGroceriesTotal.setBounds(72, 11, 95, 21);
		frame.getContentPane().add(lblGroceriesTotal);
		
		JLabel lblShoppingTotal = new JLabel("Shopping Total");
		lblShoppingTotal.setBounds(72, 32, 95, 21);
		frame.getContentPane().add(lblShoppingTotal);
		
		JLabel lblRentTotal = new JLabel("Rent Total");
		lblRentTotal.setBounds(72, 53, 95, 21);
		frame.getContentPane().add(lblRentTotal);
		
		JLabel lblUtilitiesTotal = new JLabel("Utilities Total");
		lblUtilitiesTotal.setBounds(72, 74, 95, 21);
		frame.getContentPane().add(lblUtilitiesTotal);
		
		JLabel lblDiningTotal = new JLabel("Dining Total");
		lblDiningTotal.setBounds(72, 95, 95, 21);
		frame.getContentPane().add(lblDiningTotal);
		
		JLabel lblInsuranceTotal = new JLabel("Insurance Total");
		lblInsuranceTotal.setBounds(72, 116, 95, 21);
		frame.getContentPane().add(lblInsuranceTotal);
		
		JLabel lblTransportationTotal = new JLabel("Trans. Total");
		lblTransportationTotal.setBounds(273, 11, 95, 21);
		frame.getContentPane().add(lblTransportationTotal);
		
		JLabel lblPetsTotal = new JLabel("Pets Total");
		lblPetsTotal.setBounds(273, 32, 95, 21);
		frame.getContentPane().add(lblPetsTotal);
		
		JComboBox comboBox = new JComboBox(categories);
		comboBox.setBounds(13, 242, 141, 21);
		frame.getContentPane().add(comboBox);
		
		JTextField textField = new JTextField();
		textField.setBounds(209, 242, 86, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Amount:");
		lblNewLabel.setBounds(164, 242, 50, 21);
		frame.getContentPane().add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Add Expense");
		btnNewButton.setBounds(305, 241, 119, 23);
		frame.getContentPane().add(btnNewButton);
		
		JLabel lblNewLabel_1 = new JLabel("Month Total:");
		lblNewLabel_1.setBounds(206, 179, 86, 21);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Month total amt");
		lblNewLabel_2.setBounds(290, 179, 136, 21);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Net Total:");
		lblNewLabel_3.setBounds(206, 390, 58, 21);
		frame.getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Net Total amt");
		lblNewLabel_4.setBounds(288, 391, 136, 21);
		frame.getContentPane().add(lblNewLabel_4);
		
		frame.setVisible(true);
	}
}

package budgetapp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;


public class BudgetExpenseCategory {

	private Hashtable<String, ArrayList<String>> categories = new Hashtable<String, ArrayList<String>>();
	private String primary = "";
	private String sub = "";
	
	BudgetExpenseCategory(){
		populateCategories();
	}
	BudgetExpenseCategory(String primaryCategory, String subCategory){
		populateCategories();
		setPrimaryCategory(primaryCategory);
		setSubCategory(subCategory);
	}
	private void populateCategories(){
		String[] aHousing = new String[]{"Rent","Phone","Electricity","Gas","Water/Sewage","Cable/Internet","Supplies","Parking"};
		categories.put("Housing", new ArrayList<String>(Arrays.asList(aHousing)));
		
		String[] aTransport = new String[]{"Vehicle Payment","Insurance","Licensing","Fuel","Maintenance Fund","Misc"};
		categories.put("Transportation", new ArrayList<String>(Arrays.asList(aTransport)));
		
		String[] aInsurance = new String[]{"Renters","Health","Life","Other"};
		categories.put("Insurance", new ArrayList<String>(Arrays.asList(aInsurance)));
		
		String[] aPersonal = new String[]{"Medical","Hair","Clothing","Supplies"};
		categories.put("Personal Care", new ArrayList<String>(Arrays.asList(aPersonal)));
		
		String[] aEntertain = new String[]{"Video/DVD","Music","Movies","Concerts","Sporting Events","Games","Travel"};
		categories.put("Entertainment", new ArrayList<String>(Arrays.asList(aEntertain)));
		
		String[] aPets = new String[]{"Food","Medical","Grooming","Toys","Supplies"};
		categories.put("Pets", new ArrayList<String>(Arrays.asList(aPets)));
		
		String[] aFood = new String[]{"Groceries","Dining Out","Other/Bars","Coffee"};
		categories.put("Food", new ArrayList<String>(Arrays.asList(aFood)));
	}
	public Hashtable<String, ArrayList<String>> getAllCategories(){
		return categories;
	}
	
	public String getPrimaryCategory(){
		return primary;
	}
	public void setPrimaryCategory(String primary){
		this.primary = primary;
	}
	
	public String getSubCategory(){
		return sub;
	}
	public void setSubCategory(String sub){
		this.sub = sub;
	}
	
	public void addPrimaryCategory(String cat){
		categories.put(cat, new ArrayList<String>());
	}
	public void addSubCategory(String cat, String sub){
		categories.get(cat).add(sub);
	}
	public void addSubCategories(String cat, String[] subs){
		categories.get(cat).addAll((ArrayList<String>) Arrays.asList(subs));
	}
}

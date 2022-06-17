/* (C): 1059633, 1057764, 236100 */

public class Notebook extends Item {
	private int sections;
	
	public Notebook(String name, double price, String description, int stock, int id, int sections) {
		super(name, price, description, stock, id);
		this.sections = sections;
	}
	
	public String getDetails() {
		return "\nDetails:\nNumber of sections: "+sections;
	}
}

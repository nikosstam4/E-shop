/* (C): 1059633, 1057764, 236100 */

public class Paper extends Item {
	private int weight;
	private int pages;
	
	public Paper(String name, double price, String description, int stock, int id, int weight, int pages) {
		super(name, price, description, stock, id);
		this.weight = weight;
		this.pages = pages;
	}
	
	public String getDetails() {
		return "\nDetails:\nWeight: "+weight+"\nPages: "+pages;
	}
}

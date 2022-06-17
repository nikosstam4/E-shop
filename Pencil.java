/* (C): 1059633, 1057764, 236100 */

public class Pencil extends Item {
	private double tipSize;
	private String type;
	
	public Pencil(String name, double price, String description, int stock, int id, double tipSize, String type) {
		super(name, price, description, stock, id);
		this.tipSize = tipSize;
		this.type = type;
	}
	
	public String getDetails() {
		return "\nDetails:\nTip Size: "+tipSize+"\nType: "+type;
	}
}

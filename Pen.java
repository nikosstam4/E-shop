/* (C): 1059633, 1057764, 236100 */

public class Pen extends Item {
	private String color;
	private double tipSize;
	
	public Pen(String name, double price, String description, int stock, int id, String color, double tipSize) {
		super(name, price, description, stock, id);
		this.color = color;
		this.tipSize = tipSize;
	}
	
	public String getDetails() {
		return "\nDetails:\nColor: "+color+"\nTip Size: "+tipSize;
	}
}

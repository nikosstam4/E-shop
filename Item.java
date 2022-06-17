/* (C): 1059633, 1057764, 236100 */

abstract class Item {
	private String name;
	private double price;
	private String description;
	private int stock;
	private int id;
	
	public Item(String name, double price, String description, int stock, int id) {
		this.name = name;
		this.price = price;
		this.description = description;
		this.stock = stock;
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public int getId() {
		return id;
	}
	
	public int getStock() {
		return stock;
	}
	
	public double getPrice() {
		return price;
	}
	
	public void setStock(int stock) {
		this.stock = stock;
	}
	
	public String getBasicInfo() {
		return "Basic Info:\nName: "+name+"\nPrice: "+price+"\nDescription: "+description+"\nStock: "+stock+"\nId: "+id;
	}
	
	public abstract String getDetails();
	
	public String toString() {
		return getBasicInfo() + getDetails();
	}
}

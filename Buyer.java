/* (C): 1059633, 1057764, 236100 */

public class Buyer extends User {
	private int bonus = 0;
	private String buyerCategory = "BRONZE";
	private ShoppingCart mycart;
	
	public Buyer(String buyer_name, String buyer_email, int bonus, String buyerCategory, ShoppingCart mycart) {
		super(buyer_name, buyer_email);
		this.bonus = bonus;
		this.buyerCategory = buyerCategory;
		this.mycart = mycart;
	}
	
	public int getBonus() {
		return bonus;
	}
	
	public String getBuyerCategory() {
		return buyerCategory;
	}
	
	public ShoppingCart getCart() {
		return mycart;
	}
	
	public void awardBonus() {
		double ordercost = mycart.calculateNet();
		int points = (int)(ordercost * 0.1);  //typecasting και υπολογισμός των πόντων του χρήστη βάσει
		bonus = bonus + points;               //του συνολικού κόστους του καλαθιού
		setbuyerCategory();                   //και ανανέωση της κατηγορίας του αν φτάσει τον επιθυμητό
	}                                         //αριθμό πόντων
	
	public void setbuyerCategory() {
		if (bonus > 100) {
			buyerCategory = "SILVER";
		}
		else if (bonus > 200) {
			buyerCategory = "GOLD";
		}
	}
	
	public void placeOrder(Item i, int quantity) throws StockException {
		mycart.addItemOrdered(i, quantity);
	}
	
}

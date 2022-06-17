/* (C): 1059633, 1057764, 236100 */

public class Main {
	public static void main(String[] args) {
		
		//Δημιουργία ενός owner, του eshop του, και του μενού του eshop
		Owner owner = new Owner("Kwnstantinos Raptopoulos", "rap123@gmail.com", true);
		EShop MyShop = new EShop("JUMBO", owner);
		Menu MyMenu = new Menu(owner, MyShop);
		
		//Δημιουργία αντικειμένων της κλάσης Pen
		Pen pen1 = new Pen("BIC Blue Pen", 0.6, "classic pen style", 125, 23048, "Blue", 1.2);
		Pen pen2 = new Pen("BIC Red Pen", 0.6, "new release pen", 85, 23050, "Red", 1.2);
		Pen pen3 = new Pen("BIC Green Pen", 0.6, "new release pen", 72, 23051, "Green", 1.2);
		
		//Δημιουργία αντικειμένων της κλάσης Pencil
		Pencil pencil1 = new Pencil("Faber-Castell Document", 0.8, "write and draw", 200, 24023, 2.1, "B");
		Pencil pencil2 = new Pencil("Faber-Castell Grip", 0.8, "write and draw", 12, 24024, 2.1, "H");
		Pencil pencil3 = new Pencil("Faber-Castell Sparkle", 0.8, "write and draw", 34, 24025, 2.1, "HB");
		
		//Δημιουργία αντικειμένων της κλάσης Notebook
		Notebook note1 = new Notebook("Scag Two-Section", 1.5, "Notebook with two sections", 45, 25054, 2);
		Notebook note2 = new Notebook("Scag Spiral Three-Section", 2.3, "Spiral notebook with three sections", 21, 25055, 3);
		Notebook note3 = new Notebook("Scag Spiral Five-Section", 3.5, "Spiral notebook with five sections", 90, 25056, 5);
		
		//Δημιουργία αντικειμένων της κλάσης Paper
		Paper paper1 = new Paper("Paper #1", 1.7, "The best paper", 60, 27080, 110, 70);
		Paper paper2 = new Paper("Paper #2", 2.6, "The best paper", 35, 27081, 280, 120);
		Paper paper3 = new Paper("Paper #3", 3.2, "The best paper", 12, 27082, 350, 180);
		
		//Προσθήκη όλων των αντικειμένων στο eshop
		try {
			MyShop.addItem(pen1);
			MyShop.addItem(pen2);
			MyShop.addItem(pen3);
			MyShop.addItem(pencil1);
			MyShop.addItem(pencil2);
			MyShop.addItem(pencil3);
			MyShop.addItem(note1);
			MyShop.addItem(note2);
			MyShop.addItem(note3);
			MyShop.addItem(paper1);
			MyShop.addItem(paper2);
			MyShop.addItem(paper3);
		} catch (SameItemException sie) {}
		
		//Δημιουργία αντικειμένων ShoppingCart
		ShoppingCart tsilicart = new ShoppingCart();
		ShoppingCart xioscart = new ShoppingCart();
		
		//Δημιουργία αντικειμένων Buyer
		Buyer buyer1 = new Buyer("Ioannis Tsiligkiris", "tsili@gmail.com", 90, "BRONZE", tsilicart);
		Buyer buyer2 = new Buyer("Stefanos Xios", "makeleio@gmail.com", 170, "SILVER", xioscart);
		
		//Προσθήκη των buyers στο buyerList του eshop
		try {
			MyShop.addBuyer(buyer1);
			MyShop.addBuyer(buyer2);
		} catch (SameBuyerException sbe) {}
		
		//Παραγγελία για τον buyer1
		try {
			buyer1.placeOrder(pen3, 10);
			buyer1.placeOrder(paper1, 8);
			buyer1.placeOrder(pencil1, 34);
			buyer1.placeOrder(note3, 7);
			buyer1.placeOrder(paper2, 9);
		} catch (StockException se) {}
		
		//Παραγγελία για τον buyer2
		try {
			buyer2.placeOrder(paper3, 5);
			buyer2.placeOrder(pen2, 20);
			buyer2.placeOrder(pen3, 40);
			buyer2.placeOrder(note1, 8);
			buyer2.placeOrder(note3, 19);
			buyer2.placeOrder(pencil3, 25);
			buyer2.placeOrder(paper1, 6);
		} catch (StockException se) {}
	
		//Εκκίνηση του Menu
		MyMenu.UserLogin();
	}
}

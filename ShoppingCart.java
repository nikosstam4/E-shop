/* (C): 1059633, 1057764, 236100 */

import java.util.*;
public class ShoppingCart {
	private Map<Item, Integer> orderList = new HashMap<Item, Integer>();
	
	public Map<Item, Integer> getOrderList() {   //Χρήση Map για την αποθήκευση των παραγγελιών
		return orderList;
	}
	
	public void addItemOrdered(Item item, int quantity) throws StockException {
		boolean flag = false;
		int stock;
		stock = item.getStock();
		if (stock >= quantity) {              //Αν η ζητούμενη ποσότητα δεν υπερβαίνει το stock, καταχωρείται η παραγγελία
			item.setStock(stock - quantity);  //και ενημερώνεται το stock
			for (Map.Entry<Item, Integer> entry : orderList.entrySet()) {
				if (entry.getKey() == item) {    //Αν το ζητούμενο προϊόν συγκαταλέγεται ήδη στη λίστα των παραγγελιών,
					int new_quantity = entry.getValue();  //τότε απλώς ενημερώνεται η ποσότητά του
					new_quantity += quantity;
					entry.setValue(new_quantity);
					flag = true;
				}
			}
			if (flag == false) {
				orderList.put(item, quantity);
			}
		}                                                                                        //Αν η ζητούμενη ποσότητα υπερβαίνει το stock,
		else throw new StockException("Product with ID " + item.getId() + " is out of stock!");  //εγείρει εξαίρεση
	}                                          
	
	public void removeItemOrdered(Item item) {            //Διαγράφει μια παραγγελία και ενημερώνει το stock
		int old_stock = item.getStock();
		int new_stock = old_stock + orderList.get(item);
		item.setStock(new_stock);
		orderList.remove(item);
	}
	
	public void changeItemOrderedQuantity(Item i, int newQuantity, EShop shop) throws StockException {
		if (orderList.containsKey(i)) {                                      //Για μια παραγγελία, ενημερώνει την ποσότητα
			for (Map.Entry<Item, Integer> entry : orderList.entrySet()) {    //και αν η νέα ποσότητα δεν υπερβαίνει το stock
				if (entry.getKey() == i) {                                   //καταχωρείται και ενημερώνεται το stock
					if (entry.getKey().getStock() >= newQuantity) {
						entry.setValue(newQuantity);
						int oldStock = entry.getKey().getStock();
						int newStock = oldStock - newQuantity;
						shop.updateItemStock(entry.getKey(), newStock);
					}                                                                                                 //Αν η ποσότητα υπερβαίνει το stock,
					else throw new StockException("Product with ID " + entry.getKey().getId() + " is out of stock!"); //εγείρει εξαίρεση
				}
			}
		}
	}
	
	
	public void showCart(Buyer b) {
		if (orderList.isEmpty() == true) {
			System.out.println("The Cart is empty! :(");
		}
		System.out.println("MY SHOPPING CART:");
		System.out.println("====================================================================================");
		for (Map.Entry<Item, Integer> entry : orderList.entrySet()) {
			System.out.println(entry.getKey().getName()+"  "+entry.getKey().getPrice()+"  x"+entry.getValue());
		}
		System.out.println("====================================================================================");
		System.out.println("Total_Price: " + calculateNet());
		System.out.println("Courier_Cost: " + calculateCourierCost(b));
	}
	
	public void clearCart() {
		for (Map.Entry<Item, Integer> entry : orderList.entrySet()) {     //Μέσα στη for ενημερώνεται το stock κάθε προϊόντος
			int old_stock = entry.getKey().getStock();                    //για το οποίο υπήρχε μια παραγγελία στο καλάθι αγορών
			int new_stock = old_stock + orderList.get(entry.getKey());
			entry.getKey().setStock(new_stock);
		}
		orderList.clear();                                                //και τελικά αδειάζει η λίστα
		System.out.println("\nCart emptied!");
	}
	
	public void checkout(Buyer b) {
		showCart(b);
		Scanner confirmation = new Scanner(System.in);
		System.out.println("Do you want to proceed with your purchase? [y/n]");  //Ο χρήστης ερωτάται αν επιθυμεί να προχωρήσει
		String str = confirmation.nextLine();                                    //στην εξόφληση της παραγγελία του
		if (str.equals("y")) {                                                   //και εφόσον απαντήσει θετικά, αδειάζει το καλάθι του
			b.awardBonus();                                                      //και γίνεται υπολογισμός των πόντων του
			orderList.clear();
			System.out.println("Purchase completed successfully!");
		}
	}
	
	
	public double calculateNet() {      //Υπολογίζει το συνολικό κόστος του καλαθιού
		double cost = 0.0;
		for (Map.Entry<Item, Integer> entry : orderList.entrySet()) {
			cost += entry.getKey().getPrice() * entry.getValue();
		}
		return cost;
	}
	
	public double calculateCourierCost(Buyer b) {      //Ανάλογα με την κατηγορία κάθε χρήστη,
		double couriercost = 0.0;                      //καθορίζονται και τα μεταφορικά έξοδα
		double ordercost = calculateNet();
		couriercost = 0.02 * ordercost;
		if (b.getBuyerCategory() == "GOLD") {
			couriercost = 0.0;
		}
		else if (b.getBuyerCategory() == "SILVER") {
			double discount = 0.5 * couriercost;
			couriercost = couriercost - discount;
		}
		else if (b.getBuyerCategory() == "BRONZE") {
			if (couriercost < 3.0) {
				couriercost = 3.0;
			}
		}
		return couriercost;
	}
}

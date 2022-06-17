/* (C): 1059633, 1057764, 236100 */

import java.util.*;
public class Menu {
	
	private Owner owner;
	private EShop MyShop;
	private ShoppingCart mycart;
	private Buyer buyer;
	private Stack<Integer> stack = new Stack<Integer>();
	
	public Menu(Owner owner, EShop MyShop) {
		this.owner = owner;
		this.MyShop = MyShop;
	}
	
	//Μέθοδος για την είσοδο ενός χρήστη
	public void UserLogin() {
		Scanner login = new Scanner(System.in);
		printStart();
		System.out.println("\nGive your email address:");
		String str1 = login.nextLine();
		for (int i = 0; i < MyShop.getBuyersList().size(); i++) {                 
			if (str1.equals(MyShop.getBuyersList().get(i).getuEmail())) {        //αν το email αντιστοιχεί σε αυτό ενός buyer από την buyersList,
				buyer = MyShop.getBuyersList().get(i);                           //αρχικοποιούνται οι μεταβλητές buyer και mycart και το πρόγραμμα
				mycart = buyer.getCart();                                        //καλωσορίζει τον Buyer εμφανίζοντας τα στοιχεία του και το ανάλογο Menu
				System.out.println("\nHello dear Buyer!");                       
				System.out.print("\nName: " + MyShop.getBuyersList().get(i).getuName() + "\nEmail: " + str1);
				System.out.println("\nBonus: " + MyShop.getBuyersList().get(i).getBonus() + "\nCategory: " + MyShop.getBuyersList().get(i).getBuyerCategory());
				BuyerMenu();
				break;
			}
		}
		
		if (str1.equals("rap123@gmail.com")) {             //αν το email αντιστοιχεί σε αυτό του owner του eshop,
			System.out.println("\nHello Mr. Owner!");      //το πρόγραμμα τον καλωσορίζει εμαφανίζοντας τα στοιχεία του και το ανάλογο Menu
			System.out.println("\nName: " + owner.getuName() + "\nEmail: " + owner.getuEmail() + "\nIs owner: Yes");
			OwnerMenu();
		}
		else {                                                                                         //αν το email δεν βρίσκει αντιστοιχία,
			Scanner input = new Scanner(System.in);                                                    //πρόκειται για νέο Buyer και τον ρωτάει
			System.out.println("\nNo match to this email address!\nDo you want to sign up? [y/n]");    //αν θέλει να εγγραφεί και έπειτα ζητά τα στοιχεία του
			String buyerSignup = input.nextLine();                                                     //και τον προσθέτει στην buyersList του καταστήματος
			if (buyerSignup.equals("y")) {
				Scanner newName = new Scanner(System.in);
				Scanner newEmail = new Scanner(System.in);
				System.out.println("Enter your name: ");
				String str2 = newName.nextLine();
				System.out.println("\nEnter your email address: ");
				String str3 = newEmail.nextLine();
				ShoppingCart newCart = new ShoppingCart();
				Buyer newBuyer = new Buyer(str2, str3, 0, "BRONZE", newCart);
				try {
					MyShop.addBuyer(newBuyer);
				} catch (SameBuyerException sb) {UserLogin();}
				UserLogin();
			}
			else {
				UserLogin();
			}
		}
	}
	
	public void BuyerMenu() {                       //Το Menu του Buyer
		Scanner input = new Scanner(System.in);
		int buyerchoice;
		do {
			System.out.println("\nBUYER MENU");
			System.out.println("============================================================");
			System.out.println("1. Browse Store");
			System.out.println("2. View Cart");
			System.out.println("3. Checkout");
			System.out.println("4. Back");
			System.out.println("5. Logout");
			System.out.println("6. Exit");
			System.out.println("============================================================");
			System.out.print("\nEnter your choice: ");
			
			buyerchoice = input.nextInt();
			
			switch (buyerchoice) {
			
			case 1:
				try {
					BuyerBrowseStore();
				} catch (NegativeException ne) {}
				break;
				
			case 2:
				try {
					BuyerViewCart();
				} catch (NegativeException ne) {}
				break;
				
			case 3:
				BuyerCheckout();
				break;
				
			case 4:
				try {
					try {
						if (stack.peek().equals(1)) {
							BuyerBrowseStore();
						}
						else if (stack.peek().equals(2)) {
							BuyerViewCart();
						}
						else if (stack.peek().equals(3)) {
							BuyerCheckout();
						}
						else if (stack.peek().equals(4)) {
							BuyerLogout();
						}
					} catch (NegativeException ne) {}
				} catch (EmptyStackException ese) {break;}
				break;
				
			case 5:
				BuyerLogout();
				break;
		    
			case 6:
				System.out.println("Exiting Program...");
				printEnd();
				System.exit(0);
				break;
			
			default:
				System.out.println(buyerchoice + " is not a valid Menu Option! Please select another!");
			}
		} while (buyerchoice != 6);
		
	}
	
	public void BuyerBrowseStore() throws NegativeException {      //Πλοήγηση του Buyer στο κατάστημα
		stack.push(1);
		System.out.println("\n" + MyShop.geteName());
		MyShop.showCategories();
		MyShop.showProductsInCategory();
		Scanner addcart = new Scanner(System.in);
		System.out.println("\nDo you want to add a product to your cart? [y/n]");
		String add = addcart.nextLine();
		if (add.equals("y")) {
			Scanner quantity = new Scanner(System.in);
			try {
				Item i = MyShop.getItemById();
				System.out.println("Enter the quantity you want: ");
				int quan = quantity.nextInt();
				if (quan < 0) {                                                         //Αν η ποσότητα που δίνεται από τον χρήστη είναι αρνητική,
					throw new NegativeException("You cannot give negative quantity!");  //τότε εγείρει εξαίρεση
				}
				try {
				buyer.placeOrder(i, quan);
				System.out.println("Product with ID " + i.getId() + " added to your cart");
				} catch (StockException se) {   //Χειρισμός εξαίρεσης σε περίπτωση που το quantity
					BuyerBrowseStore();         //υπερβαίνει το stock του προϊόντος 
				}
			} catch (ItemNotFoundException inf) {  //Χειρισμός εξαίρεσης σε περίπτωση που ο κωδικός προϊόντος
				BuyerBrowseStore();                //που εισάγεται δεν αντιστοιχεί σε κάποιο προϊόν
			}
		}
		else {
			BuyerMenu();
		}
	}
	
	public void BuyerViewCart() throws NegativeException {      //Προβολή καλαθιού αγορών του Buyer
		stack.push(2);
		mycart.showCart(buyer);
		Scanner input = new Scanner(System.in);
		System.out.println("\nWhat would you like to do?");
		System.out.println("1. Remove an order");
		System.out.println("2. Change the quantity of a product");
		System.out.println("3. Clear the cart");
		System.out.println("4. Checkout");
		System.out.println("5. Return to the Main Menu");
		int choice = input.nextInt();
		if (choice == 1) {
			Scanner remove = new Scanner(System.in);
			boolean found = false;
			System.out.println("Enter the name of the product you want to remove: ");
			String itemdel = remove.nextLine();
			for (Map.Entry<Item, Integer> entry : mycart.getOrderList().entrySet()) {
				if (itemdel.equals(entry.getKey().getName())) {
					found = true;
					mycart.removeItemOrdered(entry.getKey());
					System.out.println("Order removed successfully!");
					break;
				}
			}
			if (found == false) {
				System.out.println("This product is not in the cart!");
			}
			
		}
		else if (choice == 2) {
			Scanner name = new Scanner(System.in);
			boolean found1 = false;
			System.out.println("Enter the name of the product you want to change its quantity: ");
			String itemname = name.nextLine();
			for (Map.Entry<Item, Integer> entry : mycart.getOrderList().entrySet()) {
				if (itemname.equals(entry.getKey().getName())) {
					found1 = true;
					Scanner change = new Scanner(System.in);
					System.out.println("Enter the quantity you want: ");
					try {
						int newquantity = change.nextInt();
						if (newquantity < 0) {                                                  //Αν η ποσότητα που δίνεται από τον χρήστη είναι αρνητική,
							throw new NegativeException("You cannot give negative quantity!");  //τότε εγείρει εξαίρεση
						}
						mycart.changeItemOrderedQuantity(entry.getKey(), newquantity, MyShop);
						System.out.println("The quantity of product with ID " + entry.getKey().getId() + " changed successfully!");
					} catch (StockException se) {}
					break;
				}
			}
			if (found1 == false) {
				System.out.println("There is no such product!");
			}
		}
		else if (choice == 3) {
			mycart.clearCart();
		}
		else if (choice == 4) {
			BuyerCheckout();
		}
		else if (choice == 5) {
			BuyerMenu();
		}
	}
	
	public void BuyerCheckout() {    //Εξόφληση παραγγελίας
		stack.push(3);
		mycart.checkout(buyer);
	}
	
	public void BuyerLogout() {     //Αποσύνδεση του Buyer από το πρόγραμμα
		stack.push(4);
		Scanner input = new Scanner(System.in);
		System.out.println("Are you sure you want to logout? [y/n]");
		String logout = input.nextLine();
		if (logout.equals("y")) {
			stack.clear();
			UserLogin();
		}
		else {
			BuyerMenu();
		}
	}
	
	
	public void OwnerMenu() {                      //Το Menu του Owner
		Scanner input = new Scanner(System.in);
		int ownerchoice;
		do {
			System.out.println("\nOWNER MENU");
			System.out.println("============================================================");
			System.out.println("1. Browse Store");
			System.out.println("2. Check Status");
			System.out.println("3. Back");
			System.out.println("4. Logout");
			System.out.println("5. Exit");
			System.out.println("============================================================");
			System.out.print("\nEnter your choice: ");
			
			ownerchoice = input.nextInt();
			
			switch (ownerchoice) {
			
			case 1:
				try {
					OwnerBrowseStore();
				} catch (NegativeException ne) {}
				break;
			
			case 2:
				OwnerCheckStatus();
				break;
			
			case 3:
				try {
					try {
						if (stack.peek().equals(1)) {
							OwnerBrowseStore();
						}
						else if (stack.peek().equals(2)) {
							OwnerCheckStatus();
						}
						else if (stack.peek().equals(3)) {
							OwnerLogout();
						}
					} catch (NegativeException ne) {}
				} catch (EmptyStackException ese) {break;}
				break;
				
			case 4:
				OwnerLogout();
				break;
			
			case 5:
				System.out.println("Exiting Program...");
				printEnd();
				System.exit(0);
				break;
			
			default:
				System.out.println(ownerchoice + " is not a valid Menu Option! Please select another!");
			}
		} while (ownerchoice != 5);
		
	}
	
	public void OwnerBrowseStore() throws NegativeException {      //Πλοήγηση του Owner στο κατάστημα 
		stack.push(1);
		System.out.println("\n" + MyShop.geteName());
		MyShop.showCategories();
		MyShop.showProductsInCategory();
		Scanner change = new Scanner(System.in);
		System.out.println("\nDo you want to change the stock of a product? [y/n]");
		String changestock = change.nextLine();
		if (changestock.equals("y")) {
			Scanner stock = new Scanner(System.in);
			try {
				Item i = MyShop.getItemById();
				System.out.println("Enter its stock: ");
				int changedstock = stock.nextInt();
				if (changedstock < 0) {                                             //Αν δοθεί αρνητική ποσότητα stock,
					throw new NegativeException("You cannot give negative stock!"); //εγείρει εξαίρεση 
				}
				MyShop.updateItemStock(i, changedstock);
				System.out.println("The stock of product with ID " + i.getId() + " updated successfully!");
			} catch (ItemNotFoundException inf) {OwnerBrowseStore();}  //Χειρισμός εξαίρεσης αν δεν βρεθεί το προϊόν
		}
		else {
			OwnerMenu();
		}
	}
	
	public void OwnerCheckStatus() {     //Προβολή και επεξεργασία των Buyers του καταστήματος από τον Owner
		stack.push(2);
		MyShop.checkStatus();
		Scanner name = new Scanner(System.in);
		boolean found2 = false;
		System.out.println("Enter the name of the buyer you want to see his cart: ");
		String buyername = name.nextLine();
		for (int j = 0; j < MyShop.getBuyersList().size(); j++) {
			if (buyername.equals(MyShop.getBuyersList().get(j).getuName())) {
				found2 = true;
				buyer = MyShop.getBuyersList().get(j);
				mycart = buyer.getCart();
				mycart.showCart(buyer);
				Scanner remove = new Scanner(System.in);
				System.out.println("Do you want to remove this buyer? [y/n]");
				String removebuyer = remove.nextLine();
				if (removebuyer.equals("y")) {
					mycart.clearCart();
					MyShop.removeBuyer(buyer);
					System.out.println("Buyer removed successfully!");
					OwnerMenu();
				}
				else {
					OwnerMenu();
				}
			}
		}
		if (found2 == false) {
			System.out.println("There is no such buyer!");
		}
	}
	
	public void OwnerLogout() {               //Αποσύνδεση του Owner από το πρόγραμμα
		stack.push(3);
		Scanner input = new Scanner(System.in);
		System.out.println("Are you sure you want to logout? [y/n]");
		String logout = input.nextLine();
		if (logout.equals("y")) {
			stack.clear();
			UserLogin();
		}
		else {
			OwnerMenu();
		}
	}
	
	public static void printStart() {   //Γραφικά αρχής
		System.out.println(",--.   ,--.,------.,--.    ,-----. ,-----. ,--.   ,--.,------.    ,--------. ,-----.      ,-----. ,--. ,--.,------.     ");   
		System.out.println("|  |   |  ||  .---'|  |   '  .--./'  .-.  '|   `.'   ||  .---'    '--.  .--''  .-.  '    '  .-.  '|  | |  ||  .--. '    ");  
		System.out.println("|  |.'.|  ||  `--, |  |   |  |    |  | |  ||  |'.'|  ||  `--,        |  |   |  | |  |    |  | |  ||  | |  ||  '--'.'    ");  
		System.out.println("|   ,'.   ||  `---.|  '--.'  '--'\\'  '-'  '|  |   |  ||  `---.       |  |   '  '-'  '    '  '-'  ''  '-'  '|  |\\  \\  ");  
		System.out.println("'--'   '--'`------'`-----' `-----' `-----' `--'   `--'`------'       `--'    `-----'      `-----'  `-----' `--' '--'    ");  
		System.out.println("                                    ,------.        ,---.  ,--.  ,--. ,-----. ,------.                                  "); 
		System.out.println("                                    |  .---',-----.'   .-' |  '--'  |'  .-.  '|  .--. '                                 ");
		System.out.println("                                    |  `--, '-----'`.  `-. |  .--.  ||  | |  ||  '--' |                                 ");
		System.out.println("                                    |  `---.       .-'    ||  |  |  |'  '-'  '|  | --'                                  ");
		System.out.println("                                    `------'       `-----' `--'  `--' `-----' `--'                                      ");
		System.out.println("========================================================================================================================");
	}
	
	public static void printEnd() {   //Γραφικά τέλους
		System.out.println("=====================================================");
		System.out.println("                                   .''.              ");
	    System.out.println("       .''.      .        *''*    :_\\/_:     .      ");
	    System.out.println("      :_\\/_:   _\\(/_  .:.*_\\/_*   : /\\ :  .'.:.'.");
	    System.out.println("  .''.: /\\ :   ./)\\   ':'* /\\ * :  '..'.  -=:o:=- ");
	    System.out.println(" :_\\/_:'.:::.    ' *''*    * '.\\'/.' _\\(/_'.':'.' ");
	    System.out.println(" : /\\ : :::::     *_\\/_*     -= o =-  /)\\    '  * ");
	    System.out.println("  '..'  ':::'     * /\\ *     .'/.\\'.   '           ");
	    System.out.println("      *            *..*         :                    ");
	    System.out.println("       *                                             ");
	    System.out.println("       *                                             ");
	}
}
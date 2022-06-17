/* (C): 1059633, 1057764, 236100 */

import java.util.*;
public class EShop {
	private String name;
	private Owner owner;
	private ArrayList<Item> itemsList = new ArrayList<Item>();
	private ArrayList<Buyer> buyersList = new ArrayList<Buyer>();
	
	public EShop(String name, Owner owner) {
		this.name = name;
		this.owner = owner;
	}
	
	public String geteName() {
		return name;
	}
	
	public ArrayList<Item> getItemsList() {
		return itemsList;
	}
	
	public ArrayList<Buyer> getBuyersList() {
		return buyersList;
	}
	
	public void addItem(Item i) throws SameItemException {
		for (int j = 0; j < itemsList.size(); j++) {
			if (itemsList.get(j).equals(i)) {
				throw new SameItemException("Cannot add the same product!");
			}
		}
		itemsList.add(i);
	}
	
	public Item getItemById() throws ItemNotFoundException {
		Scanner getId = new Scanner(System.in);
		System.out.println("Enter the id of the product you want: ");
		int id = getId.nextInt();
		for (int i = 0; i < itemsList.size(); i++) {     
			if (itemsList.get(i).getId() == id) {
				return itemsList.get(i);
			}
		}
		
		throw new ItemNotFoundException("Product with ID " + id + " doesn't exist!");  ////αν δεν βρει το Item εγείρει εξαίρεση
	}
	
	public void removeItem(Item i) {
		itemsList.remove(i);
	}
	
	public void addBuyer(Buyer b) throws SameBuyerException{
		for (int i = 0; i < buyersList.size(); i++) {
			if (buyersList.get(i).equals(b)) {
				throw new SameBuyerException("Cannot add the same buyer!");
			}
		}
		buyersList.add(b);
	}
	
	public void removeBuyer(Buyer b) {
		buyersList.remove(b);
	}
	
	public void updateItemStock(Item i, int stock) {
		i.setStock(stock);
	}
	
	public void showCategories() {
		System.out.println("Product Categories:");
		System.out.println("1. Pen (3 Items)\n2. Pencil (3 Items)\n3. Notebook (3 Items)\n4. Paper (3 Items)");
	}
	
	public void showProductsInCategory() {
		Scanner input = new Scanner(System.in);
		int cat = input.nextInt();
		if (cat == 1) {
			for (int i = 0; i < 3; i++) {
				System.out.println((i+1) + ". " + itemsList.get(i).getName());
			}
			
			Scanner pen = new Scanner(System.in);
			int pencat = pen.nextInt();
			
			if (pencat == 1 || pencat == 2 || pencat == 3) {
				showProduct(itemsList.get(pencat - 1));
			}
		}
		else if (cat == 2) {
			for (int i = 3; i < 6; i++) {
				System.out.println((i-2) + ". " + itemsList.get(i).getName());
			}
			
			Scanner pencil = new Scanner(System.in);
			int pencilcat = pencil.nextInt();
			
			if (pencilcat == 1 || pencilcat == 2 || pencilcat == 3) {
				showProduct(itemsList.get(pencilcat + 2));
			}
		}
		else if (cat == 3) {
			for (int i = 6; i < 9; i++) {
				System.out.println((i-5) + ". " + itemsList.get(i).getName());
			}
			
			Scanner note = new Scanner(System.in);
			int notecat = note.nextInt();
			
			if (notecat == 1 || notecat == 2 || notecat == 3) {
				showProduct(itemsList.get(notecat + 5));
			}
		}
		else if (cat == 4) {
			for (int i = 9; i < 12; i++) {
				System.out.println((i-8) + ". " + itemsList.get(i).getName());
			}
			
			Scanner paper = new Scanner(System.in);
			int papercat = paper.nextInt();
			
			if (papercat == 1 || papercat == 2 || papercat == 3) {
				showProduct(itemsList.get(papercat + 8));
			}
		}
	}
	
	public void showProduct(Item i) {
		System.out.println(i.toString());
	}
	
	public void checkStatus() {
		System.out.println("BUYERS INFO");
		System.out.println("================================================================");
		for (int i = 0; i < buyersList.size(); i++) {
			System.out.print((i+1) + ". " + buyersList.get(i).getuName() + " " + buyersList.get(i).getBonus());
			System.out.println(" " + buyersList.get(i).getBuyerCategory());
		}
	}
}

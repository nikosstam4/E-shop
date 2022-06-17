/* (C): 1059633, 1057764, 236100 */

public class ItemNotFoundException extends Exception {
    public ItemNotFoundException() {}
	
	public ItemNotFoundException(String message) {
		super(message);
		System.out.println(message);
	}
}

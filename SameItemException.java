/* (C): 1059633, 1057764, 236100 */

public class SameItemException extends Exception {
	public SameItemException() {}
	
	public SameItemException(String message) {
		super(message);
		System.out.println(message);
	}	
}
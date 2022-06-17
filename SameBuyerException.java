/* (C): 1059633, 1057764, 236100 */

public class SameBuyerException extends Exception {
	public SameBuyerException() {}
	
	public SameBuyerException(String message) {
		super(message);
		System.out.println(message);
	}	
}
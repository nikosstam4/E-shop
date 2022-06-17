/* (C): 1059633, 1057764, 236100 */

public class StockException extends Exception {
	public StockException() {}
	
	public StockException(String message) {
		super(message);
		System.out.println(message);
	}	
}
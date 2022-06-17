/* (C): 1059633, 1057764, 236100 */

public class NegativeException extends Exception{
    public NegativeException() {}
	
	public NegativeException(String message) {
		super(message);
		System.out.println(message);
	}	
}

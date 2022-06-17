/* (C): 1059633, 1057764, 236100 */

public class Owner extends User {
	private boolean isAdmin = true;
	
	public Owner(String owner_name, String owner_email, boolean admin) {
		super(owner_name, owner_email);
		this.isAdmin = admin;
	}
	
	public boolean setAdmin(boolean admin) {
		return isAdmin = admin;
	}
}

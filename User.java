/* (C): 1059633, 1057764, 236100 */

abstract class User {
	private String user_name;
	private String user_email;
	
	public User(String user_name, String user_email) {
		this.user_name = user_name;
		this.user_email = user_email;
	}
	
	public String getuName() {
		return user_name;
	}
	
	public String getuEmail() {
		return user_email;
	}
}

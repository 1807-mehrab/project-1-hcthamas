package blott.object;

public class User {
	public int getAccountID() {
		return accountID;
	}

	public void setAccountID(int accountID) {
		this.accountID = accountID;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(int accountID, String username, String email, String password, boolean isAdmin) {
		super();
		this.accountID = accountID;
		this.username = username;
		this.email = email;
		this.password = password;
		this.isAdmin = isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	
	@Override
	public String toString() {
		return "User [accountID=" + accountID + ", username=" + username + ", email=" + email + ", password=" + password
				+ ", isAdmin=" + isAdmin + "]";
	}
	
	private int accountID;
	private String username;
	private String email;
	private String password;
	private boolean isAdmin;
}

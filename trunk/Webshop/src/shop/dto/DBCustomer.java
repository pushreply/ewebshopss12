package shop.dto;

import java.util.LinkedList;

/**
 * @author roha0001
 * 
 */
public class DBCustomer {
	private String username;
	private String password;
	// A Customer has one or more addresses
	LinkedList<DBAddress> addresses = new LinkedList<DBAddress>();

	// default constructor
	public DBCustomer() {
	}

	public DBCustomer(String username, String password,
			LinkedList<DBAddress> address) {
		super();
		this.username = username;
		this.password = password;
		this.addresses = address;
	}

	public LinkedList<DBAddress> getAddress() {
		return addresses;
	}

	public void setAddress(LinkedList<DBAddress> address) {
		this.addresses = address;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String login) {
		this.username = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	// maybe useful for logging.
	public String toString() {
		return "Customer [login=" + username + ", password=" + password
				+ ", mailadress=" + addresses + "]";
	}

}

package shop.dto;

import java.util.LinkedList;

/**
 * @author roha0001
 * 
 */
public class DBCustomer {
	private int custID;
	private String username;
	private String password;
	// A Customer has one or more addresses
	LinkedList<DBAddress> addresses = new LinkedList<DBAddress>();

	// default constructor
	public DBCustomer() {
	}

	public DBCustomer(int custID, String username, String password,
			LinkedList<DBAddress> addresses) {
		super();
		this.custID = custID;
		this.username = username;
		this.password = password;
		this.addresses = addresses;
	}

	public int getCustID() {
		return custID;
	}

	public void setCustID(int custID) {
		this.custID = custID;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public LinkedList<DBAddress> getAddresses() {
		return addresses;
	}

	public void setAddresses(LinkedList<DBAddress> addresses) {
		this.addresses = addresses;
	}

	@Override
	public String toString() {
		return "DBCustomer [custID=" + custID + ", username=" + username
				+ ", password=" + password + ", addresses=" + addresses + "]";
	}

	
}

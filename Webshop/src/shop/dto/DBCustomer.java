package shop.dto;

import java.io.Serializable;
import java.util.LinkedList;

/**
 * @author roha0001,Mukunzi
 * 
 */
public class DBCustomer extends DBUUIDBase implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String username;
	private String password;
	// A Customer has one or more addresses
	LinkedList<DBAddress> addresses = new LinkedList<DBAddress>();

	// default constructor
	public DBCustomer() {
	}

	public DBCustomer(String username, String password,
			LinkedList<DBAddress> addresses) {
		super();
		this.username = username;
		this.password = password;
		this.addresses = addresses;
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
		return "DBCustomer [username=" + username
				+ ", password=" + password + ", addresses=" + addresses + "]";
	}

	
}

package shop.dto;

import java.util.LinkedList;

/**
 * @author roha0001
 *
 */
public class DBCustomer {
	private String login; 
	private String password; 
	
	//A Customer has one or more addresses
	LinkedList<DBAddress> address = new LinkedList<DBAddress>();

	public LinkedList<DBAddress> getAddress() {
		return address;
	}
	
	public void setAddress(LinkedList<DBAddress> address) {
		this.address = address;
	}
	
	//default constructor
	public DBCustomer(){}
	
	public DBCustomer(String login, String password, LinkedList<DBAddress> address) {
		super();
		this.login = login;
		this.password = password;
		this.address = address;
	}
	
	public String getLogin() {
		return login;
	}
	
	public void setLogin(String login) {
		this.login = login;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}


	@Override
	//maybe useful for logging.
	public String toString() {
		return "Customer [login=" + login + ", password=" + password
				+ ", mailadress=" + address + "]";
	} 
	
	

}

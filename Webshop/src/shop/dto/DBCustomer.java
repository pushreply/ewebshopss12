package shop.dto;

import java.util.LinkedList;

/**
 * @author roha0001
 *
 */
public class DBCustomer {
	private String login; 
	private String password; 
	private String nickname;
	
	//A Customer has one or more addresses
	LinkedList<DBAddress> address = new LinkedList<DBAddress>();

	public LinkedList<DBAddress> getAddress() {
		return address;
	}
	
	public void setAddress(LinkedList<DBAddress> address) {
		this.address = address;
	}
	
	public DBCustomer(String login, String password, LinkedList<DBAddress> address,
			String nickname) {
		super();
		this.login = login;
		this.password = password;
		this.address = address;
		this.nickname = nickname;
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

	public String getNickname() {
		return nickname;
	}
	
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	@Override
	//maybe useful for logging.
	public String toString() {
		return "Customer [login=" + login + ", password=" + password
				+ ", mailadress=" + address + ", nickname=" + nickname + "]";
	} 
	
	

}

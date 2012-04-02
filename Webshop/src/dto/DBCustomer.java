package dto;

import java.util.Collection;

/**
 * @author roha0001
 *
 */
public class DBCustomer {
	private String login; 
	private String password; 
	private String nickname;
	private Collection<DBAddress> adresses;
	private DBOrder order;
	public DBCustomer(String login, String password, String nickname,
			Collection<DBAddress> adresses, DBOrder order) {
		super();
		this.login = login;
		this.password = password;
		this.nickname = nickname;
		this.adresses = adresses;
		this.order = order;
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
	public Collection<DBAddress> getAdresses() {
		return adresses;
	}
	public void setAdresses(Collection<DBAddress> adresses) {
		this.adresses = adresses;
	}
	public DBOrder getOrder() {
		return order;
	}
	public void setOrder(DBOrder order) {
		this.order = order;
	}
	@Override
	public String toString() {
		return "DBCustomer [login=" + login + ", password=" + password
				+ ", nickname=" + nickname + ", adresses=" + adresses
				+ ", order=" + order + "]";
	}
	
	
}

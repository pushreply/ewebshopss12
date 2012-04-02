package shop.dto;
/**
 * @author roha0001
 *
 */
public class DBCustomer {
	private String login; 
	private String password; 
	private DBAddress mailadress;
	private String nickname;
	
	public DBCustomer(String login, String password, DBAddress mailadress,
			String nickname) {
		super();
		this.login = login;
		this.password = password;
		this.mailadress = mailadress;
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
	public DBAddress getMailadress() {
		return mailadress;
	}
	public void setMailadress(DBAddress mailadress) {
		this.mailadress = mailadress;
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
				+ ", mailadress=" + mailadress + ", nickname=" + nickname + "]";
	} 
	
	

}

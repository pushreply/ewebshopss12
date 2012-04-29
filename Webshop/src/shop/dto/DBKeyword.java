/**
 * 
 */
package shop.dto;

/**
 * @author roha0001
 *
 */
public class DBKeyword extends DBUUIDBase {
	
	private int keyID;
	private String keywordName;
	
	//default constructor
	public DBKeyword(){}


	public DBKeyword(int keyID, String keywordName) {
		super();
		this.keyID = keyID;
		this.keywordName = keywordName;
	}


	public int getKeyID() {
		return keyID;
	}


	public void setKeyID(int keyID) {
		this.keyID = keyID;
	}


	public String getKeywordName() {
		return keywordName;
	}


	public void setKeywordName(String keywordName) {
		this.keywordName = keywordName;
	}

	@Override
	public String toString() {
		return "DBKeyword [keyID=" + keyID + ", keywordName=" + keywordName+ "]";
	}

	
}

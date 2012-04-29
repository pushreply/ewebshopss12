/**
 * 
 */
package shop.dto;

/**
 * @author roha0001
 *
 */
public class DBKeyword extends DBUUIDBase {
	
	private String keywordName;
	
	//default constructor
	public DBKeyword(){}


	public DBKeyword(String keywordName) {
		super();
		this.keywordName = keywordName;
	}

	public String getKeywordName() {
		return keywordName;
	}


	public void setKeywordName(String keywordName) {
		this.keywordName = keywordName;
	}

	@Override
	public String toString() {
		return "DBKeyword [keywordName=" + keywordName+ "]";
	}

	
}

/**
 * 
 */
package shop.dto;

/**
 * @author roha0001
 *
 */
public class DBKeyword {
	private String keywordName;

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
		return "DBKeyword [keywordName=" + keywordName + "]";
	}
	
	

}

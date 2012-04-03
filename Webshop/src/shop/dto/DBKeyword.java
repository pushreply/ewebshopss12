/**
 * 
 */
package shop.dto;

/**
 * @author roha0001
 *
 */
public class DBKeyword {
	
	private int id_dbkeyword;
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
	
	public int getId_dbkeyword() {
		return id_dbkeyword;
	}

	public void setId_dbkeyword(int id_dbkeyword) {
		this.id_dbkeyword = id_dbkeyword;
	}

	@Override
	public String toString() {
		return "DBKeyword [keywordName=" + keywordName + "]";
	}
	
	

}

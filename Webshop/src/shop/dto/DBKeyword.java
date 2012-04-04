/**
 * 
 */
package shop.dto;

import java.util.LinkedList;

/**
 * @author roha0001
 *
 */
public class DBKeyword {
	
	private int id_dbkeyword;
	private String keywordName;
	LinkedList<DBAlbum> albums = new LinkedList<DBAlbum>();
	
	
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

	public LinkedList<DBAlbum> getAlbums() {
		return albums;
	}

	public void setAlbums(LinkedList<DBAlbum> albums) {
		this.albums = albums;
	}

	@Override
	public String toString() {
		return "DBKeyword [id_dbkeyword=" + id_dbkeyword + ", keywordName="
				+ keywordName + ", albums=" + albums + "]";
	}

}

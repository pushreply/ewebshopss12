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
	
	private int keyID;
	private String keywordName;
	LinkedList<DBAlbum> albums = new LinkedList<DBAlbum>();
	
	
	//default constructor
	public DBKeyword(){}


	public DBKeyword(int keyID, String keywordName, LinkedList<DBAlbum> albums) {
		super();
		this.keyID = keyID;
		this.keywordName = keywordName;
		this.albums = albums;
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


	public LinkedList<DBAlbum> getAlbums() {
		return albums;
	}


	public void setAlbums(LinkedList<DBAlbum> albums) {
		this.albums = albums;
	}


	@Override
	public String toString() {
		return "DBKeyword [keyID=" + keyID + ", keywordName=" + keywordName
				+ ", albums=" + albums + "]";
	}

	
}

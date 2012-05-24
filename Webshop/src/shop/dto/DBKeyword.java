/**
 * 
 */
package shop.dto;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 * @author roha0001
 * @author mukunzi
 *
 */
public class DBKeyword extends DBUUIDBase implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String keywordName;
	private List<DBAlbum> kalbum = new LinkedList<DBAlbum>();
	
	//default constructor
	public DBKeyword(){}


	public DBKeyword(String keywordName) {
		super();
		this.keywordName = keywordName;
		this.kalbum = new LinkedList<DBAlbum>();
	}
	
	public DBKeyword(String keywordName, List<DBAlbum> kalbum)
	{
		this.keywordName = keywordName;
		this.kalbum = kalbum;
	}
	
	public String getKeywordName() {
		return keywordName;
	}


	public void setKeywordName(String keywordName) {
		this.keywordName = keywordName;
	}
	
	public List<DBAlbum> getAlbums() {
		return kalbum;
	}

	public void setAlbums(List<DBAlbum> kalbum) {
		this.kalbum = kalbum;
	}
	
	public void setAlbum(DBAlbum album)
	{
		this.kalbum.add(album);
	}



	@Override
	public String toString() {
		return "DBKeyword [keywordName=" + keywordName+ "]";
	}

	
}

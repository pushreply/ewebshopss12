/**
 * 
 */
package shop.dto;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Hasiholan
 * @author sesc0009
 *
 */
public class DBCategory extends DBUUIDBase implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String categoryName;
	private List<DBAlbum> calbum;
	
	//default constructor
	public DBCategory(){}

	public DBCategory(String categoryName) {
		super();
		this.categoryName = categoryName;
		this.calbum = new LinkedList<DBAlbum>();
	}
	
	public DBCategory(String categoryName, List<DBAlbum> calbum)
	{
		this.categoryName = categoryName;
		this.calbum = calbum;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
	public List<DBAlbum> getAlbums()
	{
		return calbum;
	}
	
	public void setAlbum(DBAlbum album)
	{
		this.calbum.add(album);
	}
	
	public void setAlbums(List<DBAlbum> calbum)
	{
		this.calbum = calbum;
	}

}

/**
 * 
 */
package shop.dto;

import java.util.LinkedList;

/**
 * @author roha0001
 *
 */
public class DBCategory {
	private int catID;	
	private String categoryName;	
	LinkedList<DBAlbum> albums = new LinkedList<DBAlbum>();
	
	//default constructor
	public DBCategory(){}

	public DBCategory(int catID, String categoryName,
			LinkedList<DBAlbum> albums) {
		super();
		this.catID = catID;
		this.categoryName = categoryName;
		this.albums = albums;
	}

	public int getCatID() {
		return catID;
	}

	public void setCatID(int catID) {
		this.catID = catID;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public LinkedList<DBAlbum> getAlbums() {
		return albums;
	}

	public void setAlbums(LinkedList<DBAlbum> albums) {
		this.albums = albums;
	}

	@Override
	public String toString() {
		return "DBCategory [id_dbcategory=" + catID + ", categoryName="
				+ categoryName + ", albums=" + albums + "]";
	}
   
}

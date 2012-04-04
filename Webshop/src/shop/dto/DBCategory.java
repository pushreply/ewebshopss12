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
	private int id_dbcategory;	
	private String categoryName;	
	LinkedList<DBAlbum> albums = new LinkedList<DBAlbum>();
	
	//default constructor
	public DBCategory(){}

	public DBCategory(int id_dbcategory, String categoryName,
			LinkedList<DBAlbum> albums) {
		super();
		this.id_dbcategory = id_dbcategory;
		this.categoryName = categoryName;
		this.albums = albums;
	}

	public int getId_dbcategory() {
		return id_dbcategory;
	}

	public void setId_dbcategory(int id_dbcategory) {
		this.id_dbcategory = id_dbcategory;
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
		return "DBCategory [id_dbcategory=" + id_dbcategory + ", categoryName="
				+ categoryName + ", albums=" + albums + "]";
	}
   
}

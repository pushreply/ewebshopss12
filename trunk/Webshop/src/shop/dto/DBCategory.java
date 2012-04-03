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
	
	LinkedList<DBAlbum> category = new LinkedList<DBAlbum>();
	
	//default constructor
	public DBCategory(){}

	public DBCategory(String categoryName) {
		super();
		this.categoryName = categoryName;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
	public int getId_dbcategory() {
		return id_dbcategory;
	}

	public void setId_dbcategory(int id_dbcategory) {
		this.id_dbcategory = id_dbcategory;
	}


	@Override
	public String toString() {
		return "DBCategory [categoryName=" + categoryName + "]";
	}
	
	

}

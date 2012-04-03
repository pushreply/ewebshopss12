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
	private String categoryName;
	
	LinkedList<DBAlbum> category = new LinkedList<DBAlbum>();

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

	@Override
	public String toString() {
		return "DBCategory [categoryName=" + categoryName + "]";
	}
	
	

}

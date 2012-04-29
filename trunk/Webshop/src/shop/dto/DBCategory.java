/**
 * 
 */
package shop.dto;

import java.util.LinkedList;

/**
 * @author roha0001
 *
 */
public class DBCategory extends DBUUIDBase {
	private String categoryName;	
	
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

}

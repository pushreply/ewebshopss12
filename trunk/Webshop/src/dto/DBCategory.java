/**
 * 
 */
package dto;

/**
 * @author roha0001
 *
 */
public class DBCategory {
	private String categoryName;

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

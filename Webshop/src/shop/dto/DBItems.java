/**
 * 
 */
package shop.dto;

import java.io.Serializable;
import java.util.LinkedList;

/**
 * @author Hasiholan
 * @author mukunzi
 *
 */
public class DBItems extends DBUUIDBase implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int orderAmount;	
	//an item has one or many album
	LinkedList<DBAlbum> album = new LinkedList<DBAlbum>();
	
	public DBItems(){
		
	}

	public DBItems(int orderAmount, LinkedList<DBAlbum> album) {
		super();
		this.orderAmount = orderAmount;
		this.album = album;
	}

	public int getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(int orderAmount) {
		this.orderAmount = orderAmount;
	}

	public LinkedList<DBAlbum> getAlbum() {
		return album;
	}

	public void setAlbum(LinkedList<DBAlbum> album) {
		this.album = album;
	}

	@Override
	public String toString() {
		return "DBItems [orderAmount="
				+ orderAmount + ", album=" + album + "]";
	}
	
	
}

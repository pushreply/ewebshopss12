/**
 * 
 */
package shop.dto;

import java.util.LinkedList;

/**
 * @author roha0001
 *
 */
public class DBItems extends DBUUIDBase {
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

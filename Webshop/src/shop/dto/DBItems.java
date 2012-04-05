/**
 * 
 */
package shop.dto;

import java.util.LinkedList;

/**
 * @author roha0001
 *
 */
public class DBItems {
	private int itemID;
	private int orderAmount;	
	//an item has one or many album
	LinkedList<DBAlbum> album = new LinkedList<DBAlbum>();
	
	public DBItems(){
		
	}

	public DBItems(int itemID, int orderAmount, LinkedList<DBAlbum> album) {
		super();
		this.itemID = itemID;
		this.orderAmount = orderAmount;
		this.album = album;
	}

	public int getItemID() {
		return itemID;
	}

	public void setItemID(int itemID) {
		this.itemID = itemID;
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
		return "DBItems [id_dbitem=" + itemID + ", orderAmount="
				+ orderAmount + ", album=" + album + "]";
	}
	
	
}

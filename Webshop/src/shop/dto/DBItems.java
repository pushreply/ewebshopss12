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
	private int orderAmount;
	
	//an order must have one or many items.
	LinkedList<DBOrder> item = new LinkedList<DBOrder>();
	
	//an item has one or many album
	LinkedList<DBItems> album = new LinkedList<DBItems>();
	

	public LinkedList<DBItems> getAlbum() {
		return album;
	}

	public void setAlbum(LinkedList<DBItems> album) {
		this.album = album;
	}

	public DBItems(int orderAmount) {
		super();
		this.orderAmount = orderAmount;
	}

	public int getOrderAmount() {
		return orderAmount;
	}

	public LinkedList<DBOrder> getItem() {
		return item;
	}

	public void setItem(LinkedList<DBOrder> item) {
		this.item = item;
	}

	public void setOrderAmount(int orderAmount) {
		this.orderAmount = orderAmount;
	}

	@Override
	//maybe useful for logging.
	public String toString() {
		return "Items [orderAmount=" + orderAmount + "]";
	}
	
	

}

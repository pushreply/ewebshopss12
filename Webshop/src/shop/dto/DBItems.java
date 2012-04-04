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
	private int id_dbitem;
	private int orderAmount;	
	//an item has one or many album
	LinkedList<DBAlbum> album = new LinkedList<DBAlbum>();
	
	public DBItems(){
		
	}

	public DBItems(int id_dbitem, int orderAmount, LinkedList<DBAlbum> album) {
		super();
		this.id_dbitem = id_dbitem;
		this.orderAmount = orderAmount;
		this.album = album;
	}

	public int getId_dbitem() {
		return id_dbitem;
	}

	public void setId_dbitem(int id_dbitem) {
		this.id_dbitem = id_dbitem;
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
		return "DBItems [id_dbitem=" + id_dbitem + ", orderAmount="
				+ orderAmount + ", album=" + album + "]";
	}
	
	
}

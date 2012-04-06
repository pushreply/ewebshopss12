package shop.dao;

import java.util.LinkedList;

import shop.dto.DBAlbum;
import shop.dto.DBItems;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.ext.DatabaseClosedException;
import com.db4o.ext.DatabaseFileLockedException;
import com.db4o.ext.DatabaseReadOnlyException;
import com.db4o.ext.Db4oIOException;

public class DAOItem {
	
	
	/*
	 * 
	 */
	public void insertItem(DBItems item, ObjectContainer db)
	{	
		try {
			db.store(item);
		}
		catch(DatabaseFileLockedException e)
		{
			e.printStackTrace();
		}
		finally{
			db.close();
		}
	}
	
	
	/*
	 * 
	 */
	public LinkedList<DBItems> retrieveAllItems(ObjectContainer db) {
		
		LinkedList<DBItems> items= new LinkedList<DBItems>();
		
		try {
			
			DBItems item = new DBItems();
			
			ObjectSet<DBItems> result = db.queryByExample(DBItems.class);
			
			while(result.hasNext()) {
				item = result.next();
				items.add(item);
			}
		}
		catch (DatabaseFileLockedException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally {
			db.close();
		}
		
		return items;
	}
	
	
	/*
	 * 
	 */
	public void incrementAmount (ObjectContainer db, int itemID){
		
		DBItems item = new DBItems();
		try {
			ObjectSet<DBItems> result = db.queryByExample(new DBItems(itemID, 0, null));
			item = 	result.next();
			
			item.setOrderAmount(item.getOrderAmount()+1);
			
			db.store(item);
		} catch (Db4oIOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DatabaseClosedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DatabaseReadOnlyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			db.close();
		}
	}
	
	
	/*
	 * 
	 */
	public LinkedList<DBAlbum> itemsAlbums (ObjectContainer db, int itemID){
		
		DBItems item = new DBItems();
		LinkedList<DBAlbum> albums = null;
		
		try {
			ObjectSet<DBItems> result = db.queryByExample(new DBItems(itemID, 0, null));
			item = 	result.next();
			
			albums = item.getAlbum();
		} catch (Db4oIOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DatabaseClosedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			db.close();
		}
		
		return albums;
		
	}
	
}

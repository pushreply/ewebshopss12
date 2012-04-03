package shop.dao;

import java.util.LinkedList;

import shop.dto.DBItems;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.ext.DatabaseFileLockedException;

public class DAOItem {
	public void speichern(DBItems item, ObjectContainer db)
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
	
	public LinkedList<DBItems> auslesen(ObjectContainer db) {
		
		LinkedList<DBItems> linkedListDBItems= new LinkedList<DBItems>();
		
		try {
			
			DBItems item = new DBItems();
			
			ObjectSet<DBItems> result = db.queryByExample(item);
			
			while(result.hasNext()) {
				item = result.next();
				linkedListDBItems.add(item);
			}
		}
		catch (DatabaseFileLockedException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally {
			db.close();
		}
		
		return linkedListDBItems;
	}
}

package shop.dao;

import java.util.LinkedList;

import shop.dto.DBOrder;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.ext.DatabaseFileLockedException;

public class DAOOrder {
	
	/*
	 * Spechern DBOrder in Datenbank
	 */
	public void speichern(DBOrder order, ObjectContainer db)
	{	
		try {
			db.store(order);
		}
		catch(DatabaseFileLockedException e)
		{
			e.printStackTrace();
		}
		finally{
			db.close();
		}
		
		
	}
	
public LinkedList<DBOrder> auslesen(ObjectContainer db) {
		
		LinkedList<DBOrder> linkedListDBOrder = new LinkedList<DBOrder>();
		
		try {
			
			DBOrder order = new DBOrder();
			
			ObjectSet<DBOrder> result = db.queryByExample(order);
			
			while(result.hasNext()) {
				order = result.next();
				linkedListDBOrder.add(order);
			}
		}
		catch (DatabaseFileLockedException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally {
			db.close();
		}
		
		return linkedListDBOrder;
	}

}

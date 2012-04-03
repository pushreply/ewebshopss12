package shop.dao;

import java.util.LinkedList;

import com.db4o.ObjectSet;
import com.db4o.ObjectContainer;
import com.db4o.ext.DatabaseFileLockedException;

import shop.dto.DBCustomer;

public class DAOCustomer {
	
	public void speichern(DBCustomer cust, ObjectContainer db)
	{	
		try {
			db.store(cust);
		}
		catch(DatabaseFileLockedException e)
		{
			e.printStackTrace();
		}
		finally{
			db.close();
		}
	}
	
	public LinkedList<DBCustomer> auslesen(ObjectContainer db) {
		
		LinkedList<DBCustomer> linkedListDBCustomer= new LinkedList<DBCustomer>();
		
		try {
			
			DBCustomer cust = new DBCustomer();
			
			ObjectSet<DBCustomer> result = db.queryByExample(cust);
			
			while(result.hasNext()) {
				cust = result.next();
				linkedListDBCustomer.add(cust);
			}
		}
		catch (DatabaseFileLockedException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally {
			db.close();
		}
		
		return linkedListDBCustomer;
	}
}

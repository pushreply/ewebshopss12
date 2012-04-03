package shop.dao;

import java.util.LinkedList;

import com.db4o.ObjectSet;
import com.db4o.ObjectContainer;
import com.db4o.ext.DatabaseFileLockedException;

import shop.dto.DBCustomer;

public class DAOCustomer {
	
	public void speichern(DAOCustomer cust, ObjectContainer db)
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
	
	public LinkedList<DAOCustomer> auslesen(ObjectContainer db) {
		
		LinkedList<DAOCustomer> linkedListDAOCustomer= new LinkedList<DAOCustomer>();
		
		try {
			
			DAOCustomer cust = new DAOCustomer();
			
			ObjectSet<DAOCustomer> result = db.queryByExample(cust);
			
			while(result.hasNext()) {
				cust = result.next();
				linkedListDAOCustomer.add(cust);
			}
		}
		catch (DatabaseFileLockedException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally {
			db.close();
		}
		
		return linkedListDAOCustomer;
	}
}

package shop.dao;

import java.util.LinkedList;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.ext.DatabaseFileLockedException;

public class DAOItem {
	public void speichern(DAOItem item, ObjectContainer db)
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
	
	public LinkedList<DAOItem> auslesen(ObjectContainer db) {
		
		LinkedList<DAOItem> linkedListDAOItem= new LinkedList<DAOItem>();
		
		try {
			
			DAOItem item = new DAOItem();
			
			ObjectSet<DAOItem> result = db.queryByExample(item);
			
			while(result.hasNext()) {
				item = result.next();
				linkedListDAOItem.add(item);
			}
		}
		catch (DatabaseFileLockedException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally {
			db.close();
		}
		
		return linkedListDAOItem;
	}
}

package shop.dao;

import java.util.LinkedList;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.ext.DatabaseFileLockedException;

public class DAOCategory {
	public void speichern(DAOCategory category, ObjectContainer db)
	{	
		try {
			db.store(category);
		}
		catch(DatabaseFileLockedException e)
		{
			e.printStackTrace();
		}
		finally{
			db.close();
		}
	}
	
	public LinkedList<DAOCategory> auslesen(ObjectContainer db) {
		
		LinkedList<DAOCategory> linkedListDAOCategory= new LinkedList<DAOCategory>();
		
		try {
			
			DAOCategory category = new DAOCategory();
			
			ObjectSet<DAOCategory> result = db.queryByExample(category);
			
			while(result.hasNext()) {
				category = result.next();
				linkedListDAOCategory.add(category);
			}
		}
		catch (DatabaseFileLockedException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally {
			db.close();
		}
		
		return linkedListDAOCategory;
	}
}

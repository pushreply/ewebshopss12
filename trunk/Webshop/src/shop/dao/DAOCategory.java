package shop.dao;

import java.util.LinkedList;

import shop.dto.DBCategory;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.ext.DatabaseFileLockedException;

public class DAOCategory {
	public void speichern(DBCategory category, ObjectContainer db)
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
	
	public LinkedList<DBCategory> auslesen(ObjectContainer db) {
		
		LinkedList<DBCategory> linkedListDBCategory= new LinkedList<DBCategory>();
		
		try {
			
			DBCategory category = new DBCategory();
			
			ObjectSet<DBCategory> result = db.queryByExample(category);
			
			while(result.hasNext()) {
				category = result.next();
				linkedListDBCategory.add(category);
			}
		}
		catch (DatabaseFileLockedException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally {
			db.close();
		}
		
		return linkedListDBCategory;
	}
}

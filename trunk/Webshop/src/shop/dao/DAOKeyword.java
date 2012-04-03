package shop.dao;

import java.util.LinkedList;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.ext.DatabaseFileLockedException;

public class DAOKeyword {
	public void speichern(DAOKeyword keyword, ObjectContainer db)
	{	
		try {
			db.store(keyword);
		}
		catch(DatabaseFileLockedException e)
		{
			e.printStackTrace();
		}
		finally{
			db.close();
		}
	}
	
	public LinkedList<DAOKeyword> auslesen(ObjectContainer db) {
		
		LinkedList<DAOKeyword> linkedListDAOKeyword= new LinkedList<DAOKeyword>();
		
		try {
			
			DAOKeyword keyword = new DAOKeyword();
			
			ObjectSet<DAOKeyword> result = db.queryByExample(keyword);
			
			while(result.hasNext()) {
				keyword = result.next();
				linkedListDAOKeyword.add(keyword);
			}
		}
		catch (DatabaseFileLockedException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally {
			db.close();
		}
		
		return linkedListDAOKeyword;
	}
}

package shop.dao;

import java.util.LinkedList;

import shop.dto.DBKeyword;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.ext.DatabaseFileLockedException;

public class DAOKeyword {
	public void speichern(DBKeyword keyword, ObjectContainer db)
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
	
	public LinkedList<DBKeyword> auslesen(ObjectContainer db) {
		
		LinkedList<DBKeyword> linkedListDBKeyword= new LinkedList<DBKeyword>();
		
		try {
			
			DBKeyword keyword = new DBKeyword();
			
			ObjectSet<DBKeyword> result = db.queryByExample(keyword);
			
			while(result.hasNext()) {
				keyword = result.next();
				linkedListDBKeyword.add(keyword);
			}
		}
		catch (DatabaseFileLockedException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally {
			db.close();
		}
		
		return linkedListDBKeyword;
	}
}

package shop.dao;

import java.util.LinkedList;

import shop.dto.DBAddress;
import shop.dto.DBTrack;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.ext.DatabaseFileLockedException;

public class DAOAdress {
	
	public void speichern(DBAddress address, ObjectContainer db)
	{	
		try {
			db.store(address);
		}
		catch(DatabaseFileLockedException e)
		{
			e.printStackTrace();
		}
		finally{
			db.close();
		}
	}
	
	public LinkedList<DBAddress> auslesen(ObjectContainer db) {
		
		LinkedList<DBAddress> linkedListDBAddress= new LinkedList<DBAddress>();
		
		try {
			
			DBAddress address = new DBAddress();
			
			ObjectSet<DBAddress> result = db.queryByExample(address);
			
			while(result.hasNext()) {
				address = result.next();
				linkedListDBAddress.add(address);
			}
		}
		catch (DatabaseFileLockedException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally {
			db.close();
		}
		
		return linkedListDBAddress;
	}

}

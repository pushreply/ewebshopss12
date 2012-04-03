package shop.dao;

import java.util.LinkedList;
import java.util.List;

import shop.dto.DBAddress;
import shop.dto.DBTrack;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.ext.DatabaseFileLockedException;
import com.db4o.ext.DatabaseReadOnlyException;
import com.db4o.query.Predicate;

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
	
	//update DBAddress data
	public LinkedList<DBAddress> update(final DBAddress old_data, DBAddress new_data, ObjectContainer db) {
		
		try{
			ObjectSet<DBAddress> result = db.query(new Predicate<DBAddress>(){
				public boolean match(DBAddress adr){
					return adr.getStreet().equals(old_data.getStreet())||
							adr.getFirstName().equals(old_data.getFirstName())||
							adr.getLastName().equals(old_data.getLastName())||
							adr.getGender().equals(old_data.getGender())||
							adr.getCountry().equals(old_data.getCountry());
				}
			});
			new_data = old_data;
			new_data.setStreet(new_data.getStreet());
			//update new data
			db.close();
		}
		catch (DatabaseReadOnlyException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally{
			db.close();
		}
		return null;
		
	}
	
	
	

}

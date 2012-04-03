package shop.dao;

import java.util.ArrayList;

import shop.dto.DBTrack;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.ext.DatabaseFileLockedException;

//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;

public class DAOTrack {
	
//	private static final Log log = LogFactory.getLog(DAOTrack.class);
	
	public void speichern(DBTrack track, ObjectContainer db)
	{	
		try {
			db.store(track);
		}
		catch(DatabaseFileLockedException e)
		{
			e.printStackTrace();
		}
		finally{
			db.close();
		}
		
		
	}
	
	public ArrayList<DBTrack> auslesen(ObjectContainer db) {
		
		ArrayList<DBTrack> arrayListDBTrack = new ArrayList<DBTrack>();
		
		try {
			
			DBTrack track = new DBTrack();
			
			ObjectSet<DBTrack> result = db.queryByExample(track);
			
			while(result.hasNext()) {
				track = result.next();
				arrayListDBTrack.add(track);
			}
		}
		catch (DatabaseFileLockedException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally {
			db.close();
		}
		
		return arrayListDBTrack;
	}

}

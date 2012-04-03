package shop.dao;

import java.util.LinkedList;

import shop.dto.DBTrack;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.ext.DatabaseFileLockedException;

//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;

public class DAOTrack {
	
//	private static final Log log = LogFactory.getLog(DAOTrack.class);
	
	public static void speichern(DBTrack track, ObjectContainer db)
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
	
	public static LinkedList<DBTrack> auslesen(ObjectContainer db) {
		
		LinkedList<DBTrack> linkedListDBTrack = new LinkedList<DBTrack>();
		
		try {
			
			DBTrack track = new DBTrack();
			
			ObjectSet<DBTrack> result = db.queryByExample(track);
			
			while(result.hasNext()) {
				track = result.next();
				linkedListDBTrack.add(track);
			}
		}
		catch (DatabaseFileLockedException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally {
			db.close();
		}
		
		return linkedListDBTrack;
	}
	
	
	public static DBTrack auslesenEinzeln(ObjectContainer db, int trackid)
	{
		DBTrack a = new DBTrack();
		
		try {
			/* Wir erstellen ein Beispielobject mit dem int id_dbtrack */
			DBTrack track = new DBTrack(trackid);
			
			/* Die Methode get() gibt eine OjectSet zurück, das
			 * genau ein DBTrack enthält, das exakt dem Beispielobjekt
			 * entspricht. */
			 
			 ObjectSet<DBTrack> result = db.queryByExample(track);
			
			 /* Das bestimmte Object wird aus dem ObjectSet ausgelesen. */
			 a = result.next();
			 
			
		} catch (DatabaseFileLockedException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally{
			db.close();
		}
		
		return a;
	}
}

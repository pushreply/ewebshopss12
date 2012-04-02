package shop.dao;

import shop.dto.DBTrack;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;


public class DAOTrack {
	
	private static final Log log = LogFactory.getLog(DAOTrack.class);
	
	public void speichern(DBTrack track)
	{
		/* Wir öffnen eine Datenbank: */
		ObjectContainer db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(),"C:/projekt/Code/Webshop/db/WebShop.yap");
		
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
	
	public ArrayList<DBTrack> auslesen() {
		
		ObjectContainer db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(),"C:/projekt/Code/Webshop/db/WebShop.yap");
		
		ArrayList<DBTrack> arrayListDATrack = new ArrayList<DBTrack>();
		
		try {
			
			DBTrack track = new DBTRack(null);
			
			ObjectSet<DBTrack> result = db.queryByExample(track);
			
			while(result.hasNext()) {
				auto = result.next();
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

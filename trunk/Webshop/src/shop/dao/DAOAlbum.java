package shop.dao;

import java.util.LinkedList;

import shop.dto.DBAddress;
import shop.dto.DBAlbum;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.ext.DatabaseFileLockedException;

public class DAOAlbum {
	public void speichern(DBAlbum album, ObjectContainer db)
	{	
		try {
			db.store(album);
		}
		catch(DatabaseFileLockedException e)
		{
			e.printStackTrace();
		}
		finally{
			db.close();
		}
	}
	
	public LinkedList<DBAlbum> auslesen(ObjectContainer db) {
		
		LinkedList<DBAlbum> linkedListDBAlbum= new LinkedList<DBAlbum>();
		
		try {
			
			DBAlbum album = new DBAlbum();
			
			ObjectSet<DBAlbum> result = db.queryByExample(album);
			
			while(result.hasNext()) {
				album = result.next();
				linkedListDBAlbum.add(album);
			}
		}
		catch (DatabaseFileLockedException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally {
			db.close();
		}
		
		return linkedListDBAlbum;
	}
}

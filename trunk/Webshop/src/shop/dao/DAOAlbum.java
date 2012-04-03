package shop.dao;

import java.util.LinkedList;

import shop.dto.DBAddress;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.ext.DatabaseFileLockedException;

public class DAOAlbum {
	public void speichern(DAOAlbum album, ObjectContainer db)
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
	
public LinkedList<DAOAlbum> auslesen(ObjectContainer db) {
		
		LinkedList<DAOAlbum> linkedListDAOAlbum= new LinkedList<DAOAlbum>();
		
		try {
			
			DAOAlbum album = new DAOAlbum();
			
			ObjectSet<DAOAlbum> result = db.queryByExample(album);
			
			while(result.hasNext()) {
				album = result.next();
				linkedListDAOAlbum.add(album);
			}
		}
		catch (DatabaseFileLockedException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally {
			db.close();
		}
		
		return linkedListDAOAlbum;
	}
}

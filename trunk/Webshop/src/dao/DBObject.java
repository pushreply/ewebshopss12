package dao;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;

public class DBObject {
	
	public static ObjectContainer getConnection()
	{
		ObjectContainer db = null;
		
		try
		{
		// Öffne eine Datenbank
		  db = Db4oEmbedded.openFile("C:\\projektDB\\beispiel.db");
		  
		}
		catch(Exception e)
		{
			
		}
		
		return db;
	}

}

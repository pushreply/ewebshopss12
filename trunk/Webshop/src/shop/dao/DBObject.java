package shop.dao;

/**
 * @author mukunzi, Benjamin
 */

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.config.EmbeddedConfiguration;
import com.db4o.cs.Db4oClientServer;
import com.db4o.cs.config.ClientConfiguration;
import com.db4o.ext.DatabaseFileLockedException;

public class DBObject {
	
	public  ObjectContainer getConnection(ClientConfiguration config)
	{
        ObjectContainer db = null;
		
        db = Db4oClientServer.openClient(config, "localhost", 4488, "db4o", "db4o");
        
		/*try
		{
		
		if(file ==null)
		{
			throw new NullPointerException(file +" darf nicht null sein ");
		}
		
		else if(config == null)
		{
			// Öffne Datenbank
			db = Db4oEmbedded.openFile(file);
		}
		else
		{
		  // Öffne Datenbank
		  db = Db4oEmbedded.openFile(config, file);
		}
		  
		}
		catch(DatabaseFileLockedException  e)
		{
			e.printStackTrace();
		}*/
		
        System.out.println("getConnection() wird ausgeführt");
        
		return db;
	}
	
	/*
	public ObjectContainer getConnection(String file)
	{
		return getConnection(file, null);
	}*/
	
}

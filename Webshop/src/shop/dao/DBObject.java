package shop.dao;

/**
 * @author jemu0006
 *
 */

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.config.EmbeddedConfiguration;
import com.db4o.ext.DatabaseFileLockedException;

public class DBObject {
	
	public static ObjectContainer getConnection(String file, EmbeddedConfiguration config)
	{
        ObjectContainer db = null;
		
		try
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
		}
		
		return db;
	}
	
	public  void DBClose(ObjectContainer db)
	{
	  try
	  {
		if(db == null)
		{
			throw new NullPointerException(db +" darf nicht null sein ");
		}
		db.close();
	  }
	  catch(Exception e)
	  {
		  
	  }
	}
}

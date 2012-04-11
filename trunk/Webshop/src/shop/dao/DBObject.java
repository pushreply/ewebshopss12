package shop.dao;

/**
 * @author mukunzi
 */

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.config.EmbeddedConfiguration;
import com.db4o.ext.DatabaseFileLockedException;

public class DBObject {
	
	public  ObjectContainer getConnection(String file, EmbeddedConfiguration config)
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
			// �ffne Datenbank
			db = Db4oEmbedded.openFile(file);
		}
		else
		{
		  // �ffne Datenbank
		  db = Db4oEmbedded.openFile(config, file);
		}
		  
		}
		catch(DatabaseFileLockedException  e)
		{
			e.printStackTrace();
		}
		
		return db;
	}
	
	
	public ObjectContainer getConnection(String file)
	{
		return getConnection(file, null);
	}
	
}

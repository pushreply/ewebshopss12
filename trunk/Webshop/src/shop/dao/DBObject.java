package shop.dao;

/**
 * @author mukunzi, Benjamin
 */

import shop.dto.DBAddress;
import shop.dto.DBAlbum;
import shop.dto.DBCategory;
import shop.dto.DBCustomer;
import shop.dto.DBItems;
import shop.dto.DBKeyword;
import shop.dto.DBOrder;
import shop.dto.DBTrack;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.config.EmbeddedConfiguration;
import com.db4o.cs.Db4oClientServer;
import com.db4o.cs.config.ClientConfiguration;
import com.db4o.ext.DatabaseFileLockedException;

public class DBObject {
	
	public  ObjectContainer getConnection()
	{
		
		ClientConfiguration config = Db4oClientServer.newClientConfiguration(); //Eine neue Client-Configuration wird angelegt
		initConfig(config);
		ObjectContainer client = Db4oClientServer.openClient(config, "localhost", 4488, "db4o", "db4o");
	
		return client;
	}
	
	
	private void initConfig(ClientConfiguration config) {
		// Klasse Address
		config.common().objectClass(DBAddress.class).cascadeOnUpdate(true);
		config.common().objectClass(DBAddress.class).cascadeOnDelete(true);
		
		// Klasse Album
		config.common().objectClass(DBAlbum.class).cascadeOnUpdate(true);
		config.common().objectClass(DBAlbum.class).cascadeOnDelete(true);
		
		// Klasse Category
		config.common().objectClass(DBCategory.class).cascadeOnUpdate(true);
		config.common().objectClass(DBCategory.class).cascadeOnDelete(true);
		
		// Klasse Customer
		config.common().objectClass(DBCustomer.class).cascadeOnUpdate(true);
		config.common().objectClass(DBCustomer.class).cascadeOnDelete(true);
		
		// Klasse Items
		config.common().objectClass(DBItems.class).cascadeOnUpdate(true);
		config.common().objectClass(DBItems.class).cascadeOnDelete(true);
		

		// Klasse Keyword
		config.common().objectClass(DBKeyword.class).cascadeOnUpdate(true);
		config.common().objectClass(DBKeyword.class).cascadeOnDelete(true);
		
		// Klasse Order
		config.common().objectClass(DBOrder.class).cascadeOnUpdate(true);
		config.common().objectClass(DBOrder.class).cascadeOnDelete(true);


		// Klasse Track
		config.common().objectClass(DBTrack.class).cascadeOnUpdate(true);
		config.common().objectClass(DBTrack.class).cascadeOnDelete(true);
	}
	
}

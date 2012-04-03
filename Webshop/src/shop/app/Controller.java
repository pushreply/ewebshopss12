/**
 * @author Benjamin
 *
 */
 
package shop.app;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.config.EmbeddedConfiguration;

import shop.dto.DBAddress;
import shop.dto.DBAlbum;
import shop.dto.DBCategory;
import shop.dto.DBCustomer;
import shop.dto.DBItems;
import shop.dto.DBKeyword;
import shop.dto.DBOrder;
import shop.dto.DBTrack;

public class Controller {

public static void main(String[]args){			// main-Methode Anfang
	
	// String file = ""							//Dateiname der DB4O-Datenbank
	
	EmbeddedConfiguration config = Db4oEmbedded.newConfiguration();
	
	ObjectContainer con = null;
	
	//Klasse Address
	config.common().objectClass(DBAddress.class).cascadeOnUpdate(true);
	config.common().objectClass(DBAddress.class).cascadeOnDelete(true);
	config.common().objectClass(DBAddress.class).objectField("street").indexed(true);
	config.common().objectClass(DBAddress.class).objectField("country").indexed(true);
	config.common().objectClass(DBAddress.class).objectField("firstName").indexed(true);
	config.common().objectClass(DBAddress.class).objectField("lastName").indexed(true);
	config.common().objectClass(DBAddress.class).objectField("gender").indexed(true);
	
	//Klasse Album
	config.common().objectClass(DBAlbum.class).cascadeOnUpdate(true);
	config.common().objectClass(DBAlbum.class).cascadeOnDelete(true);
	
	
	//Klasse Category
	config.common().objectClass(DBCategory.class).cascadeOnUpdate(true);
	config.common().objectClass(DBCategory.class).cascadeOnDelete(true);
	
	//Klasse Customer
	config.common().objectClass(DBCustomer.class).cascadeOnUpdate(true);
	config.common().objectClass(DBCustomer.class).cascadeOnDelete(true);
	
	//Klasse Items
	config.common().objectClass(DBItems.class).cascadeOnUpdate(true);
	config.common().objectClass(DBItems.class).cascadeOnDelete(true);
	
	//Klasse Keyword
	config.common().objectClass(DBKeyword.class).cascadeOnUpdate(true);
	config.common().objectClass(DBKeyword.class).cascadeOnDelete(true);
	
	//Klasse Order
	config.common().objectClass(DBOrder.class).cascadeOnUpdate(true);
	config.common().objectClass(DBOrder.class).cascadeOnDelete(true);
	
	//Klasse Track
	config.common().objectClass(DBTrack.class).cascadeOnUpdate(true);
	config.common().objectClass(DBTrack.class).cascadeOnDelete(true);
	
}	// Ende der main-Methode
	
}	// Ende Klasse Controller

/**
 * @author Benjamin
 *
 */

package shop.app;

import javax.servlet.http.*;

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

public class Controller extends HttpServlet {

	public void service(HttpServletRequest request, HttpServletResponse response){			// service-Methode Anfang

		System.out.println("Test");
		
		/*ObjectContainer db = null;
	
		EmbeddedConfiguration config = Db4oEmbedded.newConfiguration();
	
		String file = "WebshopDB.dbf";
		
	
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
		config.common().objectClass(DBAlbum.class).objectField("coverpath").indexed(true);
		config.common().objectClass(DBAlbum.class).objectField("albumTitel").indexed(true);
		config.common().objectClass(DBAlbum.class).objectField("artist").indexed(true);
		config.common().objectClass(DBAlbum.class).objectField("numberOfDisks").indexed(true);
		config.common().objectClass(DBAlbum.class).objectField("price").indexed(true);
		config.common().objectClass(DBAlbum.class).objectField("amount").indexed(true);
		config.common().objectClass(DBAlbum.class).objectField("numberOfTracks").indexed(true);
		config.common().objectClass(DBAlbum.class).objectField("label").indexed(true);
		
		//Klasse Category
		config.common().objectClass(DBCategory.class).cascadeOnUpdate(true);
		config.common().objectClass(DBCategory.class).cascadeOnDelete(true);
		config.common().objectClass(DBCategory.class).objectField("categoryName").indexed(true);
		
		//Klasse Customer
		config.common().objectClass(DBCustomer.class).cascadeOnUpdate(true);
		config.common().objectClass(DBCustomer.class).cascadeOnDelete(true);
		config.common().objectClass(DBCustomer.class).objectField("login").indexed(true);
		config.common().objectClass(DBCustomer.class).objectField("password").indexed(true);
		config.common().objectClass(DBCustomer.class).objectField("mailadress").indexed(true);
		config.common().objectClass(DBCustomer.class).objectField("nickname").indexed(true);
		
		//Klasse Items
		config.common().objectClass(DBItems.class).cascadeOnUpdate(true);
		config.common().objectClass(DBItems.class).cascadeOnDelete(true);
		config.common().objectClass(DBItems.class).objectField("orderAmount").indexed(true);
		
		//Klasse Keyword
		config.common().objectClass(DBKeyword.class).cascadeOnUpdate(true);
		config.common().objectClass(DBKeyword.class).cascadeOnDelete(true);
		config.common().objectClass(DBKeyword.class).objectField("keywordName").indexed(true);
		
		//Klasse Order
		config.common().objectClass(DBOrder.class).cascadeOnUpdate(true);
		config.common().objectClass(DBOrder.class).cascadeOnDelete(true);
		config.common().objectClass(DBOrder.class).objectField("cust").indexed(true);
		config.common().objectClass(DBOrder.class).objectField("datum").indexed(true);
		config.common().objectClass(DBOrder.class).objectField("item").indexed(true);
		config.common().objectClass(DBOrder.class).objectField("state").indexed(true);
		
		//Klasse Track
		config.common().objectClass(DBTrack.class).cascadeOnUpdate(true);
		config.common().objectClass(DBTrack.class).cascadeOnDelete(true);
		config.common().objectClass(DBOrder.class).objectField("file").indexed(true);
		config.common().objectClass(DBOrder.class).objectField("trackTitle").indexed(true);
		config.common().objectClass(DBOrder.class).objectField("trackArtist").indexed(true);
		config.common().objectClass(DBOrder.class).objectField("trackDate").indexed(true);
		config.common().objectClass(DBOrder.class).objectField("trackGenre").indexed(true);
		config.common().objectClass(DBOrder.class).objectField("trackNumber").indexed(true);
		config.common().objectClass(DBOrder.class).objectField("trackDiskNumber").indexed(true);
		
		db = Db4oEmbedded.openFile(config, file);
		
		/*DBKeyword test = new DBKeyword("Michael Schumacher");
		db.store(test);
		System.out.println("Stored " + test);*/
		
		//db.close();
		
	}	// Ende der service-Methode

}	// Ende Klasse Controller

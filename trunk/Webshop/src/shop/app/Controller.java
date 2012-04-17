/**
 * @author Benjamin
 * @author Andreas
 */

package shop.app;

import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shop.dao.DBObject;
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

public class Controller extends HttpServlet {

	private static final long serialVersionUID = -3188047853265442959L;

	/**
	 * 
	 */
	public void service(HttpServletRequest request, HttpServletResponse response) { // service-Methode
																					// Anfang

		System.out.println("Test");

		ObjectContainer db = null;

		EmbeddedConfiguration config = Db4oEmbedded.newConfiguration();

		String file = "WebshopDB.dbf";

		// Klasse Address
		config.common().objectClass(DBAddress.class).cascadeOnUpdate(true);
		config.common().objectClass(DBAddress.class).cascadeOnDelete(true);
		config.common().objectClass(DBAddress.class).objectField("street")
				.indexed(true);
		config.common().objectClass(DBAddress.class).objectField("country")
				.indexed(true);
		config.common().objectClass(DBAddress.class).objectField("firstName")
				.indexed(true);
		config.common().objectClass(DBAddress.class).objectField("lastName")
				.indexed(true);
		config.common().objectClass(DBAddress.class).objectField("gender")
				.indexed(true);

		// Klasse Album
		config.common().objectClass(DBAlbum.class).cascadeOnUpdate(true);
		config.common().objectClass(DBAlbum.class).cascadeOnDelete(true);
		config.common().objectClass(DBAlbum.class).objectField("coverpath")
				.indexed(true);
		config.common().objectClass(DBAlbum.class).objectField("albumTitel")
				.indexed(true);
		config.common().objectClass(DBAlbum.class).objectField("artist")
				.indexed(true);
		config.common().objectClass(DBAlbum.class).objectField("numberOfDisks")
				.indexed(true);
		config.common().objectClass(DBAlbum.class).objectField("price")
				.indexed(true);
		config.common().objectClass(DBAlbum.class).objectField("amount")
				.indexed(true);
		config.common().objectClass(DBAlbum.class)
				.objectField("numberOfTracks").indexed(true);
		config.common().objectClass(DBAlbum.class).objectField("label")
				.indexed(true);

		// Klasse Category
		config.common().objectClass(DBCategory.class).cascadeOnUpdate(true);
		config.common().objectClass(DBCategory.class).cascadeOnDelete(true);
		config.common().objectClass(DBCategory.class)
				.objectField("categoryName").indexed(true);

		// Klasse Customer
		config.common().objectClass(DBCustomer.class).cascadeOnUpdate(true);
		config.common().objectClass(DBCustomer.class).cascadeOnDelete(true);
		config.common().objectClass(DBCustomer.class).objectField("login")
				.indexed(true);
		config.common().objectClass(DBCustomer.class).objectField("password")
				.indexed(true);
		config.common().objectClass(DBCustomer.class).objectField("mailadress")
				.indexed(true);
		config.common().objectClass(DBCustomer.class).objectField("nickname")
				.indexed(true);

		// Klasse Items
		config.common().objectClass(DBItems.class).cascadeOnUpdate(true);
		config.common().objectClass(DBItems.class).cascadeOnDelete(true);
		config.common().objectClass(DBItems.class).objectField("orderAmount")
				.indexed(true);

		// Klasse Keyword
		config.common().objectClass(DBKeyword.class).cascadeOnUpdate(true);
		config.common().objectClass(DBKeyword.class).cascadeOnDelete(true);
		config.common().objectClass(DBKeyword.class).objectField("keywordName")
				.indexed(true);

		// Klasse Order
		config.common().objectClass(DBOrder.class).cascadeOnUpdate(true);
		config.common().objectClass(DBOrder.class).cascadeOnDelete(true);
		config.common().objectClass(DBOrder.class).objectField("cust")
				.indexed(true);
		config.common().objectClass(DBOrder.class).objectField("datum")
				.indexed(true);
		config.common().objectClass(DBOrder.class).objectField("item")
				.indexed(true);
		config.common().objectClass(DBOrder.class).objectField("state")
				.indexed(true);

		// Klasse Track
		config.common().objectClass(DBTrack.class).cascadeOnUpdate(true);
		config.common().objectClass(DBTrack.class).cascadeOnDelete(true);
		config.common().objectClass(DBTrack.class).objectField("file")
				.indexed(true);
		config.common().objectClass(DBTrack.class).objectField("trackTitle")
				.indexed(true);
		config.common().objectClass(DBTrack.class).objectField("trackArtist")
				.indexed(true);
		config.common().objectClass(DBTrack.class).objectField("trackDate")
				.indexed(true);
		config.common().objectClass(DBTrack.class).objectField("trackGenre")
				.indexed(true);
		config.common().objectClass(DBTrack.class).objectField("trackNumber")
				.indexed(true);
		config.common().objectClass(DBTrack.class)
				.objectField("trackDiskNumber").indexed(true);

		//DBObject baut eine verbindung zur DB auf
		db = new DBObject().getConnection(file, config);

		DBKeyword test = new DBKeyword(0, "Auto");
		db.store(test);

		List<DBKeyword> result = null;
		try {
			result = db.queryByExample(DBKeyword.class);
		} catch (Exception e) {
			System.out.println(e);
		}

		for (int i = 0; i < result.size(); i++) {
			DBKeyword a = (DBKeyword) result.get(i);
			System.out.println(a.getKeywordName());
		}
		
		testing(db);
		
		db.close();
		
	} // Ende der service-Methode

	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
	}

	/**
	 * method to test stuff
	 */
	private void testing(ObjectContainer db) {

//		Sequence sequence = new Sequence(0, "trackid");
//		DBTrack test2 = Trackfactory.createTrack(new File(
//				"C:\\projekt\\DB\\Webshop\\WebContent\\images\\wwm.mp3"),
//				sequence.nextVal());
//		db.store(test2);
		
		System.out.println("---------------");

		List<DBTrack> result2 = null;
		try {
			result2 = db.query(DBTrack.class);
		} catch (Exception e) {
			System.out.println(e);
		}

		for (DBTrack dbTrack : result2) {
			System.out.println(dbTrack.getTrackArtist());
			System.out.println(dbTrack.getFile());
		}

		db.close();
	}
} // Ende Klasse Controller

/**
 * @author Benjamin
 * @author Andreas
 */

package shop.app;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.servlet.ServletFileUpload;

import shop.actions.MultipartMap;
import shop.actions.UploadMusicFile;

import shop.dao.DBObject;
import shop.dto.DBAddress;
import shop.dto.DBAlbum;
import shop.dto.DBCategory;
import shop.dto.DBCustomer;
import shop.dto.DBItems;
import shop.dto.DBKeyword;
import shop.dto.DBOrder;
import shop.dto.DBTrack;

import shop.dao.DAOKeyword;
import shop.dao.DAOTrack;


import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.config.EmbeddedConfiguration;
import com.db4o.cs.Db4oClientServer;
import com.db4o.cs.config.ClientConfiguration;

@WebServlet(urlPatterns = { "/upload" })
@MultipartConfig(location = "C:\\projekt", maxFileSize = 10485760L) // 10MB.
public class Controller extends HttpServlet {

	private static final long serialVersionUID = -3188047853265442959L;

	/**
	 * 
	 */
	public void service(HttpServletRequest request, HttpServletResponse response) { // service-Methode
																					// Anfang

		//System.out.println("Test");

		ClientConfiguration config = Db4oClientServer.newClientConfiguration(); //Eine neue Client-Configuration wird angelegt
		
		ObjectContainer db = null;	//Ein leerer Objekt-Container wird angelegt

		//String file = "WebshopDB.dbf";

		initConfig(config);

		//DBObject baut eine Verbindung zur DB auf
		db = new DBObject().getConnection(config);

		System.out.println("Verbindung wurde aufgebaut");
		
		
		//Controller leitet die Anfragen entsprechend weiter
		if((request.getParameter("trackHochladenButton")!=null)){
			System.out.println("trackhochladenButton");
			RequestDispatcher disp = request.getRequestDispatcher("/trackhinzufuegen.jsp");
				try {
					disp.forward(request, response);
				} catch (ServletException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		
		else if ((request.getParameter("tracksAnzeigenButton") != null)) {
			
			request.setAttribute("AlbumTracks", DAOTrack.retrieveAllTracks(db));
			RequestDispatcher disp = request.getRequestDispatcher("/track.jsp");
			try {
				disp.forward(request, response);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
        else if ((request.getParameter("trackEditieren") != null)) {
        	
        	UUID trackID = UUID.fromString(request.getParameter("uuid"));
			
			request.setAttribute("track", DAOTrack.retrieveTrackByID(db,trackID));
			RequestDispatcher disp = request.getRequestDispatcher("/TrackEditieren.jsp");
			try {
				disp.forward(request, response);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		/**
		 * @author Schneider
		 * @author Sergej
		 */
		
		else if(ServletFileUpload.isMultipartContent(request)){
			System.out.println("ich bin in upload");
		
			UploadMusicFile up = new UploadMusicFile(this, request, response, db);

		
			db.close();
		}
	} // Ende der service-Methode

	private void initConfig(ClientConfiguration config) {
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
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
	}

	/**
	 * method to test stuff
	 */
	private void testing(ObjectContainer db) {

		// Sequence sequence = new Sequence(0, "trackid");
		// DBTrack test2 = Trackfactory.createTrack(new File(
		// "C:\\projekt\\DB\\Webshop\\WebContent\\images\\wwm.mp3"),
		// sequence.nextVal());
		// db.store(test2);

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
	}
} // Ende Klasse Controller

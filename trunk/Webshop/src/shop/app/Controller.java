/**
 * @author Benjamin
 *
 */

package shop.app;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.config.EmbeddedConfiguration;


import shop.actions.UploadMusicFile;
import shop.dao.DAOTrack;
import shop.dao.DBObject;
import shop.dto.DBAddress;
import shop.dto.DBAlbum;
import shop.dto.DBCategory;
import shop.dto.DBCustomer;
import shop.dto.DBItems;
import shop.dto.DBKeyword;
import shop.dto.DBOrder;
import shop.dto.DBTrack;
import shop.util.Sequence;

public class Controller extends HttpServlet {
	
	//content-type
	private static final String CONTENT_TYPE = "text/html; charset=windows-1252";
	
	ObjectContainer db = null;
	
	EmbeddedConfiguration config = Db4oEmbedded.newConfiguration();

	String file = "WebshopDB.dbf";
	
	//http://docs.oracle.com/javase/6/docs/api/java/io/Serializable.html
	private static final long serialVersionUID = 1L; 
	
	//init()
	public void init() throws ServletException {
		super.init();
	}
	
	//service()
	public void service(HttpServletRequest request, HttpServletResponse response){			// service-Methode Anfang

		System.out.println("Test");
		//test zum taggen.... einkommentieren
//		try {
//			TaggingTest.starttest();
//		} catch (FrameDamagedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		
		response.setContentType(CONTENT_TYPE);
		
		//variable for post/get method 
		String param = request.getParameter("action");
		
		
		try {
			//if 'param' is empty/null, show 'index.jsp', only temporary..
			request.getRequestDispatcher("index.jsp").forward(request, response);
			
			//show what controller does in eclipse console
			System.out.println("Controller: " + param); 
			
			//create session
			//HttpSession session = request.getSession(true);
			
			//testing file upload
			if(request.getParameter("filename") != null){
				//DBObject baut eine verbindung zur DB auf
				db = new DBObject().getConnection(file, config);
				
				DBTrack filename = new DBTrack();
				DAOTrack.insertTrack(filename, db);
				request.setAttribute("filename", filename);
				
				
			}
			
			
			
		} catch (ServletException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		

		
	
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
		config.common().objectClass(DBTrack.class).objectField("file").indexed(true);
		config.common().objectClass(DBTrack.class).objectField("trackTitle").indexed(true);
		config.common().objectClass(DBTrack.class).objectField("trackArtist").indexed(true);
		config.common().objectClass(DBTrack.class).objectField("trackDate").indexed(true);
		config.common().objectClass(DBTrack.class).objectField("trackGenre").indexed(true);
		config.common().objectClass(DBTrack.class).objectField("trackNumber").indexed(true);
		config.common().objectClass(DBTrack.class).objectField("trackDiskNumber").indexed(true);
		 
		//Klasse Sequence
		config.common().objectClass(Sequence.class).objectField("nextNumber").indexed(true);
		config.common().objectClass(Sequence.class).objectField("seqname").indexed(true);
		
		
		//DBObject baut eine verbindung zur DB auf
		db = new DBObject().getConnection(file, config);
		
		
		
		
		db.close();
		
	}	// Ende der service-Methode

}	// Ende Klasse Controller

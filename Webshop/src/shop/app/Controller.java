/**
 * @author Benjamin
 * @author Andreas
 * @author mukunzi
 */

package shop.app;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.servlet.ServletFileUpload;

import shop.actions.AbstractAction;
import shop.actions.CategoryAction;
import shop.actions.CustomerAction;
import shop.actions.KeywordAction;
import shop.actions.TrackAction;
import shop.actions.UploadMusicFile;
import shop.dao.DBObject;
import shop.dao.GenericDaoImpl;
import shop.dao.IGenericDao;
import shop.dto.DBTrack;

import com.db4o.ObjectContainer;

@WebServlet(urlPatterns = { "/upload" })
@MultipartConfig(location = "C:\\projekt", maxFileSize = 10485760L)
// 10MB.
public class Controller extends HttpServlet {

	private static final long serialVersionUID = -3188047853265442959L;

	private ObjectContainer db = null;
	private static Map<String, AbstractAction> actionMap = new HashMap<String, AbstractAction>();

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);


		// Map initialisieren mit allen benötigten Actions
		actionMap.put("category", new CategoryAction());
		actionMap.put("track", new TrackAction());
		actionMap.put("keyword", new KeywordAction());
		actionMap.put("customer", new CustomerAction()); //meine ist noch nicht vollkommen fertig, aber erst sicher committen.
		
		// more "put" go here
	}

	/**
	 * 
	 */
	public void service(HttpServletRequest request, HttpServletResponse response) {
		
		// DBObject baut eine Verbindung zur DB auf
		db = new DBObject().getConnection();
		
		// Aufruf des passenden Controllers
		AbstractAction action = actionMap.get(request.getParameter("action"));
		if (action != null)
			action.processAndClose(request, response, db);

		// Controller leitet die Anfragen entsprechend weiter
		
		// Track editieren
		else if ((request.getParameter("trackEditieren") != null)) {

			// UUID trackID = UUID.fromString(request.getParameter("uuid"));
			IGenericDao<DBTrack> dao = new GenericDaoImpl<DBTrack>(DBTrack.class, db);
			
			request.setAttribute("track",
					dao.read("3b0bf46a-2ff6-4f2e-be52-981a2864a96f"));
			RequestDispatcher disp = request
					.getRequestDispatcher("/TrackEditieren.jsp");
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
		
			
		// Das editierte Track speichern
		if ((request.getParameter("TrackEditierenButton") != null)) {
			System.out.println("TrackEditierenButton");
			System.out.println("----"
					+ request.getParameter("TrackEditierenButton") + "-----");
			
			IGenericDao<DBTrack> dao = new GenericDaoImpl<DBTrack>(DBTrack.class, db);
			DBTrack track = dao.read(request.getParameter("uuid"));
			
			String titel = request.getParameter("titel");
			String artist = request.getParameter("artist");
			int date = Integer.parseInt((request.getParameter("date")));
			String genre = request.getParameter("genre");
			int trackanzahl = Integer.parseInt(request
					.getParameter("trackanzahl"));
			int diskanzahl = Integer.parseInt(request
					.getParameter("diskanzahl"));
			track.setTrackTitle(titel);
			track.setTrackArtist(artist);
			track.setTrackDate(date);
			track.setTrackGenre(genre);
			track.setTrackNumber(trackanzahl);
			track.setTrackDiskNumber(diskanzahl);
			
			dao.update(track);
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

		/**
		 * @author Schneider
		 * @author Sergej
		 */

		else if (ServletFileUpload.isMultipartContent(request)) {
			System.out.println("ich bin in upload");

			UploadMusicFile up = new UploadMusicFile(request, response,	db);

		}
		db.close();
	} // Ende der service-Methode

} // Ende Klasse Controller

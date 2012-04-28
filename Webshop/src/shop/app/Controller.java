/**
 * @author Benjamin
 * @author Andreas
 * @author mukunzi
 */

package shop.app;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
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

import shop.actions.AbstractAction;
import shop.actions.CategoryAction;
import shop.actions.UploadMusicFile;
import shop.dao.DAOTrack;
import shop.dao.DBObject;

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
		// more puts here
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
		if ((request.getParameter("trackHochladenButton") != null)) {
			System.out.println("trackhochladenButton");
			RequestDispatcher disp = request
					.getRequestDispatcher("/trackhinzufuegen.jsp");
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
		
		// Track editieren
		else if ((request.getParameter("trackEditieren") != null)) {

			// UUID trackID = UUID.fromString(request.getParameter("uuid"));

			UUID trackID = UUID
					.fromString("3b0bf46a-2ff6-4f2e-be52-981a2864a96f");

			request.setAttribute("track",
					DAOTrack.retrieveTrackByID(db, trackID));
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

			UUID uuid = UUID.fromString(request.getParameter("uuid"));
			String titel = request.getParameter("titel");
			String artist = request.getParameter("artist");
			int date = Integer.parseInt((request.getParameter("date")));
			String genre = request.getParameter("genre");
			int trackanzahl = Integer.parseInt(request
					.getParameter("trackanzahl"));
			int diskanzahl = Integer.parseInt(request
					.getParameter("diskanzahl"));

			DAOTrack.editTrack(db, uuid, titel, artist, date, genre,
					trackanzahl, diskanzahl);
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

			UploadMusicFile up = new UploadMusicFile(this, request, response,
					db);

			db.close();
		}
	} // Ende der service-Methode

} // Ende Klasse Controller

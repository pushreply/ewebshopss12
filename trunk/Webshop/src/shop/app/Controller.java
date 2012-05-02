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
import shop.actions.KeywordAction;
import shop.actions.LoginAction;
import shop.actions.RegisterAction;
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
		actionMap.put("editieren", new TrackAction());
		actionMap.put("TrackEditierenButton", new TrackAction());
		actionMap.put("keyword", new KeywordAction());
		actionMap.put("Login", new LoginAction()); 
		actionMap.put("register", new RegisterAction());

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

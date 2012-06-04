/**
 * @author Benjamin
 * @author Andreas
 * @author mukunzi
 */

package shop.app;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.servlet.ServletFileUpload;

import shop.actions.AbstractAction;
//import shop.actions.AddressAction;
import shop.actions.AddressAction;
import shop.actions.AlbumAction;
import shop.actions.CategoryAction;
import shop.actions.HomeAction;
import shop.actions.KeywordAction;
import shop.actions.LoginAction;
import shop.actions.LogoutAction;
import shop.actions.OrderAction;
import shop.actions.ProfileAction;
import shop.actions.RegisterAction;
import shop.actions.SearchAction;
import shop.actions.TrackAction;
import shop.actions.UploadMusicFile;
import shop.dao.DBObject;
import shop.util.ErrorHandler;

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
		actionMap.put("home", new HomeAction());
		actionMap.put("category", new CategoryAction());
		actionMap.put("track", new TrackAction());
		actionMap.put("keyword", new KeywordAction());
		actionMap.put("login", new LoginAction());
		actionMap.put("logout", new LogoutAction());
		actionMap.put("register", new RegisterAction());
		actionMap.put("album", new AlbumAction());
		actionMap.put("albumsearch", new SearchAction());
		actionMap.put("orderalbum", new OrderAction());
		actionMap.put("customer", new ProfileAction());
		actionMap.put("address", new AddressAction());
		
		// more "put" go here
	}

	/**
	 * 
	 */
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		ErrorHandler errorHandler = new ErrorHandler();

		// DBObject baut eine Verbindung zur DB auf
		try {
			db = new DBObject().getConnection();
		} catch (Exception e) {
			errorHandler.toUser("Datenbankserver ist nicht erreichbar", e);
		}

		// Aufruf des passenden Controllers
		AbstractAction action = actionMap.get(request.getParameter("action"));
		if (action != null)
			action.processAndClose(request, response, db);

		/**
		 * @author Schneider
		 * @author Sergej
		 */

		else if (ServletFileUpload.isMultipartContent(request)) {
			UploadMusicFile up = new UploadMusicFile(request, response, db);
		} 
		if (db != null)
			db.close();
	} // Ende der service-Methode

} // Ende Klasse Controller

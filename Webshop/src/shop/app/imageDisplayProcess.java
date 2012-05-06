package shop.app;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.db4o.ObjectContainer;

import shop.dao.DBObject;
import shop.dao.GenericDaoImpl;
import shop.dao.IGenericDao;
import shop.dto.DBAlbum;
import shop.util.ErrorHandler;

/**
 * @author mukunzi
 */

/**
 * Servlet implementation class imageDisplayProcess
 */
@WebServlet("/imageDisplayProcess")
public class imageDisplayProcess extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public imageDisplayProcess() {
        super();
        
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ObjectContainer db = new DBObject().getConnection();
		byte[] imageData= null;
		
		ErrorHandler errorHandler = new ErrorHandler();
		try {
			IGenericDao<DBAlbum> dao = new GenericDaoImpl<DBAlbum>(DBAlbum.class, db);
			imageData = dao.read(request.getParameter("identifier")).getCover();
		} catch (InstantiationException e) {
			errorHandler.toUser(
					"Etwas mit der Weiterleitung ist schief gelaufen.", e);
		} catch (IllegalAccessException e) {
			errorHandler
			.toUser("Ein technischer Fehler ist aufgetreten", e);
		}
		response.setContentType("image/jpeg");
		response.setContentLength(imageData.length);
		response.getOutputStream().write(imageData);
		response.getOutputStream().flush();
		response.getOutputStream().close();
		
	}

}

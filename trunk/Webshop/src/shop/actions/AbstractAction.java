package shop.actions;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shop.util.ErrorHandler;

import com.db4o.ObjectContainer;

/**
 * Any action that is passed by the Controller should extend AbstractAction.
 * 
 * @author Andreas
 * 
 */
public abstract class AbstractAction {

	ErrorHandler errorHandler = new ErrorHandler();

	/**
	 * this method represents the entry point to the action.
	 * 
	 * @param request
	 *            the request of the servlet
	 * @param response
	 *            the response of the servlet
	 * @param db
	 *            the open Database connection; it will be closed in this method
	 * @throws IOException 
	 * @throws ServletException 
	 */
	protected abstract void process(HttpServletRequest request,
			HttpServletResponse response, ObjectContainer db) throws ServletException, IOException;

	/**
	 * calls the <code>process</code> method and finally closes the Database
	 * connection. This method is called by the controller.
	 * 
	 * @see AbstractAction#process(HttpServletRequest, HttpServletResponse,
	 *      ObjectContainer)
	 * @param request
	 * @param response
	 * @param db
	 * @throws IOException 
	 * @throws ServletException 
	 */
	public void processAndClose(HttpServletRequest request,
			HttpServletResponse response, ObjectContainer db) throws ServletException, IOException {
		try {
			process(request, response, db);
		} catch (Exception e) {
			errorHandler.toUser("Der Vorgang konnte nicht durchgeführt werden", e);
		} finally {
			if (db != null)
				db.close();
		}
	}
}

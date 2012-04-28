package shop.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.db4o.ObjectContainer;

/**
 * Any action that is passed by the Controller should extend AbstractAction. 
 * @author Andreas
 *
 */
public abstract class AbstractAction {
	/**
	 * this method represents the entry point to the action. 
	 * @param request the request of the servlet
	 * @param response the response of the servlet
	 * @param db the open Database connection; it will be closed in this method
	 */
	protected abstract void process(HttpServletRequest request, HttpServletResponse response, ObjectContainer db);
	
	/**
	 * calls the <code>process</code> method and finally closes the Database connection. This method is called by the controller.
	 * @see AbstractAction#process(HttpServletRequest, HttpServletResponse, ObjectContainer)
	 * @param request
	 * @param response
	 * @param db
	 */
	public void processAndClose(HttpServletRequest request, HttpServletResponse response, ObjectContainer db){
		try{
			process(request, response, db);
		}finally{
			db.close();
		}
	}
}

package shop.actions;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shop.util.ErrorHandler;

import com.db4o.ObjectContainer;

/**
 * Any action that is passed by the Controller should implement IAction.
 * 
 * @author Andreas
 * 
 */
public interface IAction {

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
	public void process(HttpServletRequest request,
			HttpServletResponse response, ObjectContainer db)
			throws ServletException, IOException;
}

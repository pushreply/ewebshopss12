package shop.actions;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.db4o.ObjectContainer;

/**
 * This Action handles LOGOUT by invalidating the user session
 * 
 * @author Hasiholan
 * 
 * 
 */
public class LogoutAction implements IAction{

	@Override
	public void process(HttpServletRequest request,
			HttpServletResponse response, ObjectContainer db)
			throws ServletException, IOException {

			RequestDispatcher disp = request.getRequestDispatcher("/reloadmenu.jsp");
			try {
				
				HttpSession session = request.getSession(false);
				if (session != null) {
					session.invalidate();
					disp.forward(request, response);
				}
			} catch (Exception e) {
				errorHandler.toUser("Etwas mit der Weiterleitung ist schief gelaufen.", e);
			}
		
	}

}

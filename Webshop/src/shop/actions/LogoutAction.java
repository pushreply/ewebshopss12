package shop.actions;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import shop.dao.DAOCustomer;
import shop.dao.GenericDaoImpl;
import shop.dao.IGenericDao;
import shop.dto.DBCustomer;

import com.db4o.ObjectContainer;

/**
 * This Action handles LOGOUT
 * 
 * @author roha0001
 * 
 * 
 */
public class LogoutAction  extends AbstractAction{

	@Override
	protected void process(HttpServletRequest request,
			HttpServletResponse response, ObjectContainer db)
			throws ServletException, IOException {

		if (request.getParameter("logout") != null) {
			RequestDispatcher disp = request.getRequestDispatcher("/login.jsp");
			try {
				
				HttpSession session = request.getSession(false);
				if (session != null) {
					session.invalidate();
					System.out.println("Logging out");
					disp.forward(request, response);
				}
			} catch (Exception e) {
				errorHandler.toUser("Etwas mit der Weiterleitung ist schief gelaufen.", e);
			}
		}
		
		
	}

}

package shop.actions;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import shop.dao.DAOCustomer;

import com.db4o.ObjectContainer;

/**
 * This Action handles the LOGIN Function
 * Successful login will be forwarded to user profile page (profileview.jsp)
 * Possible Errors:
 * - username/password is false
 * - username/password is empty
 * 
 * @author Hasiholan
 * 
 * 
 */

public class LoginAction implements IAction {

	@Override
	public void process(HttpServletRequest request,
			HttpServletResponse response, ObjectContainer db)
			throws ServletException {

		String loginUsername, loginPassword;

		DAOCustomer daoCustomer = new DAOCustomer();
		try {
			
			loginUsername = request.getParameter("username").trim();
			loginPassword = request.getParameter("password").trim();
		
			daoCustomer.isMatchUser(loginUsername, loginPassword, db);
			
			//set session
			HttpSession session = request.getSession(true);
			session.setAttribute("username", loginUsername);
			
			RequestDispatcher disp = request.getRequestDispatcher("/reloadmenu.jsp");
			
			disp.forward(request, response);
		} catch (Exception e) {
			errorHandler.toUser("Benutzername oder Passwort ist falsch.", e);
		}
	}
}

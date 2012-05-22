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
 * This Action handles the LOGIN Function
 * Successful login will be forwarded to user profile page (profileview.jsp)
 * Possible Errors:
 * - username/password is false
 * - username/password is empty
 * 
 * @author roha0001
 * 
 * 
 */

public class LoginAction extends AbstractAction {

	@Override
	protected void process(HttpServletRequest request,
			HttpServletResponse response, ObjectContainer db)
			throws ServletException {

		String loginUsername, loginPassword;
		boolean match = false;
		DBCustomer user = new DBCustomer();
		 DAOCustomer daoCustomer = new DAOCustomer();
		try {
			
			loginUsername = request.getParameter("username").trim();
			loginPassword = request.getParameter("password").trim();
			System.out.println("Login parameters are set: " + loginUsername + ":" + loginPassword);
		
			match = daoCustomer.isMatchUser(loginUsername, loginPassword, db);
			System.out.println("Login OK.");
			
			//get users' data
			for (int i = 0; i < daoCustomer.readUserData(loginUsername, db).getAddresses().size(); i++) {
				user = daoCustomer.readUserData(loginUsername, db);
			}
			
			request.setAttribute("userprofile", user);	
			//set session
			HttpSession session = request.getSession(true);
			session.setAttribute("username", loginUsername);
//			session.setAttribute("userprofile", user);
			RequestDispatcher disp = request.getRequestDispatcher("/profileview.jsp");
			
			disp.forward(request, response);
		} catch (Exception e) {
			errorHandler.toUser("Benutzername oder Passwort ist falsch.", e);
		}
	}
}

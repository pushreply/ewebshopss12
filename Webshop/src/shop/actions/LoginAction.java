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
 * This Action handles LOGIN Function
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

		DAOCustomer customer = new DAOCustomer();

		/*
		 * LOGIN: Login ok -> index.jsp Login error (username/password is
		 * wrong/empty) -> loginerror.jsp -> login.jsp
		 */

		String loginUsername = request.getParameter("username").trim();
		String loginPassword = request.getParameter("password").trim();
		boolean match = false;
		// RequestDispatcher disp = null;

		System.out.println("Login parameters are set: " + loginUsername + ":"
				+ loginPassword);
		try {
			match = customer.isMatchUser(loginUsername, loginPassword, db);

			System.out.println("Comparing data from DB and user input");
			System.out.println("login OK, return to index.jsp");
			HttpSession session = request.getSession(true);
			session.setAttribute("username", loginUsername);
			RequestDispatcher disp = request
					.getRequestDispatcher("controller?action=home");

			disp.forward(request, response);
		} catch (Exception e) {
			errorHandler.toUser("Benutzername oder Passwort ist falsch.", e);
		}
	}
}

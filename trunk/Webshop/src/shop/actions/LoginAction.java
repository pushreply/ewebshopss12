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
 * This Action handles all needs of categories
 * 
 * @author roha0001
 * 
 * 
 */

public class LoginAction extends AbstractAction {

	@Override
	protected void process(HttpServletRequest request, HttpServletResponse response, ObjectContainer db) throws ServletException {

		IGenericDao<DBCustomer> dao = new GenericDaoImpl<DBCustomer>(DBCustomer.class, db);
		DAOCustomer customer = new DAOCustomer();

		/*
		 * LOGIN: Login ok -> index.jsp 
		 * Login error (username/password is wrong/empty) -> loginerror.jsp -> login.jsp
		 */

		String loginUsername = request.getParameter("username");
		String loginPassword = request.getParameter("password");
		boolean match = false;
//		RequestDispatcher disp = null;
		
		System.out.println("Login parameters are set: " + loginUsername + ":"+ loginPassword);
		
		if (!((loginUsername == null || loginUsername.isEmpty()) && !(loginPassword == null || loginPassword.isEmpty()))) {
			
			System.out.println("Username and password is not empty, processing login");
			try {
				match = customer.isMatchUser(loginUsername, loginPassword, db);
			} catch (Exception e) {
				errorHandler.toUser("Beim Anmelden ist ein Fehler aufgetreten, bitte versuchen Sie es später wieder", e);
			}

			System.out.println("Comparing data from DB and user input");
			if (match) {
				System.out.println("login OK, return to index.jsp");
				HttpSession session = request.getSession(true);
				session.setAttribute("username", loginUsername);
				RequestDispatcher disp = request.getRequestDispatcher("controller?action=home");

				try {
					disp.forward(request, response);
				} catch (Exception e) {
					errorHandler.toUser(
							"Etwas mit der Weiterleitung ist schief gelaufen.", e);
				}
			}
			else {
				System.out.println("login failed: user/password is wrong, redo login.");
				RequestDispatcher disp = request.getRequestDispatcher("controller?action=login");
				try {
					disp.forward(request, response);
				} catch (Exception e) {
					errorHandler.toUser(
							"Etwas mit der Weiterleitung ist schief gelaufen.", e);
				}
			}
		}
	}
}

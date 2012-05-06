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
	protected void process(HttpServletRequest request,
			HttpServletResponse response, ObjectContainer db)
			throws ServletException {

		IGenericDao<DBCustomer> dao = new GenericDaoImpl<DBCustomer>(DBCustomer.class, db);
		DAOCustomer customer = new DAOCustomer();

		/*
		 * LOGIN: Login ok -> index.jsp Login error (username/password is
		 * wrong/empty) -> loginerror.jsp -> login.jsp
		 */

		String loginUsername = request.getParameter("username");
		String loginPassword = request.getParameter("password");
		boolean match = false;
		
		System.out.println("Login parameters are set: " + loginUsername + ":"
				+ loginPassword);
		if(request.getParameter("Login")!=null){
			if (!((loginUsername == null || loginUsername.isEmpty()) && !(loginPassword == null || loginPassword
					.isEmpty()))) {
				System.out.println("processing login");
				
				try {
					match = customer.matchUser(loginUsername, loginPassword, db);
				} catch (Exception e) {
					errorHandler.toUser("Beim Anmelden ist ein Fehler aufgetreten, bitte versuchen Sie es später wieder", e);
				}
	
				RequestDispatcher disp;
				System.out.println("comparing data from DB and user input");
				if (match) {
					System.out.println("login OK, return to index.jsp");
					HttpSession session = request.getSession(true);
					session.setAttribute("username", loginUsername);
					disp = request.getRequestDispatcher("/index.jsp");
				} else {
					System.out.println("login failed: user/password is wrong");
					disp = request.getRequestDispatcher("/login.jsp");
				}
				try {
					disp.forward(request, response);
				} catch (Exception e) {
					errorHandler.toUser(
							"Etwas mit der Weiterleitung ist schief gelaufen.", e);
				}
			}
		}
		if(request.getParameter("Logout")!=null)
		{
			try {
				HttpSession session = request.getSession(false);
				session.invalidate();
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
}

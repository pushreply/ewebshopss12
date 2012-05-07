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
 */

public class RegisterAction extends AbstractAction {

	@Override
	protected void process(HttpServletRequest request,
			HttpServletResponse response, ObjectContainer db)
			throws ServletException {

		IGenericDao<DBCustomer> dao = new GenericDaoImpl<DBCustomer>(
				DBCustomer.class, db);
		DAOCustomer newRegistration = new DAOCustomer();

		/*
		 * REGISTRATION: registration -> OK -> index.jsp
		 * registration -> ERROR: "username not available" -> redo registration
		 */
		String newUsername = request.getParameter("username").toLowerCase().trim();
		String newPassword1 = request.getParameter("pw1").trim();
		String newPassword2 = request.getParameter("pw2").trim();
		boolean match = false;

		/*
		 * check whether the proposed username and password is empty..
		 */
		if (!((newUsername == null || newUsername.isEmpty())
				&& !(newPassword1 == null || newPassword1.isEmpty()) && 
				!(newPassword2 == null || newPassword2.isEmpty()))) {
			/*
			 * ..and whether the passwords are identical.
			 */
			if ((newPassword1.equals(newPassword2))) {
				
				System.out.println("Passwords are identical.");
				
				try {
					match = newRegistration.isMatchUser(newUsername, db);
				} catch (Exception e) {
					errorHandler.toUser("Etwas mit der Weiterleitung ist schief gelaufen.", e);
				}
				System.out.println("Comparing proposed registration to DB..");
				if (match) {
					System.out.println("Proposed username is available, prepare new user, set the username and password");
					DBCustomer newUser = new DBCustomer();
					newUser.setUsername(newUsername);
					newUser.setPassword(newPassword1);
					RequestDispatcher disp = request.getRequestDispatcher("/index.jsp");
					try {
						dao.create(newUser);
						System.out.println("New User is created and saved. Back to index.jsp");
						disp.forward(request, response);
					} catch (Exception e) {
						errorHandler.toUser(
								"Fehler: Registrierung ist fehlgeschlagen. Daten konnte nicht gespeichert werden.", e);
					}
				}
				else{
					System.out.println("Registration failed, username is taken.");
					RequestDispatcher disp = request.getRequestDispatcher("/registererror.jsp");
					try {
						disp.forward(request, response);
					} catch (Exception e) {
						errorHandler.toUser(
								"Etwas mit der Weiterleitung ist schief gelaufen.", e);
					}
				}
			}
			else{
				System.out.println("Passwords are not identical!");
				RequestDispatcher disp = request.getRequestDispatcher("/registererror.jsp");
				try {
					disp.forward(request, response);
				} catch (Exception e) {
					errorHandler.toUser(
							"Etwas mit der Weiterleitung ist schief gelaufen.", e);
				}
			}	
		}
		else{
			System.out.println("Username/Password is empty!");
			RequestDispatcher disp = request.getRequestDispatcher("/registererror.jsp");
			try {
				disp.forward(request, response);
			} catch (Exception e) {
				errorHandler.toUser(
						"Etwas mit der Weiterleitung ist schief gelaufen.", e);
			}
		}
	}
}

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

public class RegisterAction extends AbstractAction {

	@Override
	protected void process(HttpServletRequest request,
			HttpServletResponse response, ObjectContainer db) throws ServletException {

		IGenericDao<DBCustomer> dao = new GenericDaoImpl<DBCustomer>(DBCustomer.class, db);
		DAOCustomer newRegistration = new DAOCustomer();

		/*
		 * REGISTRATION: registration -> OK -> index.jsp registration -> ERROR:
		 * username not available -> registration
		 */
		String newUsername = request.getParameter("username").toLowerCase().trim();
		String newPassword1 = request.getParameter("pw1").trim();
		String newPassword2 = request.getParameter("pw2").trim();
		boolean match = false;

		// check whether the username and password is empty
		if (!((newUsername == null || newUsername.isEmpty())
				&& !(newPassword1 == null || newPassword1.isEmpty())
				&& !(newPassword2 == null || newPassword2.isEmpty()) && (newPassword1 == newPassword2))) {
			System.out.println("conditions fulfilled.");
			
			try {
				match = newRegistration.matchUser(newUsername, db);
			} catch (Exception e) {
					errorHandler.toUser("Etwas mit der Weiterleitung ist schief gelaufen.", e);
			}
			
			if(!match){
				DBCustomer newUser = new DBCustomer();
				newUser.setUsername(newUsername);
				newUser.setPassword(newPassword1);
				try {
					dao.create(new DBCustomer());
				} catch (Exception e) {
						errorHandler.toUser("Fehler: Registrierung ist fehlgeschlagen.", e);
				}

			}
			System.out.println("data is saved.");

			// username is registered successfully, forward page to index.jsp
			RequestDispatcher disp = request.getRequestDispatcher("/index.jsp");
			try {
				disp.forward(request, response);
			} catch (Exception e) {
				errorHandler.toUser(
						"Etwas mit der Weiterleitung ist schief gelaufen.", e);
			}

		}
	}

}

package shop.actions;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import shop.dao.DAOCustomer;
import shop.dao.GenericDaoImpl;
import shop.dao.IGenericDao;
import shop.dto.DBAddress;
import shop.dto.DBCustomer;

import com.db4o.ObjectContainer;
import com.db4o.ext.Db4oException;

/**
 * This Action handles the user registration
 * 
 * Possible Errors: 
 * - username/password is empty 
 * - username is not available 
 * - passwords are not identical 
 * -- cannot save to database 
 * -- page is not available
 * 
 * @author Hasiholan
 * 
 */

public class RegisterAction implements IAction{

	@Override
	public void process(HttpServletRequest request,
			HttpServletResponse response, ObjectContainer db)
			throws ServletException {

		IGenericDao<DBCustomer> daoCustomer = new GenericDaoImpl<DBCustomer>(DBCustomer.class, db);
		IGenericDao<DBAddress> daoAddress = new GenericDaoImpl<DBAddress>(DBAddress.class, db);
		DAOCustomer newRegistration = new DAOCustomer();

		String newUsername, newPassword1, newPassword2, gender, firstName, lastName, street, country;
		boolean available;

		newUsername = request.getParameter("username").trim();
		newPassword1 = request.getParameter("pw1").trim();
		newPassword2 = request.getParameter("pw2").trim();
		gender = request.getParameter("gender");
		firstName = request.getParameter("fname");
		lastName = request.getParameter("lname");
		street = request.getParameter("street");
		country = request.getParameter("country");
		
		/*
		 * check whether the username or password is empty
		 */
		if(!(newUsername == null||newUsername.isEmpty()) 
				&& !(newPassword1 == null|| newPassword1.isEmpty())
				&& !(newPassword2 == null|| newPassword2.isEmpty())) {

			/*
			 * check whether the passwords are identical.
			 */
			if (newPassword1.equals(newPassword2)) {
				available = newRegistration.isMatchUser(newUsername, db);
				/*
				 * check if the username is available
				 * insert "delivery" as "art" of address as the default value
				 */
				if (available == true) {
					DBAddress address = new DBAddress(street, country, firstName, lastName, gender, "delivery");
					LinkedList<DBAddress> useraddress = new LinkedList<DBAddress>();
					useraddress.add(address);
					DBCustomer user = new DBCustomer(newUsername, newPassword1, useraddress);
					RequestDispatcher disp = request.getRequestDispatcher("/profileview.jsp");
					try {
						daoAddress.create(address);
						daoCustomer.create(user); 
						request.setAttribute("userprofile", user);
						HttpSession session = request.getSession(true);
						session.setAttribute("username", user.getUsername());

						disp.forward(request, response);
					} catch (Db4oException e) {
						errorHandler.toUser("Database error, data could not be saved.", e);
					} catch (IOException e) {
						errorHandler.toUser("Etwas mit der Weiterleitung ist schief gelaufen.", e);
					}
				} else {
//					System.out.println("Registration failed, username is taken.");
					RequestDispatcher disp = request.getRequestDispatcher("/registererror.jsp");
					try {
						disp.forward(request, response);
					} catch (Exception e) {
						errorHandler.toUser("Etwas mit der Weiterleitung ist schief gelaufen.", e);
					}
				}
			} else {
//				System.out.println("Passwords are not identical!");
				RequestDispatcher disp = request.getRequestDispatcher("/registererror.jsp");
				try {
					disp.forward(request, response);
				} catch (Exception e) {
					errorHandler.toUser("Etwas mit der Weiterleitung ist schief gelaufen.", e);
				}
			}
		} else {
//			System.out.println("Username/Password is empty!");
			RequestDispatcher disp = request.getRequestDispatcher("/registererror.jsp");
			try {
				disp.forward(request, response);
			} catch (Exception e) {
				errorHandler.toUser("Etwas mit der Weiterleitung ist schief gelaufen.", e);
			}
		}
	}
}

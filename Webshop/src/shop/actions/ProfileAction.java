package shop.actions;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shop.dao.DAOCustomer;
import shop.dto.DBCustomer;

import com.db4o.ObjectContainer;

/**
 * This action handles User profile
 * Function: show profile: first-lastname and addresses.
 * 
 * @author Hasiholan
 * 
 */
public class ProfileAction implements IAction{

	private DBCustomer user;

	@Override
	public void process(HttpServletRequest request,
			HttpServletResponse response, ObjectContainer db)
			throws ServletException, IOException {

		try {
			DAOCustomer daoCustomer = new DAOCustomer();

			String loginUsername = (String) request.getSession().getAttribute("username");

			RequestDispatcher disp = request.getRequestDispatcher("/profileview.jsp");
			// get users' data
			if (!loginUsername.isEmpty()) {
				for (int i = 0; i <= daoCustomer.readUserData(loginUsername, db).getAddresses().size(); i++) {
					user = daoCustomer.readUserData(loginUsername, db);
				}

				request.setAttribute("userprofile", user);
				disp.forward(request, response);
			}
		} catch (Exception e) {
			errorHandler
					.toUser("Beim Anmelden ist ein Fehler aufgetreten, bitte versuchen Sie es sp�ter wieder",
							e);
		}

	}

}

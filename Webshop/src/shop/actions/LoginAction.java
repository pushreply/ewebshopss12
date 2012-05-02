package shop.actions;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
			HttpServletResponse response, ObjectContainer db) {

		IGenericDao<DBCustomer> dao = new GenericDaoImpl<DBCustomer>(
				DBCustomer.class, db);

		/*
		 * LOGIN: Login ok -> index.jsp Login error (username/password is
		 * wrong/empty) -> loginerror.jsp -> login.jsp
		 */

		String loginUsername = null;
		String loginPassword = null;

		if (!((loginUsername == null || loginUsername.isEmpty()) && 
				!(loginPassword == null || loginPassword.isEmpty()))) 
		{
			System.out.println("processing login");
			DBCustomer LoginUser = new DBCustomer();
			LoginUser.setUsername(loginUsername);

			LoginUser.setPassword(loginPassword);
			DBCustomer ExistedUser = dao.read(request
					.getParameter("identifier"));

			System.out.println("comparing data from DB and user input");
			if (!(LoginUser.equals(ExistedUser))) {
				System.out.println("login OK, go to index.jsp");
				RequestDispatcher disp = request
						.getRequestDispatcher("/index.jsp");
				HttpSession session = request.getSession(true);
				session.setAttribute("username", loginUsername);

				try {
					disp.forward(request, response);
				} catch (ServletException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				System.out.println("login failed: user/password is wrong");
				RequestDispatcher disp = request
						.getRequestDispatcher("/login.jsp");
				try {
					disp.forward(request, response);
				} catch (ServletException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}

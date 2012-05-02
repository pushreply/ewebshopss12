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

public class RegisterAction extends AbstractAction{

	@Override
	protected void process(HttpServletRequest request,
			HttpServletResponse response, ObjectContainer db) {
		// TODO Auto-generated method stub
		IGenericDao<DBCustomer> dao = new GenericDaoImpl<DBCustomer>(DBCustomer.class, db);
		
		/*
		 * REGISTRATION:
		 * registration -> OK -> index.jsp
		 * registration -> ERROR: username not available -> registration
		 */
		
		String newUsername = request.getParameter("username").toLowerCase().trim(); 
		String newPassword1 = request.getParameter("pw1").trim();
		String newPassword2 = request.getParameter("pw2").trim();
		
		
		//check whether the username and password is empty
		if (!((newUsername == null||newUsername.isEmpty()) && 
				!(newPassword1 == null || newPassword1.isEmpty()) &&
				!(newPassword2 == null || newPassword2.isEmpty()) &&
				(newPassword1==newPassword2)
			))
		{
			DBCustomer newuser = new DBCustomer();
			
			//set newPassword1 as new user password
			newuser.setUsername(newUsername);
			newuser.setPassword(newPassword1); 
			
			DBCustomer user =  dao.read(request.getParameter("identifier"));
		
			//save the username
			if(!(user.equals(newuser))){
				dao.create(newuser);
			}
			else{
				//if username is not available
				RequestDispatcher disp = request.getRequestDispatcher("/registererror.jsp");
				try {
					disp.forward(request, response);
				} catch (ServletException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			//username is registered successfully, forward page to index.jsp
			RequestDispatcher disp = request.getRequestDispatcher("/index.jsp");
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

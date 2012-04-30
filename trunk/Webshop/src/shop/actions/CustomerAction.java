package shop.actions;

import java.io.IOException;
import java.util.List;

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
 */

public class CustomerAction extends AbstractAction{

	@Override
	protected void process(HttpServletRequest request, HttpServletResponse response, ObjectContainer db) {
		
		IGenericDao<DBCustomer> dao = new GenericDaoImpl<DBCustomer>(DBCustomer.class, db);
		
		// add user
		String addUsername = null;
		String addPassword = null;
		
		try {
			addUsername = request.getParameter("username").trim();
			addPassword = request.getParameter("password");
			//String encryptedPassword = toMD5(addPassword);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//REGISTRATION
		//check whether the username and password is not empty
		if (!((addUsername == null||addUsername.isEmpty()) && !(addPassword == null || addPassword.isEmpty()))){
			
			DBCustomer newcustomer = new DBCustomer();
			newcustomer.setUsername(addUsername);
			newcustomer.setPassword(addPassword); //plain text! It should be saved and compared in MD5/encrypted.
			DBCustomer oldcustomer =  dao.read(addUsername);
			
			//save the username, if it is available (selected username does not exist in DB)
			if(!(oldcustomer.equals(newcustomer))){
				dao.create(newcustomer);
			}
			else{
				//if username is not available, forward page to error registration page "registererror.jsp"
				RequestDispatcher disp = request.getRequestDispatcher("/registererror.jsp");
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
		
		//LOGIN
		else if(!((addUsername == null||addUsername.isEmpty()) && !(addPassword == null || addPassword.isEmpty()))){
			System.out.println("processing login");
			DBCustomer LoginUser = new DBCustomer();
			LoginUser.setUsername(addUsername);
			
			LoginUser.setPassword(addPassword);
			DBCustomer ExistedUser = dao.read(addUsername);
			
			if(!(LoginUser.equals(ExistedUser))){ 
				System.out.println("login compared");
				RequestDispatcher disp = request.getRequestDispatcher("/login.jsp");
				HttpSession session = request.getSession(true);
				//session
				try {
					disp.forward(request, response);
				} catch (ServletException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			else {
				RequestDispatcher disp = request.getRequestDispatcher("/login.jsp");
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
		else{
			RequestDispatcher disp = request.getRequestDispatcher("/index.jsp");
			try {
				disp.forward(request, response);
			} catch (ServletException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		
	}

}

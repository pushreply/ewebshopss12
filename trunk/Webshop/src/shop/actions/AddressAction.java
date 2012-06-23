package shop.actions;

import java.io.IOException;

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
 * This Action handles customer's (delivery) addresses.
 * A customer can have more than one address and 
 * the item could be delivered to other person.
 * 
 * The customer has to provide an address at the order process.
 * 
 * Functionality:
 * + add
 * + delete
 * 
 * @param street, country, art, firstName, lastName
 * 
 * @author Hasiholan
 * 
 */
public class AddressAction implements IAction {

	@Override
	public void process(HttpServletRequest request,
			HttpServletResponse response, ObjectContainer db)
			throws ServletException, IOException {

		IGenericDao<DBAddress> dao = new GenericDaoImpl<DBAddress>(DBAddress.class, db);
		IGenericDao<DBCustomer> genericDaoCustomer = new GenericDaoImpl<DBCustomer>(DBCustomer.class, db);
		
		DAOCustomer daoCustomer = new DAOCustomer();
		String street, country, art, firstName, lastName, gender;
		String username = (String) request.getSession().getAttribute("username"); //aktuelle username (in session)
		DBCustomer user = new DBCustomer();
		user = daoCustomer.findUser(username, db);
		
		street = request.getParameter("street"); 
		country = request.getParameter("country");
		firstName = request.getParameter("firstName");
		lastName = request.getParameter("lastName");
		gender = request.getParameter("gender");
		art = request.getParameter("art");
		
		HttpSession session = request.getSession(true);
		session.getAttribute("username");
		
		/*
		 * adding new address, or updating an edited address 
		 */
		if (request.getParameter("address")!=null &&
				request.getParameter("address").equals("addnew")) 
		{
			try {				
				DBAddress address = new DBAddress(street, country, firstName, lastName, gender, art);
				user.getAddresses().add(address);
				dao.create(address);
				genericDaoCustomer.update(user);
				
				request.setAttribute("userprofile", user);
				RequestDispatcher disp = request.getRequestDispatcher("controller?action=customer&show=profile");  
				disp.forward(request, response);
			} catch (Db4oException e) {
				errorHandler.toUser("Beim Speichern der Adresse ist ein Fehler aufgetreten.", e);
			} catch (Exception e) {
				errorHandler.toUser("Etwas mit der Weiterleitung ist schief gelaufen.", e);
			}	
		} 

		/*
		 * delete address, forward to editing page: show the actual address
		 */
		else if (request.getParameter("delete")!=null && !request.getParameter("delete").isEmpty())
		{
			try{				
				user.getAddresses().remove(dao.read(request.getParameter("delete")));
				dao.delete(request.getParameter("delete"));
				genericDaoCustomer.update(user);
				request.setAttribute("userprofile", user);
				RequestDispatcher disp = request.getRequestDispatcher("controller?action=customer&show=profile");  
				disp.forward(request, response);
			}catch (Exception e) {
				errorHandler.toUser("Etwas mit der Weiterleitung ist schief gelaufen", e);
			}
		}
		
		else if(request.getParameter("show")!=null){
			try {
				session.getAttribute("username");
				for (int i = 0; i < daoCustomer.readUserData(username, db).getAddresses().size(); i++) {
					user = daoCustomer.readUserData(username, db);
				}
				request.setAttribute("userprofile", user);	
				RequestDispatcher disp = request.getRequestDispatcher("/profileview.jsp");  
				disp.forward(request, response);
			} catch (Exception e) {
				errorHandler.toUser("Etwas mit der Weiterleitung ist schief gelaufen", e);
			}		
		}
	}
}

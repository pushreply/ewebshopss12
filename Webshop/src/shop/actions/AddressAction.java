package shop.actions;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

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
import shop.dto.DBKeyword;

import com.db4o.ObjectContainer;
import com.db4o.ext.DatabaseClosedException;
import com.db4o.ext.Db4oException;
import com.db4o.ext.Db4oIOException;

/**
 * This Action handles customer's (delivery) addresses.
 * A customer can have more than one address and 
 * the item could be delivered to other person.
 * 
 * The customer has to provide an address at the order process.
 * 
 * Functionality:
 * + show 
 * + update 
 * + delete
 * 
 * @param street, country, art, firstName, lastName
 * 
 * @author roha0001
 * 
 */
public class AddressAction extends AbstractAction {

	

	@Override
	protected void process(HttpServletRequest request,
			HttpServletResponse response, ObjectContainer db)
			throws ServletException, IOException {

		IGenericDao<DBAddress> dao = new GenericDaoImpl<DBAddress>(DBAddress.class, db);
		
		/*
		 * load all addresses that belong to the username
		 */
//		loadAddress(request, dao);
		
		String street, country, art, firstName, lastName, gender;
		String username = (String) request.getSession().getAttribute("username");
		
		street = request.getParameter("street"); 
		country = request.getParameter("country");
		firstName = request.getParameter("firstName");
		lastName = request.getParameter("lastName");
		gender = request.getParameter("gender");
		art = request.getParameter("art");
		
		HttpSession session = request.getSession(true);
		session.getAttribute("username");
		
		DAOCustomer daoCustomer = new DAOCustomer();
		DBCustomer user = new DBCustomer();
		LinkedList<DBAddress> addresses = new LinkedList<DBAddress>();
		/*
		 * adding new address, or updating an edited address 
		 */
		if (request.getParameter("address")!=null) {
			try {
				System.out.println("adding new address");
				
				System.out.println(street+country+firstName+lastName+gender+art);
				DBAddress address = new DBAddress(street, country, firstName, lastName, gender, art);
				addresses.add(address);
				user.setUsername(username);
				user.setAddresses(addresses);
				
				dao.create(address);
				
				request.setAttribute("userprofile", user);
				RequestDispatcher disp = request.getRequestDispatcher("controller?action=customer&show=profile");  
				disp.forward(request, response);
			} catch (Db4oException e) {
				errorHandler.toUser("Beim Speichern der Adresse ist ein Fehler aufgetreten.", e);
			} catch (Exception e) {
				errorHandler.toUser("Etwas mit der Weiterleitung ist schief gelaufen.", e);
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
		
		/*
		 * Edit actual address, forward to editing page: show the actual address and the editing form 
		 */
//		else if (request.getParameter("edit").equals("edit"))
//		{
//			try{
//				RequestDispatcher disp = request.getRequestDispatcher("controller?action=address&edit=true");  //"Edit" address
//				disp.forward(request, response);
//			}catch (Exception e) {
//				errorHandler.toUser("Etwas mit der Weiterleitung ist schief gelaufen", e);
//			}
//		}
	}

	/*
	 * read all address from the customer
	 */
	public void loadAddress(HttpServletRequest request, IGenericDao<DBAddress> dao) throws ServletException {
		List<DBAddress> adr = null;
		try {
			adr = dao.readAll();
		} catch (Exception e) {
			errorHandler.toUser("Beim Lesen des Schlüsselwort ist ein Fehler aufgetreten, bitte versuchen Sie es später wieder", e);
		}
		request.setAttribute("address", adr);
	}
}

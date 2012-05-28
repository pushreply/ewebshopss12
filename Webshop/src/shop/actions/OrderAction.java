/**
 * @author mukunzi
 */

package shop.actions;

import java.util.LinkedList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shop.dao.GenericDaoImpl;
import shop.dao.IGenericDao;
import shop.dto.DBAlbum;
import com.db4o.ObjectContainer;

public class OrderAction extends AbstractAction {

	@Override
	public void process(HttpServletRequest request,
			HttpServletResponse response, ObjectContainer db)
			throws ServletException {

		RequestDispatcher disp = null;

		IGenericDao<DBAlbum> daoAlbum = new GenericDaoImpl<DBAlbum>(
				DBAlbum.class, db);
        
		//Artikel in Warenkorb legen
		if (request.getParameter("PutAlbumInSessionID") != null) {

			DBAlbum album = new DBAlbum();

			try {
				album = daoAlbum.read(request.getParameter("PutAlbumInSessionID"));
				
				if(album.getAmount() <=5)
				{
					album.setAmount(30);
					daoAlbum.update(album);
				}
				
				album.setAmount(album.getAmount() - Integer.parseInt(request.getParameter("anzahl")));
				daoAlbum.update(album);

				//@SuppressWarnings("unchecked")
				album.setAmount(Integer.parseInt(request.getParameter("anzahl")));
				LinkedList<DBAlbum> sessionAlbumen = (LinkedList<DBAlbum>) request.getSession().getAttribute("orderedAlben");
				sessionAlbumen.add(album);
				//sessionAlbumen.
					
				request.getSession().setAttribute("orderedAlben", sessionAlbumen);
					
				disp = request.getRequestDispatcher("/controller?action=album&show=all");

			} catch (Exception e) {
				errorHandler.toUser("Beim Loaden eines Albums ist ein Fehler aufgetretten Bitte versuchen sie es später wieder",
								e);
			}

		}
		
		
		//Gewünschter Artikel aus warenkorb entfernen
		if (request.getParameter("DeleteAlbumFromSessionID") != null) {


			try {
				
					LinkedList<DBAlbum> sessionAlben = new LinkedList<DBAlbum>();
					sessionAlben = (LinkedList<DBAlbum>) request.getSession().getAttribute("orderedAlben");
					
					for (DBAlbum album : sessionAlben) {
						if((album.getIdentifier().toString()).equals(request.getParameter("DeleteAlbumFromSessionID")))
						{
							 sessionAlben.remove(album);
						}
						
					}

					request.getSession().setAttribute("orderedAlben", sessionAlben);
					
					disp = request.getRequestDispatcher("/bestellungsUebersicht.jsp");
                    System.out.println("bestellungsUebersicht -seite aufgerufen!");
				 
			} catch (Exception e) {
				errorHandler.toUser("Beim Löschen des Albums ist ein Fehler aufgetretten Bitte versuchen sie es später wieder",
								e);
			}

		}
		
		
		//Artikel in warenkorn zum Anzeigen vorbereiten
		if (request.getParameter("warenkorb") != null) {


			try {
				
					LinkedList<DBAlbum> sessionAlben = new LinkedList<DBAlbum>();
					sessionAlben = (LinkedList<DBAlbum>) request.getSession().getAttribute("orderedAlben");
					float gesammtpreis = 0;
					
					for (DBAlbum album : sessionAlben) {
											
                         gesammtpreis += album.getPrice() * album.getAmount();					
						
					}
					
					request.setAttribute("gesammtpreis", gesammtpreis);
					
					disp = request.getRequestDispatcher("/bestellungsUebersicht.jsp");
                    System.out.println("bestellungsUebersicht -seite aufgerufen!");
				 
			} catch (Exception e) {
				errorHandler.toUser("Beim Löschen des Albums ist ein Fehler aufgetretten Bitte versuchen sie es später wieder",
								e);
			}

		}
		
		try {
			 System.out.println("auch hier");
			disp.forward(request, response);
		} catch (Exception e) {
			errorHandler.toUser(
					"Etwas mit der Weiterleitung ist schief gelaufen", e);
		}
		
		
	}
	

}

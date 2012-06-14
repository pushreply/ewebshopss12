/**
 * @author mukunzi
 * @author Benjamin
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

public class OrderAction implements IAction {

	@SuppressWarnings("unchecked")
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
				
				if(album.getAmount() < Integer.parseInt(request.getParameter("anzahl")))
				{					
					String meldung = "Leider zu wenig Alben vorhanden";
					request.setAttribute("meldung", meldung);
					
					disp = request.getRequestDispatcher("/bestellungError.jsp");
					disp.forward(request, response);
					return;
				}
				
				 else if(album.getAmount() <=5)
					{
						album.setAmount(30);
						daoAlbum.update(album);
					}				
                
				//@SuppressWarnings("unchecked")
				int anzahl = Integer.parseInt(request.getParameter("anzahl"));
				album.setAmount(anzahl);
				LinkedList<DBAlbum> sessionAlben = (LinkedList<DBAlbum>) request.getSession().getAttribute("orderedAlben");
				boolean found = false;
				for (DBAlbum dbAlbum : sessionAlben) {
					if(!found && dbAlbum.getAlbumTitel().equals(album.getAlbumTitel())){
						dbAlbum.setAmount(dbAlbum.getAmount() + album.getAmount());	
						found = true;
					}
				}
				if (!found) {
					sessionAlben.add(album);		        
				}
				
				request.getSession().setAttribute("orderedAlben", sessionAlben);

					
				disp = request.getRequestDispatcher("/controller?action=album&show=all");

			} catch (Exception e) {
				errorHandler.toUser("Beim Laden eines Albums ist ein Fehler aufgetretten Bitte versuchen sie es später wieder",
								e);
			}

		}
		
		
		//Weitere Alben in Warenkorb legen
				if (request.getParameter("weiterealben") != null) {

					try {
							
						disp = request.getRequestDispatcher("/controller?action=album&show=all");

					} catch (Exception e) {
						errorHandler.toUser("Bei der Bestellung weiteren Alben ist ein Fehler aufgetretten, Bitte versuchen sie es später wieder",
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
					
                    float gesammtpreis = 0;
					
					for (DBAlbum album : sessionAlben) {
											
                         gesammtpreis += album.getPrice() * album.getAmount();					
						
					}
					gesammtpreis = (float) (Math.round(gesammtpreis * 100) / 100.0);
					request.setAttribute("gesammtpreis", gesammtpreis);
					
					request.getSession().setAttribute("orderedAlben", sessionAlben);
					
					disp = request.getRequestDispatcher("/bestellungsUebersicht.jsp");

				 
			} catch (Exception e) {
				errorHandler.toUser("Beim Löschen des Albums ist ein Fehler aufgetretten Bitte versuchen sie es später wieder",
								e);
			}

		}
		
		
		//Artikel in warenkorb zum Anzeigen vorbereiten
		if (request.getParameter("warenkorb") != null) {


			try {
				
					LinkedList<DBAlbum> sessionAlben = new LinkedList<DBAlbum>();
					sessionAlben = (LinkedList<DBAlbum>) request.getSession().getAttribute("orderedAlben");
					float gesammtpreis = 0;
					
					for (DBAlbum album : sessionAlben) {
											
                         gesammtpreis += album.getPrice() * album.getAmount();					
						
					}
					gesammtpreis = (float) (Math.round(gesammtpreis * 100) / 100.0);
					request.setAttribute("gesammtpreis", gesammtpreis);
					
					disp = request.getRequestDispatcher("/bestellungsUebersicht.jsp");

				 
			} catch (Exception e) {
				errorHandler.toUser("Beim Anzeigen des gewünschten Albums ist ein Fehler aufgetretten, Bitte versuchen sie es später wieder",
								e);
			}

		}
		
		
		//Bestellen
		if (request.getParameter("order") != null) {

			try {
				LinkedList<DBAlbum> alben = (LinkedList<DBAlbum>) request.getSession().getAttribute("orderedAlben");
				
				for(DBAlbum albumsession : alben){
				   
					DBAlbum album = daoAlbum.read(albumsession.getIdentifier().toString());
				  	
					album.setAmount(album.getAmount() - albumsession.getAmount());
					daoAlbum.update(album);	
					
				}	
				
				//session leeren
				alben.remove();
				request.getSession().setAttribute("orderedAlben", alben);
				
				disp = request.getRequestDispatcher("/bestellungAufgeben.jsp");

				 
			} catch (Exception e) {
				errorHandler.toUser("Beim Bestellen ist ein Fehler aufgetretten Bitte versuchen sie es später wieder",
								e);
			}

		}
		
		
		try {
			disp.forward(request, response);
		} catch (Exception e) {
			errorHandler.toUser(
					"Etwas mit der Weiterleitung ist schief gelaufen", e);
		}
		
		
	}
	

}

package shop.actions;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shop.dao.GenericDaoImpl;
import shop.dao.IGenericDao;
import shop.dto.DBAlbum;
import shop.dto.DBCategory;
import shop.dto.DBKeyword;
import shop.dto.DBTrack;

import com.db4o.ObjectContainer;

/**
 * @author Mukunzi
 * @author Sergej Schneider
 * 
 */

public class AlbumAction extends AbstractAction {

	@Override
	protected void process(HttpServletRequest request,
			HttpServletResponse response, ObjectContainer db)
			throws ServletException {
		
		RequestDispatcher disp = null;

		IGenericDao<DBAlbum> dao = new GenericDaoImpl<DBAlbum>(DBAlbum.class,
				db);
		IGenericDao<DBCategory> daoc = new GenericDaoImpl<DBCategory>(
				DBCategory.class, db);
		IGenericDao<DBKeyword> daok = new GenericDaoImpl<DBKeyword>(
				DBKeyword.class, db);

		if (request.getParameter("delete") != null) {
			DBAlbum dbalbum = null;
			IGenericDao<DBTrack> daot = new GenericDaoImpl<DBTrack>(
					DBTrack.class, db);
			try {

				dbalbum = dao.read(request.getParameter("delete"));
				System.out.println(dbalbum.getTracks().size());
				for (int i = 0; i < dbalbum.getTracks().size(); i++) {
					daot.delete(dbalbum.getTracks().get(i));
				}

				dao.delete(request.getParameter("delete"));

			} catch (Exception e) {
				errorHandler
						.toUser("Beim Löschen des Album ist ein Fehler aufgetreten, bitte versuchen Sie es später wieder",
								e);
			}
			try {
				request.setAttribute("Alben", dao.readAll());
			} catch (Exception e) {
				errorHandler
						.toUser("Beim Laden der MP3 ist ein Fehler aufgetreten, bitte versuchen Sie es später wieder",
								e);
			}
			disp = request.getRequestDispatcher("/allealbenanzeigen.jsp");
		}

		if (request.getParameter("upload") != null) {
			List<DBCategory> categories = null;
			List<DBKeyword> keywordies = null;
			try {
				categories = daoc.readAll();
				keywordies = daok.readAll();

			} catch (Exception e) {
				errorHandler
						.toUser("Beim Laden der Kategorie und Keyword ist ein Fehler aufgetreten, bitte versuchen Sie es später wieder",
								e);
			}
			request.setAttribute("keywordies", keywordies);
			request.setAttribute("categories", categories);
			disp = request.getRequestDispatcher("/Albumanlegen.jsp");

		}

		// Alle Alben anzegen
		if (request.getParameter("show") != null) {
			if (request.getParameter("show").equals("all")) {
				try {
					request.setAttribute("Alben", dao.readAll());
				} catch (Exception e) {
					errorHandler
							.toUser("Beim Laden aller MP3 ist ein Fehler aufgetreten, bitte versuchen Sie es später wieder",
									e);
				}
				disp = request.getRequestDispatcher("/allealbenanzeigen.jsp");
			}

			// Album anzeigen
			else {
				try {
					request.setAttribute("album",
							dao.read(request.getParameter("show")));
					
				} catch (Exception e) {
					errorHandler
							.toUser("Beim Laden der MP3 ist ein Fehler aufgetreten, bitte versuchen Sie es später wieder",
									e);
				}
				disp = request.getRequestDispatcher("/albumanzeigen.jsp");
			}
		}

		// Album in Felder
		else if ((request.getParameter("changeAlbumInfo") != null)) {
			List<DBCategory> categories = null;
			List<DBKeyword> keywordies = null;
			DBAlbum album = null;
			Map<DBKeyword,Boolean> checkedKeywords = new HashMap<DBKeyword, Boolean>();
			Map<DBCategory,Boolean> checkedCategory = new HashMap<DBCategory, Boolean>();
			
			try {
				album = dao.read(request.getParameter("changeAlbumInfo"));
				categories = daoc.readAll();
				keywordies = daok.readAll();
				request.setAttribute("album",album);
				for (DBKeyword dbKeyword : keywordies) {
					checkedKeywords.put(dbKeyword, album.getKeywords().contains(dbKeyword));
				}
				
				for (DBCategory dbCategory : categories)
				{
					checkedCategory.put(dbCategory, album.getCategories().contains(dbCategory));
				}

			} catch (Exception e) {
				errorHandler
						.toUser("Beim Laden der Kategorie und Keyword ist ein Fehler aufgetreten, bitte versuchen Sie es später wieder",
								e);
			}
			request.setAttribute("keywordies", checkedKeywords);
			request.setAttribute("categories", checkedCategory);

			disp = request.getRequestDispatcher("/albumBearbeiten.jsp");
		}

		// Album editieren
		/** This feature album edit and upload forwarding to another cover. **/
		
		else if ((request.getParameter("updateAlbum") != null)) {
			try {
				String identifier = request.getParameter("updateAlbum");
				DBAlbum album = dao.read(identifier);
				LinkedList<DBKeyword> newKeywords = new LinkedList<DBKeyword>();
				LinkedList<DBCategory> newCategory = new LinkedList<DBCategory>();
				
				String[] keywordValues = request.getParameterValues("keyword");
				for (String string : keywordValues) {
					newKeywords.add(daok.read(string));
				}
				String[] categoryValues = request.getParameterValues("category");
				for (String id : categoryValues) {
					newCategory.add(daoc.read(id));
				}
				
				String titel = request.getParameter("titel");
				String artist = request.getParameter("artist");
				int diskAnzahl = Integer.parseInt((request
						.getParameter("diskAnzahl")));
				Double preis = Double.parseDouble((request
						.getParameter("preis")));
				int anzahl = Integer.parseInt(request.getParameter("albumAnzahl"));
				int trackAnzahl = Integer.parseInt(request
						.getParameter("trackAnzahl"));
				String label = request.getParameter("label");
				album.setAlbumTitel(titel);
				album.setArtist(artist);
				album.setNumberOfDisks(diskAnzahl);
				album.setPrice(preis);
				album.setAmount(anzahl);
				album.setNumberOfTracks(trackAnzahl);
				album.setLabel(label);
				album.setKeywords(newKeywords);
				album.setCategories(newCategory);
				dao.update(album);

				LinkedList<DBAlbum> ldbalbum = new LinkedList<DBAlbum>();
				
				ldbalbum.add(album);
				
				// Album zur Kategorie hinzufuegen
				for(int i = 0; i < newCategory.size(); i++)
				{	
					DBCategory temp = newCategory.get(i);
					temp.setAlbums(ldbalbum);
				    daoc.update(newCategory.get(i));
				}
				
				// Album zur Schlagwoerter hinzufuegen
				for(int i = 0; i < newKeywords.size(); i++)
				{
					DBKeyword temp = newKeywords.get(i);
					temp.setAlbums(ldbalbum);
					daok.update(newKeywords.get(i));
				}
				
				
				request.setAttribute("album", dao.read(identifier));
				System.out.println(request.getParameter("coverchange"));
				if(request.getParameter("coverchange") != null && "Cover ändern".equals(request.getParameter("coverchange")))
				{
					disp = request.getRequestDispatcher("/coverchange.jsp");
				}
				else
				{
					disp = request.getRequestDispatcher("/albumanzeigen.jsp");
				}
			} catch (Exception e) {
				errorHandler
						.toUser("Beim Laden der MP3 ist ein Fehler aufgetreten, bitte versuchen Sie es später wieder",
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

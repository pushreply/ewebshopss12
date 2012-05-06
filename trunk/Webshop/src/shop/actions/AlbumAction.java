package shop.actions;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shop.dao.GenericDaoImpl;
import shop.dao.IGenericDao;
import shop.dto.DBAlbum;

import com.db4o.ObjectContainer;



/**
 * @author Mukunzi
 *
 */

public class AlbumAction extends AbstractAction{

	@Override
	protected void process(HttpServletRequest request,
			HttpServletResponse response, ObjectContainer db) throws ServletException {
		RequestDispatcher disp = null;
		
		IGenericDao<DBAlbum> dao = new GenericDaoImpl<DBAlbum>(DBAlbum.class, db);
		
		
		if (request.getParameter("upload") != null) {
			disp = request
					.getRequestDispatcher("/trackhinzufuegen.jsp");
			
		}
		
		//Alle Alben anzegen
		if (request.getParameter("alle") != null) {
				try {
					request.setAttribute("Alben", dao.readAll());
				} catch (Exception e) {
					errorHandler.toUser("Beim Laden der MP3 ist ein Fehler aufgetreten, bitte versuchen Sie es sp�ter wieder", e);
				} 
			disp = request.getRequestDispatcher("/allealbenanzeigen.jsp");
		}
		
		//Album anzeigen
		else if ((request.getParameter("identifier") != null)) {
			try {
				request.setAttribute("album",dao.read(request.getParameter("identifier")));
				
			} catch (Exception e) {
				errorHandler.toUser("Beim Laden der MP3 ist ein Fehler aufgetreten, bitte versuchen Sie es sp�ter wieder", e);
			} 
			disp = request.getRequestDispatcher("/albumanzeigen.jsp");
		}
		
		//Album in Felder
		else if ((request.getParameter("uuid1") != null)) {
			System.out.println("daten in Felder");
			try {
				request.setAttribute("album",dao.read(request.getParameter("uuid1")));
				
				
			} catch (Exception e) {
				System.out.println(e);
				errorHandler.toUser("Beim Laden der MP3 ist ein Fehler aufgetreten, bitte versuchen Sie es sp�ter wieder", e);	
			} 
			disp = request.getRequestDispatcher("/albumBearbeiten.jsp");
		} 
		
		//Album bearbeiten
		else if ((request.getParameter("ident1") != null)) {
			try {
				dao = new GenericDaoImpl<DBAlbum>(DBAlbum.class, db);
				DBAlbum album = dao.read(request.getParameter("ident1"));
				
			String titel = request.getParameter("titel");
			String artist = request.getParameter("artist");
			int diskAnzahl = Integer.parseInt((request.getParameter("diskAnzahl")));
			Double preis = Double.parseDouble((request.getParameter("preis")));
			int anzahl = Integer.parseInt(request.getParameter("anzahl"));
			int trackAnzahl = Integer.parseInt(request.getParameter("trackAnzahl"));
			String label = request.getParameter("label");
			album.setAlbumTitel(titel);
			album.setArtist(artist);
			album.setNumberOfDisks(diskAnzahl);
			album.setPrice(preis);
			album.setAmount(anzahl);
			album.setNumberOfTracks(trackAnzahl);
			album.setLabel(label);
			
			dao.update(album);
			} catch (Exception e) {
				errorHandler.toUser("Beim Laden der MP3 ist ein Fehler aufgetreten, bitte versuchen Sie es sp�ter wieder", e);
			}
			disp = request.getRequestDispatcher("/controller?action=alben&alle=alleAlben");
		 }
		
		//Albumtracks anzeigen
		else if ((request.getParameter("uuid2") != null)) {
			try {
				request.setAttribute("albumTracks",dao.read(request.getParameter("uuid2")));
				
			} catch (Exception e) {
				errorHandler.toUser("Beim Laden der MP3 ist ein Fehler aufgetreten, bitte versuchen Sie es sp�ter wieder", e);
			} 
			disp = request.getRequestDispatcher("/albumTracks.jsp");
		} 
		try {
			disp.forward(request, response);
		} catch (Exception e) {
			errorHandler.toUser("Etwas mit der Weiterleitung ist schief gelaufen", e);
		}
		
	}

}

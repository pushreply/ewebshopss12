package shop.actions;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shop.dao.GenericDaoImpl;
import shop.dao.IGenericDao;
import shop.dto.DBAlbum;
import shop.util.ByteArray;

import com.db4o.ObjectContainer;

public class AlbumAction extends AbstractAction{

	@Override
	protected void process(HttpServletRequest request,
			HttpServletResponse response, ObjectContainer db) throws ServletException {
		RequestDispatcher disp = null;
		System.out.println("ich in album action");
		
		IGenericDao<DBAlbum> dao = new GenericDaoImpl<DBAlbum>(DBAlbum.class, db);
		
		try{
		if ((request.getParameter("upload") != null)) {
			disp = request
					.getRequestDispatcher("/trackhinzufuegen.jsp");
		}
		
		//Alle Alben anzegen --> Bitte Finger weg!
		if ((request.getParameter("alben") != null)) {
			request.setAttribute("Alben", dao.readAll());
			disp = request.getRequestDispatcher("/allealbenanzeigen.jsp");
		} 
		
		//Album anzeigen --> Finger Weg!
		else if ((request.getParameter("identifier") != null)) {
			request.setAttribute("album",dao.read(request.getParameter("identifier")));
			byte[] cover = dao.read(request.getParameter("identifier")).getCover();
			ByteArray.byteArrayToFile(cover,"images/cover.jpg");
			disp = request.getRequestDispatcher("/albumanzeigen.jsp");
		} 
		
		//Album editieren
		else if ((request.getParameter("identifier") != null)) {
			dao = new GenericDaoImpl<DBAlbum>(DBAlbum.class, db);
			DBAlbum album = dao.read(request.getParameter("identifier"));
		}
//			String titel = request.getParameter("titel");
//			String artist = request.getParameter("artist");
//			int date = Integer.parseInt((request.getParameter("date")));
//			String genre = request.getParameter("genre");
//			int trackanzahl = Integer.parseInt(request
//					.getParameter("trackanzahl"));
//			int diskanzahl = Integer.parseInt(request
//					.getParameter("diskanzahl"));
//			track.setTrackTitle(titel);
//			track.setTrackArtist(artist);
//			track.setTrackDate(date);
//			track.setTrackGenre(genre);
//			track.setTrackNumber(trackanzahl);
//			track.setTrackDiskNumber(diskanzahl);
//			
//			dao.update(track);
			disp = request.getRequestDispatcher("/album.jsp");
		} 

		catch (Exception e) {
			errorHandler.toUser("Beim Bearbeiten ihrer Anfrage ist ein technischer Fehler aufgetreten", e);
		}

		try {
			disp.forward(request, response);
		} catch (Exception e) {
			errorHandler.toUser("Etwas mit der Weiterleitung ist schief gelaufen", e);
		}
		
	}

}


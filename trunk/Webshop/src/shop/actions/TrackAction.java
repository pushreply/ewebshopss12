package shop.actions;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shop.dao.GenericDaoImpl;
import shop.dao.IGenericDao;
import shop.dto.DBTrack;

import com.db4o.ObjectContainer;
import com.db4o.ext.DatabaseClosedException;
import com.db4o.ext.Db4oIOException;

/**
 * 
 * @author mukunzi
 *
 */

public class TrackAction extends AbstractAction{

	@Override
	protected void process(HttpServletRequest request,
			HttpServletResponse response, ObjectContainer db) throws ServletException{
		RequestDispatcher disp = null;
		
		IGenericDao<DBTrack> dao = new GenericDaoImpl<DBTrack>(DBTrack.class, db);
		
		if ((request.getParameter("upload") != null)) {
			disp = request
					.getRequestDispatcher("/trackhinzufuegen.jsp");
		}
		//Alle Tracks anzeigen
		else if ((request.getParameter("show") != null)) {
			try {
				request.setAttribute("AlbumTracks", dao.readAll());
			}catch (Exception e) {
				errorHandler.toUser("Beim Hochladen ihrer MP3 ist en Fehler aufgetreten, bitte versuchen Sie es später wieder", e);
			}
			disp = request.getRequestDispatcher("/track.jsp");
		} 
		
		//Track- Attributen in Felder
		else if ((request.getParameter("identifier") != null)) {
			try {
				request.setAttribute("track",dao.read(request.getParameter("identifier")));
			} catch (Exception e) {
				errorHandler.toUser("Beim Laden der MP3 ist ein Fehler aufgetreten, bitte versuchen Sie es später wieder", e);
			}
			disp = request.getRequestDispatcher("/TrackEditieren.jsp");
		} 
		
		//Track editieren
		else if ((request.getParameter("TrackEditierenButton") != null)) {
			dao = new GenericDaoImpl<DBTrack>(DBTrack.class, db);
			DBTrack track=null;
			try {
				track = dao.read(request.getParameter("uuid"));
			String titel = request.getParameter("titel");
			String artist = request.getParameter("artist");
			int date = Integer.parseInt((request.getParameter("date")));
			String genre = request.getParameter("genre");
			int trackanzahl = Integer.parseInt(request
					.getParameter("trackanzahl"));
			int diskanzahl = Integer.parseInt(request
					.getParameter("diskanzahl"));
			track.setTrackTitle(titel);
			track.setTrackArtist(artist);
			track.setTrackDate(date);
			track.setTrackGenre(genre);
			track.setTrackNumber(trackanzahl);
			track.setTrackDiskNumber(diskanzahl);
			
			dao.update(track);
			disp = request.getRequestDispatcher("controller?action=track&show=true");
			} catch (Exception e) {
				errorHandler.toUser("Beim Laden der MP3 ist ein Fehler aufgetreten, bitte versuchen Sie es später wieder",
								e);
			}
		} 

		try {
			disp.forward(request, response);
		} catch (Exception e) {
			errorHandler.toUser(
					"Etwas mit der Weiterleitung ist schief gelaufen.", e);
		}
		
	}

}

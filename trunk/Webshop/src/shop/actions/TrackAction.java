package shop.actions;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shop.dao.GenericDaoImpl;
import shop.dao.IGenericDao;
import shop.dto.DBTrack;

import com.db4o.ObjectContainer;

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

//		//GIBT ES SO NICHTMEHR, WAR NUR ZUM AUSPROBIEREN AM ANFANG (Alle Tracks anzeigen)
//		else if ((request.getParameter("show") != null)) {
//			try {
//				request.setAttribute("AlbumTracks", dao.readAll());
//			}catch (Exception e) {
//				errorHandler.toUser("Beim Hochladen ihrer MP3 ist en Fehler aufgetreten, bitte versuchen Sie es später wieder", e);
//			}
//			disp = request.getRequestDispatcher("/track.jsp");
//		} 
//		
		//Track- Attributen in Felder
		if ((request.getParameter("changeTrackData") != null)) {
			try {
				request.setAttribute("track",dao.read(request.getParameter("changeTrackData")));
				request.setAttribute("albumIdentifier", request.getParameter("albumIdentifier"));
			} catch (Exception e) {
				errorHandler.toUser("Beim Laden der MP3 ist ein Fehler aufgetreten, bitte versuchen Sie es später wieder", e);
			}
			disp = request.getRequestDispatcher("/TrackEditieren.jsp");
		} 
		
		//Track editieren
		else if ((request.getParameter("updateTrack") != null)) {
			dao = new GenericDaoImpl<DBTrack>(DBTrack.class, db);
			DBTrack track=null;
			try {
				track = dao.read(request.getParameter("updateTrack"));
			String titel = request.getParameter("titel");
			String artist = request.getParameter("artist");
			int date = Integer.parseInt((request.getParameter("date")));
			String genre = request.getParameter("genre");
			int trackanzahl = Integer.parseInt(request
					.getParameter("trackAnzahl"));
			int diskanzahl = Integer.parseInt(request
					.getParameter("diskAnzahl"));
			track.setTrackTitle(titel);
			track.setTrackArtist(artist);
			track.setTrackDate(date);
			track.setTrackGenre(genre);
			track.setTrackNumber(trackanzahl);
			track.setTrackDiskNumber(diskanzahl);
			
			dao.update(track);
			disp = request.getRequestDispatcher("controller?action=album&show=" + request.getParameter("albumIdentifier"));
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

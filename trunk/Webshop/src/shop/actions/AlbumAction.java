package shop.actions;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shop.dao.GenericDaoImpl;
import shop.dao.IGenericDao;
import shop.dto.DBAlbum;

import com.db4o.ObjectContainer;

public class AlbumAction extends AbstractAction{

	@Override
	protected void process(HttpServletRequest request,
			HttpServletResponse response, ObjectContainer db) {
		RequestDispatcher disp = null;
		
		IGenericDao<DBAlbum> dao = new GenericDaoImpl<DBAlbum>(DBAlbum.class, db);
		
		if ((request.getParameter("upload") != null)) {
			disp = request
					.getRequestDispatcher("/trackhinzufuegen.jsp");
		}
		else if ((request.getParameter("showalbum") != null)) {
			request.setAttribute("Albums", dao.readAll());
			disp = request.getRequestDispatcher("/album.jsp");
		} 
		
		//Track editieren
		else if ((request.getParameter("identifier") != null)) {
			request.setAttribute("album",dao.read(request.getParameter("identifier")));
			disp = request.getRequestDispatcher("/albumEditieren.jsp");
		} 
		
		else if ((request.getParameter("AlbumEditierenButton") != null)) {
			dao = new GenericDaoImpl<DBAlbum>(DBAlbum.class, db);
			DBAlbum album = dao.read(request.getParameter("uuid"));
			
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


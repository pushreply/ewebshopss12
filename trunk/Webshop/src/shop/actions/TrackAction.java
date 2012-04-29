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

public class TrackAction extends AbstractAction{

	@Override
	protected void process(HttpServletRequest request,
			HttpServletResponse response, ObjectContainer db) {
		RequestDispatcher disp = null;
		
		IGenericDao<DBTrack> dao = new GenericDaoImpl<DBTrack>(DBTrack.class, db);
		
		if ((request.getParameter("upload") != null)) {
			disp = request
					.getRequestDispatcher("/trackhinzufuegen.jsp");
		}
		else if ((request.getParameter("show") != null)) {
			request.setAttribute("AlbumTracks", dao.readAll());
			disp = request.getRequestDispatcher("/track.jsp");
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

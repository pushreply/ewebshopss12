package shop.actions;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shop.dao.DAOAlbum;

import com.db4o.ObjectContainer;

public class SearchAction extends AbstractAction {

	protected void process(HttpServletRequest request,
			HttpServletResponse response, ObjectContainer db)
			throws ServletException {
		RequestDispatcher disp = null;
		
		DAOAlbum daoalbum = new DAOAlbum();
		
		System.out.println("der album albumseach: "+request.getParameter("action"));
		
		if(request.getParameter("action") != null)
		{
			if (request.getParameter("albumtitle") != null) {
				System.out.println(request.getParameter("albumtitle"));
				try {
					request.setAttribute("Alben", daoalbum.findAlbumbyTitle(request.getParameter("albumtitle"), db));
				} catch (Exception e) {
					errorHandler
							.toUser("Beim Laden aller MP3 ist ein Fehler aufgetreten, bitte versuchen Sie es später wieder",
									e);
				}
				disp = request.getRequestDispatcher("/allealbenanzeigen.jsp");
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

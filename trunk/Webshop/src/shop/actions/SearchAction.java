package shop.actions;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shop.dao.DAOAlbum;
import shop.dao.GenericDaoImpl;
import shop.dao.IGenericDao;
import shop.dto.DBCategory;
import shop.dto.DBKeyword;

import com.db4o.ObjectContainer;
public class SearchAction extends AbstractAction {
	

	protected void process(HttpServletRequest request,
			HttpServletResponse response, ObjectContainer db)
			throws ServletException, IOException {
		RequestDispatcher disp = null;
		
		DAOAlbum daoalbum = new DAOAlbum();
		
		System.out.println("der album albumseach: "+request.getParameter("upload"));
		
		if (request.getParameter("upload") != null) {
			IGenericDao<DBCategory> daoc = new GenericDaoImpl<DBCategory>(
					DBCategory.class, db);
			IGenericDao<DBKeyword> daok = new GenericDaoImpl<DBKeyword>(
					DBKeyword.class, db);
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
			disp = request.getRequestDispatcher("/simplesearch.jsp");

		}
		
		if(request.getParameter("action") != null)
		{
			System.out.println(request.getParameter("category"));
			if (request.getParameter("titel") != null && request.getParameter("artist").equals("") && 
					request.getParameter("category") == null && request.getParameter("keyword") == null){
				try {
					request.setAttribute("Alben", daoalbum.findAlbumbyTitle(request.getParameter("titel"), db));
				} catch (Exception e) {
					errorHandler
							.toUser("Beim Suchen ist ein Fehler aufgetreten, bitte versuchen Sie es später wieder",
									e);
				}
				disp = request.getRequestDispatcher("/allealbenanzeigen.jsp");
			}
			
			if (request.getParameter("artist") != null && request.getParameter("titel").equals("") && 
					request.getParameter("category") == null && request.getParameter("keyword") == null){
				System.out.println(request.getParameter("artist"));
				try {
					request.setAttribute("Alben", daoalbum.findAlbumbyArtist(request.getParameter("artist"), db));
				} catch (Exception e) {
					errorHandler
							.toUser("Beim Suchen ist ein Fehler aufgetreten, bitte versuchen Sie es später wieder",
									e);
				}
				disp = request.getRequestDispatcher("/allealbenanzeigen.jsp");
			}
			
			if (request.getParameter("titel") != null && request.getParameter("artist") != null && 
					request.getParameter("category") == null && request.getParameter("keyword") == null){
				try {
					request.setAttribute("Alben", daoalbum.findAlbumbyTitleArtist(request.getParameter("titel"), request.getParameter("artist"), db));
				} catch (Exception e) {
					errorHandler
							.toUser("Beim Suchen ist ein Fehler aufgetreten, bitte versuchen Sie es später wieder",
									e);
				}
				disp = request.getRequestDispatcher("/allealbenanzeigen.jsp");
			}
			if (request.getParameter("titel") != null && request.getParameter("artist") != null && 
					request.getParameter("category") != null && request.getParameter("keyword") == null){
					try {
						request.setAttribute("Alben", daoalbum.findAlbumbyTitleArtistCategory(request.getParameter("titel"), request.getParameter("artist"), request.getParameterValues("category"), db));
					} catch (Exception e) {
						errorHandler
								.toUser("Beim Suchen ist ein Fehler aufgetreten, bitte versuchen Sie es später wieder",
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

package shop.actions;

/**
 * This Action handles all needs of search
 * 
 * @author sesc0009
 * 
 */
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
			IGenericDao<DBCategory> daoc = new GenericDaoImpl<DBCategory>(DBCategory.class, db);
			IGenericDao<DBKeyword> daok = new GenericDaoImpl<DBKeyword>(DBKeyword.class, db);
			
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
		
		if(request.getParameter("search") != null)
		{
			// 1
			//Search Album by Title
			if (request.getParameter("titel") != null && request.getParameter("artist").equals("") && 
					request.getParameter("category") == null && request.getParameter("keyword") == null){
				try {
					System.out.println("ich suche nach Title");
					request.setAttribute("Alben", daoalbum.findAlbumbyTitle(request.getParameter("titel"), db));
				} catch (Exception e) {
					errorHandler
							.toUser("Beim Suchen ist ein Fehler aufgetreten, bitte versuchen Sie es später wieder",
									e);
				}
				disp = request.getRequestDispatcher("/allealbenanzeigen.jsp");
			}
			
			// 2
			// Search Album by Artist
			if (request.getParameter("artist") != null && request.getParameter("titel").equals("") && 
					request.getParameter("category") == null && request.getParameter("keyword") == null){
				System.out.println(request.getParameter("artist"));
				try {
					System.out.println("ich suche nach Artist");
					request.setAttribute("Alben", daoalbum.findAlbumbyArtist(request.getParameter("artist"), db));
				} catch (Exception e) {
					errorHandler
							.toUser("Beim Suchen ist ein Fehler aufgetreten, bitte versuchen Sie es später wieder",
									e);
				}
				disp = request.getRequestDispatcher("/allealbenanzeigen.jsp");
			}
			
			// 3
			// Search Album by Title and Artist
			if (request.getParameter("titel") != null && request.getParameter("artist") != null && 
					request.getParameter("category") == null && request.getParameter("keyword") == null){
				try {
					System.out.println("ich suche nach Title und Artist");
					request.setAttribute("Alben", daoalbum.findAlbumbyTitleArtist(request.getParameter("titel"), request.getParameter("artist"), db));
				} catch (Exception e) {
					errorHandler
							.toUser("Beim Suchen ist ein Fehler aufgetreten, bitte versuchen Sie es später wieder",
									e);
				}
				disp = request.getRequestDispatcher("/allealbenanzeigen.jsp");
			}
			
			// 4
		    // Search Album by Category
			if (request.getParameter("titel").equals("") && request.getParameter("artist").equals("") && 
					request.getParameter("category") != null && request.getParameter("keyword") == null){
					try {
						System.out.println("ich suche nach Category");
						request.setAttribute("Alben", daoalbum.findAlbumbyCategory(request.getParameterValues("category"), db));
					} catch (Exception e) {
						errorHandler
								.toUser("Beim Suchen ist ein Fehler aufgetreten, bitte versuchen Sie es später wieder",
										e);
					}
				disp = request.getRequestDispatcher("/allealbenanzeigen.jsp");
			}
			
			// 5
			// Search Album by Title and Category
			if (request.getParameter("titel") != "" && request.getParameter("artist").equals("") && 
					request.getParameter("category") != null && request.getParameter("keyword") == null){
					try {
						System.out.println("ich suche nach Title und Category");
						request.setAttribute("Alben", daoalbum.findAlbumbyTitleCategory(request.getParameter("titel"), request.getParameterValues("category"), db));
					} catch (Exception e) {
						errorHandler
								.toUser("Beim Suchen ist ein Fehler aufgetreten, bitte versuchen Sie es später wieder",
										e);
					}
				disp = request.getRequestDispatcher("/allealbenanzeigen.jsp");
			}
			
			// 6
			// Search Album by Artist and Category
			if (request.getParameter("titel").equals("") && request.getParameter("artist") != "" && 
					request.getParameter("category") != null && request.getParameter("keyword") == null){
					try {
						System.out.println("ich suche nach Artist und Category");
						request.setAttribute("Alben", daoalbum.findAlbumbyArtistCategory(request.getParameter("artist"), request.getParameterValues("category"), db));
					} catch (Exception e) {
						errorHandler
								.toUser("Beim Suchen ist ein Fehler aufgetreten, bitte versuchen Sie es später wieder",
										e);
					}
				disp = request.getRequestDispatcher("/allealbenanzeigen.jsp");
			}
			
			// 7
			// Search Album by Title, Artist and Category
			if (request.getParameter("titel") != "" && request.getParameter("artist") != "" && 
					request.getParameter("category") != null && request.getParameter("keyword") == null){
					try {
						System.out.println("ich suche nach Title Artist und Category");
						request.setAttribute("Alben", daoalbum.findAlbumbyTitleArtistCategory(request.getParameter("titel"), request.getParameter("artist"), request.getParameterValues("category"), db));
					} catch (Exception e) {
						errorHandler
								.toUser("Beim Suchen ist ein Fehler aufgetreten, bitte versuchen Sie es später wieder",
										e);
					}
				disp = request.getRequestDispatcher("/allealbenanzeigen.jsp");
			}
			
			// 8
			// Search Album by Keyword
			if (request.getParameter("titel").equals("") && request.getParameter("artist").equals("") && 
					request.getParameter("category") == null && request.getParameter("keyword") != null){
					try {
						System.out.println("ich suche nach Keyword");
						request.setAttribute("Alben", daoalbum.findAlbumbyKeyword(request.getParameterValues("keyword"), db));
					} catch (Exception e) {
						errorHandler
								.toUser("Beim Suchen ist ein Fehler aufgetreten, bitte versuchen Sie es später wieder",
										e);
					}
				disp = request.getRequestDispatcher("/allealbenanzeigen.jsp");
			}
			
			// 9
			// Search Album by Title and Keyword
			if (request.getParameter("titel") != "" && request.getParameter("artist").equals("") && 
					request.getParameter("category") == null && request.getParameter("keyword") != null){
					try {
						System.out.println("ich suche nach Title und Keyword");
						request.setAttribute("Alben", daoalbum.findAlbumbyTitleKeyword(request.getParameter("titel"),request.getParameterValues("keyword"), db));
					} catch (Exception e) {
						errorHandler
								.toUser("Beim Suchen ist ein Fehler aufgetreten, bitte versuchen Sie es später wieder",
										e);
					}
				disp = request.getRequestDispatcher("/allealbenanzeigen.jsp");
			}
			
			// 10
			// Search Album by Artist and Keyword
			if (request.getParameter("titel").equals("") && request.getParameter("artist") != "" && 
					request.getParameter("category") == null && request.getParameter("keyword") != null){
					try {
						System.out.println("ich suche nach Artist und Keyword");
						request.setAttribute("Alben", daoalbum.findAlbumbyArtistKeyword(request.getParameter("artist"), request.getParameterValues("keyword"), db));
					} catch (Exception e) {
						errorHandler
								.toUser("Beim Suchen ist ein Fehler aufgetreten, bitte versuchen Sie es später wieder",
										e);
					}
				disp = request.getRequestDispatcher("/allealbenanzeigen.jsp");
			}
			
			// 11
			// Search by Title, Artist and Keyword
			if (request.getParameter("titel") != "" && request.getParameter("artist") != "" && 
					request.getParameter("category") == null && request.getParameter("keyword") != null){
					try {
						System.out.println("ich suche nach Title Artist und Keyword");
						request.setAttribute("Alben", daoalbum.findAlbumbyTitleArtistKeyword(request.getParameter("titel"), request.getParameter("artist"), request.getParameterValues("keyword"), db));
					} catch (Exception e) {
						errorHandler
								.toUser("Beim Suchen ist ein Fehler aufgetreten, bitte versuchen Sie es später wieder",
										e);
					}
				disp = request.getRequestDispatcher("/allealbenanzeigen.jsp");
			}
			
			// 12
			// Search by Category and Keyword
			if (request.getParameter("titel").equals("") && request.getParameter("artist").equals("") && 
					request.getParameter("category") != null && request.getParameter("keyword") != null){
					try {
						System.out.println("ich suche nach Category und Keyword");
						request.setAttribute("Alben", daoalbum.findAlbumbyCategoryKeyword(request.getParameterValues("category"), request.getParameterValues("keyword"), db));
					} catch (Exception e) {
						errorHandler
								.toUser("Beim Suchen ist ein Fehler aufgetreten, bitte versuchen Sie es später wieder",
										e);
					}
				disp = request.getRequestDispatcher("/allealbenanzeigen.jsp");
			}
			
			// 13
			// Search by Title, Category and Keyword
			if (request.getParameter("titel") != "" && request.getParameter("artist").equals("") && 
					request.getParameter("category") != null && request.getParameter("keyword") != null){
					try {
						System.out.println("ich suche nach Title Category and Keyword");
						request.setAttribute("Alben", daoalbum.findAlbumbyTitleCategoryKeyword(request.getParameter("titel"), request.getParameterValues("category"), request.getParameterValues("keyword"),db));
					} catch (Exception e) {
						errorHandler
								.toUser("Beim Suchen ist ein Fehler aufgetreten, bitte versuchen Sie es später wieder",
										e);
					}
				disp = request.getRequestDispatcher("/allealbenanzeigen.jsp");
			}
			
			// 14
			// Search by Artist, Category and Keyword
			if (request.getParameter("titel").equals("") && request.getParameter("artist") != "" && 
					request.getParameter("category") != null && request.getParameter("keyword") != null){
					try {
						System.out.println("ich suche nach Artist, Category and Keyword");
						request.setAttribute("Alben", daoalbum.findAlbumbyArtistCategoryKeyword(request.getParameter("artist"), request.getParameterValues("category"), request.getParameterValues("keyword"), db));
					} catch (Exception e) {
						errorHandler
								.toUser("Beim Suchen ist ein Fehler aufgetreten, bitte versuchen Sie es später wieder",
										e);
					}
				disp = request.getRequestDispatcher("/allealbenanzeigen.jsp");
			}
			
			// 15
			// Search by Title, Artist, Category and Keyword
			if (request.getParameter("titel") != "" && request.getParameter("artist") != "" && 
					request.getParameter("category") != null && request.getParameter("keyword") != null){
					try {
						System.out.println("ich suche nach Title Artist Category und Keyword");
						request.setAttribute("Alben", daoalbum.findAlbumbyTitleArtistCategoryKeyword(request.getParameter("titel"), request.getParameter("artist"), request.getParameterValues("category"), request.getParameterValues("keyword"), db));
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

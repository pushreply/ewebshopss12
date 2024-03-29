package shop.actions;

/**
 * This Action handles all needs of search
 * 
 * @author sesc0009 Sergej Schneider
 * 
 */
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shop.dao.DAOAlbum;
import shop.dto.DBCategory;
import shop.dto.DBKeyword;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Query;
public class SearchAction implements IAction {
	


	public void process(HttpServletRequest request,
			HttpServletResponse response, ObjectContainer db)
			throws ServletException, IOException {
		RequestDispatcher disp = null;
		
		DAOAlbum daoalbum = new DAOAlbum();
		
		
		/** Feature category and keyword to the search output jsp **/
		/** With all the quary fetched category and keyword. **/
		if (request.getParameter("upload") != null) {
			
				Query cquery = db.query();
				cquery.constrain(DBCategory.class);
				ObjectSet<DBCategory> cres = cquery.execute();
			
				Query kquery = db.query();
				kquery.constrain(DBKeyword.class);
				ObjectSet<DBKeyword> kres = kquery.execute();
				
				request.setAttribute("keywordies", kres);
				request.setAttribute("categories", cres);
				disp = request.getRequestDispatcher("/simplesearch.jsp");

		}
		
		
		
		if(request.getParameter("search") != null)
		{
			// 1
			//Search Album by Title
			if (request.getParameter("titel") != ""  && request.getParameter("artist").equals("") && 
					request.getParameter("category") == null && request.getParameter("keyword") == null){
				try {
					
					request.setAttribute("Alben", daoalbum.findAlbumbyTitle(request.getParameter("titel"), db));
				} catch (Exception e) {
					errorHandler
							.toUser("Beim Suchen ist ein Fehler aufgetreten, bitte versuchen Sie es sp�ter wieder",
									e);
				}
				disp = request.getRequestDispatcher("/allealbenanzeigen.jsp");
			}
			
			// 2
			// Search Album by Artist
			if (request.getParameter("artist") != "" && request.getParameter("titel").equals("") && 
					request.getParameter("category") == null && request.getParameter("keyword") == null){
				System.out.println(request.getParameter("artist"));
				try {
					
					request.setAttribute("Alben", daoalbum.findAlbumbyArtist(request.getParameter("artist"), db));
				} catch (Exception e) {
					errorHandler
							.toUser("Beim Suchen ist ein Fehler aufgetreten, bitte versuchen Sie es sp�ter wieder",
									e);
				}
				disp = request.getRequestDispatcher("/allealbenanzeigen.jsp");
			}
			
			// 3
			// Search Album by Title and Artist
			if (request.getParameter("titel") != "" && request.getParameter("artist") != "" && 
					request.getParameter("category") == null && request.getParameter("keyword") == null){
				try {
					
					request.setAttribute("Alben", daoalbum.findAlbumbyTitleArtist(request.getParameter("titel"), request.getParameter("artist"), db));
				} catch (Exception e) {
					errorHandler
							.toUser("Beim Suchen ist ein Fehler aufgetreten, bitte versuchen Sie es sp�ter wieder",
									e);
				}
				disp = request.getRequestDispatcher("/allealbenanzeigen.jsp");
			}
			
			// 4
		    // Search Album by Category
			if (request.getParameter("titel").equals("") && request.getParameter("artist").equals("") && 
					request.getParameter("category") != null && request.getParameter("keyword") == null){
					try {
						
						request.setAttribute("Alben", daoalbum.findAlbumbyCategory(request.getParameterValues("category"), db));
					} catch (Exception e) {
						errorHandler
								.toUser("Beim Suchen ist ein Fehler aufgetreten, bitte versuchen Sie es sp�ter wieder",
										e);
					}
				disp = request.getRequestDispatcher("/allealbenanzeigen.jsp");
			}
			
			// 5
			// Search Album by Title and Category
			if (request.getParameter("titel") != "" && request.getParameter("artist").equals("") && 
					request.getParameter("category") != null && request.getParameter("keyword") == null){
					try {
						
						request.setAttribute("Alben", daoalbum.findAlbumbyTitleCategory(request.getParameter("titel"), request.getParameterValues("category"), db));
					} catch (Exception e) {
						errorHandler
								.toUser("Beim Suchen ist ein Fehler aufgetreten, bitte versuchen Sie es sp�ter wieder",
										e);
					}
				disp = request.getRequestDispatcher("/allealbenanzeigen.jsp");
			}
			
			// 6
			// Search Album by Artist and Category
			if (request.getParameter("titel").equals("") && request.getParameter("artist") != "" && 
					request.getParameter("category") != null && request.getParameter("keyword") == null){
					try {
						
						request.setAttribute("Alben", daoalbum.findAlbumbyArtistCategory(request.getParameter("artist"), request.getParameterValues("category"), db));
					} catch (Exception e) {
						errorHandler
								.toUser("Beim Suchen ist ein Fehler aufgetreten, bitte versuchen Sie es sp�ter wieder",
										e);
					}
				disp = request.getRequestDispatcher("/allealbenanzeigen.jsp");
			}
			
			// 7
			// Search Album by Title, Artist and Category
			if (request.getParameter("titel") != "" && request.getParameter("artist") != "" && 
					request.getParameter("category") != null && request.getParameter("keyword") == null){
					try {
						
						request.setAttribute("Alben", daoalbum.findAlbumbyTitleArtistCategory(request.getParameter("titel"), request.getParameter("artist"), request.getParameterValues("category"), db));
					} catch (Exception e) {
						errorHandler
								.toUser("Beim Suchen ist ein Fehler aufgetreten, bitte versuchen Sie es sp�ter wieder",
										e);
					}
				disp = request.getRequestDispatcher("/allealbenanzeigen.jsp");
			}
			
			// 8
			// Search Album by Keyword
			if (request.getParameter("titel").equals("") && request.getParameter("artist").equals("") && 
					request.getParameter("category") == null && request.getParameter("keyword") != null){
					try {
						
						request.setAttribute("Alben", daoalbum.findAlbumbyKeyword(request.getParameterValues("keyword"), db));
					} catch (Exception e) {
						errorHandler
								.toUser("Beim Suchen ist ein Fehler aufgetreten, bitte versuchen Sie es sp�ter wieder",
										e);
					}
				disp = request.getRequestDispatcher("/allealbenanzeigen.jsp");
			}
			
			// 9
			// Search Album by Title and Keyword
			if (request.getParameter("titel") != "" && request.getParameter("artist").equals("") && 
					request.getParameter("category") == null && request.getParameter("keyword") != null){
					try {
						
						request.setAttribute("Alben", daoalbum.findAlbumbyTitleKeyword(request.getParameter("titel"),request.getParameterValues("keyword"), db));
					} catch (Exception e) {
						errorHandler
								.toUser("Beim Suchen ist ein Fehler aufgetreten, bitte versuchen Sie es sp�ter wieder",
										e);
					}
				disp = request.getRequestDispatcher("/allealbenanzeigen.jsp");
			}
			
			// 10
			// Search Album by Artist and Keyword
			if (request.getParameter("titel").equals("") && request.getParameter("artist") != "" && 
					request.getParameter("category") == null && request.getParameter("keyword") != null){
					try {
						
						request.setAttribute("Alben", daoalbum.findAlbumbyArtistKeyword(request.getParameter("artist"), request.getParameterValues("keyword"), db));
					} catch (Exception e) {
						errorHandler
								.toUser("Beim Suchen ist ein Fehler aufgetreten, bitte versuchen Sie es sp�ter wieder",
										e);
					}
				disp = request.getRequestDispatcher("/allealbenanzeigen.jsp");
			}
			
			// 11
			// Search by Title, Artist and Keyword
			if (request.getParameter("titel") != "" && request.getParameter("artist") != "" && 
					request.getParameter("category") == null && request.getParameter("keyword") != null){
					try {
						request.setAttribute("Alben", daoalbum.findAlbumbyTitleArtistKeyword(request.getParameter("titel"), request.getParameter("artist"), request.getParameterValues("keyword"), db));
					} catch (Exception e) {
						errorHandler
								.toUser("Beim Suchen ist ein Fehler aufgetreten, bitte versuchen Sie es sp�ter wieder",
										e);
					}
				disp = request.getRequestDispatcher("/allealbenanzeigen.jsp");
			}
			
			// 12
			// Search by Category and Keyword
			if (request.getParameter("titel").equals("") && request.getParameter("artist").equals("") && 
					request.getParameter("category") != null && request.getParameter("keyword") != null){
					try {
						request.setAttribute("Alben", daoalbum.findAlbumbyCategoryKeyword(request.getParameterValues("category"), request.getParameterValues("keyword"), db));
					} catch (Exception e) {
						errorHandler
								.toUser("Beim Suchen ist ein Fehler aufgetreten, bitte versuchen Sie es sp�ter wieder",
										e);
					}
				disp = request.getRequestDispatcher("/allealbenanzeigen.jsp");
			}
			
			// 13
			// Search by Title, Category and Keyword
			if (request.getParameter("titel") != "" && request.getParameter("artist").equals("") && 
					request.getParameter("category") != null && request.getParameter("keyword") != null){
					try {
						request.setAttribute("Alben", daoalbum.findAlbumbyTitleCategoryKeyword(request.getParameter("titel"), request.getParameterValues("category"), request.getParameterValues("keyword"),db));
					} catch (Exception e) {
						errorHandler
								.toUser("Beim Suchen ist ein Fehler aufgetreten, bitte versuchen Sie es sp�ter wieder",
										e);
					}
				disp = request.getRequestDispatcher("/allealbenanzeigen.jsp");
			}
			
			// 14
			// Search by Artist, Category and Keyword
			if (request.getParameter("titel").equals("") && request.getParameter("artist") != "" && 
					request.getParameter("category") != null && request.getParameter("keyword") != null){
					try {
						request.setAttribute("Alben", daoalbum.findAlbumbyArtistCategoryKeyword(request.getParameter("artist"), request.getParameterValues("category"), request.getParameterValues("keyword"), db));
					} catch (Exception e) {
						errorHandler
								.toUser("Beim Suchen ist ein Fehler aufgetreten, bitte versuchen Sie es sp�ter wieder",
										e);
					}
				disp = request.getRequestDispatcher("/allealbenanzeigen.jsp");
			}
			
			// 15
			// Search by Title, Artist, Category and Keyword
			if (request.getParameter("titel") != "" && request.getParameter("artist") != "" && 
					request.getParameter("category") != null && request.getParameter("keyword") != null){
					try {

						request.setAttribute("Alben", daoalbum.findAlbumbyTitleArtistCategoryKeyword(request.getParameter("titel"), request.getParameter("artist"), request.getParameterValues("category"), request.getParameterValues("keyword"), db));
					} catch (Exception e) {
						errorHandler
								.toUser("Beim Suchen ist ein Fehler aufgetreten, bitte versuchen Sie es sp�ter wieder",
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

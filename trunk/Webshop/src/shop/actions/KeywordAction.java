package shop.actions;

import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shop.dao.GenericDaoImpl;
import shop.dao.IGenericDao;
import shop.dto.DBKeyword;

import com.db4o.ObjectContainer;

/**
 * This Action handles all needs of keywords
 * 
 * @author Andreas
 * 
 */
public class KeywordAction extends AbstractAction {

	@Override
	public void process(HttpServletRequest request,
			HttpServletResponse response, ObjectContainer db)
			throws ServletException {

		IGenericDao<DBKeyword> dao = new GenericDaoImpl<DBKeyword>(
				DBKeyword.class, db);

		// read all categories for initial JSP displaying purposes
		loadKeywords(request, dao);

		// add a new category
		String add = null;
		try {
			add = request.getParameter("addKeyword");
			if (!(add == null || add.isEmpty() || dao.existByAttribute(
					"keywordName", add))) {
				DBKeyword keyword = new DBKeyword(add);
				dao.create(keyword);
				loadKeywords(request, dao);
			}
		} catch (Exception e) {
			errorHandler
					.toUser("Das Schlüsselwort konnte aus unbekannten Gründen nicht hinzugefügt werden",
							e);
		}

		// delete a category
		String toDelete = null;
		try {
			toDelete = request.getParameter("deleteKeyword");
			if (!(toDelete == null || toDelete.isEmpty())) {
				dao.delete(toDelete);
				loadKeywords(request, dao);
			}
		} catch (Exception e) {
			errorHandler
					.toUser("Beim Löschen des Schlüsselwort ist ein Fehler aufgetreten, bitte versuchen Sie es später wieder",
							e);
		}

		// forwarding to same page again
		RequestDispatcher disp = request.getRequestDispatcher("/keyword.jsp");
		try {
			disp.forward(request, response);
		} catch (Exception e) {
			errorHandler.toUser(
					"Etwas mit der Weiterleitung ist schief gelaufen", e);
		}
	}

	/**
	 * read all categories from database and loads them into the request
	 * 
	 * @param request
	 *            the request to write the loaded files in
	 * @param dao
	 *            the DAO to read the categories from DB
	 * @throws ServletException
	 */
	private void loadKeywords(HttpServletRequest request,
			IGenericDao<DBKeyword> dao) throws ServletException {
		List<DBKeyword> keyword = null;
		try {
			keyword = dao.readAll();
		} catch (Exception e) {
			errorHandler
					.toUser("Beim Lesen des Schlüsselwort ist ein Fehler aufgetreten, bitte versuchen Sie es später wieder",
							e);
		}
		request.setAttribute("keywords", keyword);
	}
}

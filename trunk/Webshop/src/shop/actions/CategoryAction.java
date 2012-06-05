package shop.actions;

import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shop.dao.GenericDaoImpl;
import shop.dao.IGenericDao;
import shop.dto.DBCategory;

import com.db4o.ObjectContainer;

/**
 * This Action handles all needs of categories
 * 
 * @author Andreas
 * 
 */
public class CategoryAction extends AbstractAction {

	@Override
	public void process(HttpServletRequest request,
			HttpServletResponse response, ObjectContainer db)
			throws ServletException {

		IGenericDao<DBCategory> dao = new GenericDaoImpl<DBCategory>(
				DBCategory.class, db);

		// read all categories for initial JSP displaying purposes
		loadCategories(request, dao);

		// add a new category
		String add = null;
		try {
			add = request.getParameter("addCategory");
		} catch (Exception e) {
			errorHandler
					.toUser("Die Kategorie konnte aus unbekannten Gründen nicht hinzugefügt werden",
							e);
		}
		if (!(add == null || add.isEmpty() || dao.existByAttribute(
				"categoryName", add))) {
			DBCategory category = new DBCategory(add);
			dao.create(category);
			loadCategories(request, dao);
		}

		// delete a category
		String toDelete = null;
		try {
			toDelete = request.getParameter("deleteCategory");
			if (!(toDelete == null || toDelete.isEmpty())) {
				dao.delete(toDelete);
				loadCategories(request, dao);
			}
		} catch (Exception e) {
			errorHandler
					.toUser("Beim Löschen der Kategorie ist en Fehler aufgetreten, bitte versuchen Sie es später wieder",
							e);
		}

		// forwarding to same page again
		RequestDispatcher disp = request.getRequestDispatcher("/category.jsp");
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
	private void loadCategories(HttpServletRequest request,
			IGenericDao<DBCategory> dao) throws ServletException {
		List<DBCategory> categories = null;
		try {
			categories = dao.readAll();
		} catch (Exception e) {
			errorHandler
					.toUser("Beim Laden der Kategorien ist en Fehler aufgetreten, bitte versuchen Sie es später wieder",
							e);
		}
		request.setAttribute("categories", categories);
	}
}

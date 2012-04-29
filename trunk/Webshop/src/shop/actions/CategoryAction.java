package shop.actions;

import java.io.IOException;
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
			HttpServletResponse response, ObjectContainer db) {

		IGenericDao<DBCategory> dao = new GenericDaoImpl<DBCategory>(
				DBCategory.class, db);

		// read all categories for initial JSP displaying purposes
		loadCategories(request, dao);

		// add a new category
		String add = null;
		try {
			add = request.getParameter("addCategory");
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (!(add == null || add.isEmpty())) {
			DBCategory category = new DBCategory();
			category.setCategoryName(request.getParameter("addCategory").trim());
			dao.create(category);
			loadCategories(request, dao);
		}

		// delete a category
		String toDelete = null;
		try {
			toDelete = request.getParameter("deleteCategory");
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (!(toDelete == null || toDelete.isEmpty())) {
			dao.delete(toDelete);
			loadCategories(request, dao);
		}

		// forwarding to same page again
		RequestDispatcher disp = request
				.getRequestDispatcher("/category.jsp");
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

	/**
	 * read all categories from database and loads them into the request 
	 * @param request the request to write the loaded files in
	 * @param dao the DAO to read the categories from DB
	 */
	private void loadCategories(HttpServletRequest request,
			IGenericDao<DBCategory> dao) {
		List<DBCategory> categories;
		categories = dao.readAll();
		request.setAttribute("categories", categories);
	}
}

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
		List<DBCategory> categories = dao.readAll();
		request.setAttribute("categories", categories);

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
		}

		// delete a category
		String deleteId = null;
		try {
			deleteId = request.getParameter("deleteCategory");
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (!(deleteId == null || deleteId.isEmpty())) {
			dao.delete(deleteId);
		}

		// forwarding to same page again
		RequestDispatcher disp = request
				.getRequestDispatcher("/kategorieHinzufuegen.jsp");
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

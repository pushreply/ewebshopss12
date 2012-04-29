package shop.actions;

import java.io.IOException;
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
			HttpServletResponse response, ObjectContainer db) {

		IGenericDao<DBKeyword> dao = new GenericDaoImpl<DBKeyword>(
				DBKeyword.class, db);

		// read all categories for initial JSP displaying purposes
		loadKeywords(request, dao);

		// add a new category
		String add = null;
		try {
			add = request.getParameter("addKeyword");
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (!(add == null || add.isEmpty())) {
			DBKeyword keyword = new DBKeyword();
			keyword.setKeywordName(request.getParameter("addKeyword").trim());
			dao.create(keyword);
			loadKeywords(request, dao);
		}

		// delete a category
		String toDelete = null;
		try {
			toDelete = request.getParameter("deleteKeyword");
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (!(toDelete == null || toDelete.isEmpty())) {
			dao.delete(toDelete);
			loadKeywords(request, dao);
		}

		// forwarding to same page again
		RequestDispatcher disp = request
				.getRequestDispatcher("/keyword.jsp");
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
	private void loadKeywords(HttpServletRequest request,
			IGenericDao<DBKeyword> dao) {
		List<DBKeyword> keyword;
		keyword = dao.readAll();
		request.setAttribute("keywords", keyword);
	}
}

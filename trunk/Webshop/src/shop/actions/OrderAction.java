/**
 * @author mukunzi
 */

package shop.actions;

import java.util.LinkedList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shop.dao.GenericDaoImpl;
import shop.dao.IGenericDao;
import shop.dto.DBAlbum;
import com.db4o.ObjectContainer;

public class OrderAction extends AbstractAction {

	@Override
	public void process(HttpServletRequest request,
			HttpServletResponse response, ObjectContainer db)
			throws ServletException {

		RequestDispatcher disp = null;

		IGenericDao<DBAlbum> daoAlbum = new GenericDaoImpl<DBAlbum>(
				DBAlbum.class, db);

		if (request.getParameter("albumID") != null) {

			System.out.println("AlbumID des bestellten Albums:"
					+ request.getParameter("albumID"));

			DBAlbum album = new DBAlbum();

			try {
				album = daoAlbum.read(request.getParameter("albumID"));

				if (album.getAmount() != 0) {
					album.setAmount(album.getAmount() - 1);
					daoAlbum.update(album);

					@SuppressWarnings("unchecked")
					LinkedList<DBAlbum> sessinAlbumen = (LinkedList<DBAlbum>) request.getSession().getAttribute("orderedAlben");
					sessinAlbumen.add(album);
					
					request.getSession().setAttribute("orderedAlben", sessinAlbumen);

					System.out.println("Bisher bestellte Alben:"+ sessinAlbumen.size());
					
					request.setAttribute("test", null);
					disp = request.getRequestDispatcher("/controller?action=album&show=all");
					System.out.println("Das ist ok!");
				} else {
					errorHandler.toUser("Dieses Album gibt es nicht mehr in unser Lager!",null);
					disp = request.getRequestDispatcher("/error.jsp");
				}

			} catch (Exception e) {
				errorHandler.toUser("Beim Loaden eines Albums ist ein Fehler aufgetretten Bitte versuchen sie es später wieder",
								e);
			}

		}
		
	}

}

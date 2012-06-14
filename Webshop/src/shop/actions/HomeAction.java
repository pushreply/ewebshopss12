package shop.actions;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.db4o.ObjectContainer;

public class HomeAction implements IAction{

	@Override
	public void process(HttpServletRequest request,
			HttpServletResponse response, ObjectContainer db)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher disp = request.getRequestDispatcher("/index.jsp");
		try {
			disp.forward(request, response);
		} catch (Exception e) {
			errorHandler.toUser("Beim Anmelden ist ein Fehler aufgetreten, bitte versuchen Sie es später wieder", e);
		}
	}
}

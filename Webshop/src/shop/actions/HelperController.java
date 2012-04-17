package shop.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HelperController {

	protected HttpServletRequest request;
	protected HttpServletResponse response;
	
	public HelperController(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
		
	}
}

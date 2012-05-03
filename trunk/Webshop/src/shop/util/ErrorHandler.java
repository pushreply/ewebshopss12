package shop.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ErrorHandler {
	 Log logger = LogFactory.getLog(ErrorHandler.class);

	   public void toUser(String displayMessage, Exception e) throws ServletException{
		   String message =e.getMessage();
	         String uri = e.getClass().toString();
	        
	         
	         // you might want to add some more information - e.g. request parameters
	         String completeMessage = "Problem encountered while serving " + uri +
	                 "\nmessage: " + message +
	                 "\n" +
	                 getCompleteStack(e);
	         
	         // log problem; you could also trigger some kind of notification mechanism
	         logger.error(completeMessage);
		   throw new ServletException(displayMessage, new ServletException(displayMessage, e));   
	   }   
	   
	   private String getCompleteStack(Throwable t) {
	      StringWriter writer = new StringWriter();
	      PrintWriter out = new PrintWriter(writer);
	      while (t != null) {
	         t.printStackTrace(out);
	         if (t instanceof ServletException) {
	            t = ((ServletException)t).getRootCause();
	         }
	         else {
	            t = t.getCause();
	         }
	         if (t != null) {
	            out.print("Caused by: ");
	         }
	      }
	      out.flush();
	      out.close();
	      return writer.toString();
	   }
}

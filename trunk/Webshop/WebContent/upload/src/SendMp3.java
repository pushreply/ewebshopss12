import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SendMp3 extends HttpServlet {

  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

   // String fileName = (String) request.getParameter("file");
	String fileName = "X-Men First Class Trailer Music Half The Man Full Version";
	
    if (fileName == null || fileName.equals(""))
      throw new ServletException(
          "Invalid or non-existent file parameter in SendMp3 servlet.");

      if (fileName.indexOf(".mp3") == -1)
      fileName = fileName + ".mp3";

  //  String mp3Dir = getServletContext().getInitParameter("mp3-dir");
    String mp3Dir = "C:/Users/HP-Master/Desktop/FH";
    if (mp3Dir == null || mp3Dir.equals(""))
      throw new ServletException(
          "Invalid or non-existent mp3Dir context-param.");

    ServletOutputStream stream = null;
    BufferedInputStream buf = null;
    try {

      stream = response.getOutputStream();
      File mp3 = new File(mp3Dir + "/" + fileName);

      //set response headers
      response.setContentType("audio/mpeg");

      response.addHeader("Content-Disposition", "attachment; filename="
          + fileName);

      response.setContentLength((int) mp3.length());

      FileInputStream input = new FileInputStream(mp3);
      buf = new BufferedInputStream(input);
      int readBytes = 0;
      //read from the file; write to the ServletOutputStream
      while ((readBytes = buf.read()) != -1)
        stream.write(readBytes);
    } catch (IOException ioe) {
      throw new ServletException(ioe.getMessage());
    } finally {
      if (stream != null)
        stream.close();
      if (buf != null)
        buf.close();
    }
  }

  public void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    doGet(request, response);
  }
}
package shop.actions;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shop.actions.MultipartMap;
import shop.app.Controller;

@WebServlet(urlPatterns = { "/upload" })
@MultipartConfig(location = "C:\\projekt", maxFileSize = 10485760L) // 10MB.
public class UploadMusicFile {

	protected HttpServletRequest request;
	protected HttpServletResponse response;
	protected HttpServlet servlet;
	
    public UploadMusicFile(HttpServlet servlet, HttpServletRequest request,
			HttpServletResponse response) {
    	this.servlet = servlet;
    	this.request = request;
    	this.response = response;
    	
    	try {
			process(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

    protected void process(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
        MultipartMap map = new MultipartMap(request, this);
        File file = map.getFile("file");

        // Now do your thing with the obtained input.
        System.out.println("File: " + file);

        request.getRequestDispatcher("/weiter.jsp").forward(request, response);
    }

}

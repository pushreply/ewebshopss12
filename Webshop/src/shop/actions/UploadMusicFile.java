package shop.actions;

import java.awt.image.DataBufferShort;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.db4o.ObjectContainer;

import shop.actions.MultipartMap;
import shop.app.Controller;
import shop.dao.DAOTrack;
import shop.util.Trackfactory;

@WebServlet(urlPatterns = { "/upload" })
@MultipartConfig(location = "C:\\projekt", maxFileSize = 10485760L) // 10MB.
public class UploadMusicFile {

	protected HttpServletRequest request;
	protected HttpServletResponse response;
	protected HttpServlet servlet;
	protected ObjectContainer db;
	
    public UploadMusicFile(HttpServlet servlet, HttpServletRequest request,
			HttpServletResponse response, ObjectContainer db) {
    	this.servlet = servlet;
    	this.request = request;
    	this.response = response;
    	this.db = db;
    	
    	try {
			process(request, response, db);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

    protected void process(HttpServletRequest request, HttpServletResponse response, ObjectContainer db)
        throws ServletException, IOException
    {
        MultipartMap map = new MultipartMap(request, this);
        DAOTrack.insertTrack(Trackfactory.createTrack(map.getFile("file")),db);

        request.getRequestDispatcher("/weiter.jsp").forward(request, response);
    }

}

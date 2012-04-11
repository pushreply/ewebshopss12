/**
 * 
 */
package shop.util;

import org.apache.commons.fileupload.disk.DiskFileItemFactory;

import java.beans.DesignMode;
import java.io.*;
import java.util.*;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.*;
import org.apache.commons.fileupload.disk.*;
import org.apache.commons.fileupload.servlet.*;

/**
 * @author roha0001
 *
 */
public class UploadAlbumImage extends HttpServlet{
	private static final String TMP_DIR_PATH = "/";
	private File tmpDir;
	private static final String DESTINATION_DIR_PATH ="/file";
	private File destinationDir;
	
	public void init(ServletConfig config) throws ServletException{
		super.init(config);
		tmpDir = new File(TMP_DIR_PATH);
		if(!tmpDir.isDirectory()){
			throw new ServletException(DESTINATION_DIR_PATH + " is not a directory");
		}
		String realPath = getServletContext().getRealPath(DESTINATION_DIR_PATH);
		destinationDir = new File(realPath);
		if(!destinationDir.isDirectory()){
			throw new ServletException(DESTINATION_DIR_PATH + " is not a directory");
		}
	}
	
    public static String getFileExtension(String f) { //aus i-net http://groups.google.com/group/comp.lang.java.programmer/browse_thread/thread/b56ce193e50deb3e?pli=1
        String ext = ""; 
        int i = f.lastIndexOf('.'); 
        if (i > 0 &&  i < f.length() - 1) { 
            ext = f.substring(i+1).toLowerCase(); 
        } 
        return ext; 
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
    	// TODO Auto-generated method stub
    	super.doPost(req, resp);
    	PrintWriter out = resp.getWriter();
    	//resp.sendRedirect();
    	
    	DiskFileItemFactory dfi = new DiskFileItemFactory();
    	
    	//Set the size threshold
    	dfi.setSizeThreshold(20*1024*1024); //20 MB
    	
    	//set temporary directory to store the uploaded files of size above threshold
    	dfi.setRepository(tmpDir);
    	
    	ServletFileUpload uploadHandler = new ServletFileUpload(dfi);
    	
    	try {
		//parse request
    		List items = uploadHandler.parseRequest(req);
    		Iterator itr = items.iterator();
    		while(itr.hasNext()){
    			FileItem item = (FileItem) itr.next();
    			//handle form fields
    			
    			String a = getFileExtension(item.getName());
				a.toLowerCase();
    			String contentType = item.getContentType();
    			out.print(contentType);
//    			if(a.equals("jpg")||a.equals("gif") || a.equals("png") || a.equals("jpeg")){
//    				File upload = new File(); --> TODO
//    			}
    			
    		}
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    	
    }
	
}

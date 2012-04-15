/**
 * 
 */
package shop.actions;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;

import java.io.*;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.*;
import org.apache.commons.fileupload.servlet.*;

import shop.util.FileUploadListener;

import java.io.File;
import java.io.InputStream;
import java.io.IOException;
/**
 * @author roha0001
 *
 */
public class UploadMusicFile extends HttpServlet implements Servlet{
	
	/**
	 * default UID
	 */
	private static final long serialVersionUID = 1L;

	//temp path for testing purpose on local system
	/*
	 * Warning: 
	 * data is written to "/" level of local system, 
	 * I'm still trying to "correct" the path..
	 * "./" --> file is copied to eclipse folder
	 * "/" --> file is copied to system-root folder, i.e.: 'C:/'
	 */
	private static final String DEST_PATH = "./"; 
	
	public UploadMusicFile(){
		super();
	}
	
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
		
		//create file upload factory and upload servlet
		//http://commons.apache.org/fileupload/using.html
		DiskFileItemFactory factory = new DiskFileItemFactory();
		
		//set diskfactory settings
		//factory.setSizeThreshold(1024*1024*10); //10 mb 
		//factory.setRepository(null);
		
		//create a new file upload handler
		ServletFileUpload upload = new ServletFileUpload(factory);
		
		//set overall request size constraint
		upload.setFileSizeMax(1024*1024*20); //20 mb
		
		//create upload listener
		FileUploadListener listener = new FileUploadListener();
		
		//get session
		HttpSession session = req.getSession();
		
		session.setAttribute("LISTENER", listener);
		
		//upload servlet allows to set upload listener
		upload.setProgressListener(listener);
		
		List uploadedItems = null;
		FileItem fileItem = null;
		
		try {
			//iterate over all uploaded files
			uploadedItems = upload.parseRequest(req);
			Iterator iter = uploadedItems.iterator();
			while(iter.hasNext()){
				System.out.println("entering while-iterator");
				fileItem = (FileItem)iter.next();
				System.out.println("entering file item iterator");
				if(fileItem.isFormField() == false){
					System.out.println("entering if isFormField");
					if(fileItem.getSize() > 0){
						System.out.println("entering if getsize");
						//InputStream uploadedFile = null;
						File uploadedFile = null;
						String myFullFileName = fileItem.getName(), myFileName = "", slashType = (myFullFileName.lastIndexOf("\\")>0) ? "\\" : "/" ;  //windows or UNIX Path
						
						int startIndex = myFullFileName.lastIndexOf(slashType);
						
						//igonore the path and get filename
						myFileName = myFullFileName.substring(startIndex + 1, myFullFileName.length());
						System.out.println("FileName calculated");
						
						//create new File object
						uploadedFile = new File(DEST_PATH, myFileName);
						
						//write the uploaded file to the system
						fileItem.write(uploadedFile);
						System.out.println("File is written to disk.");
					}
				}
			}
			
		} catch (FileUploadException fue) {
			fue.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
//		RequestDispatcher rd = req.getRequestDispatcher("index.jsp");
//		rd.forward(req, resp);
	}

}



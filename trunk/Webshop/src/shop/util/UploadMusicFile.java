/**
 * 
 */
package shop.util;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;

import java.beans.DesignMode;
import java.io.*;
import java.util.*;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.*;
import org.apache.commons.fileupload.disk.*;
import org.apache.commons.fileupload.servlet.*;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.IOException;
/**
 * @author roha0001
 *
 */
public class UploadMusicFile extends HttpServlet implements Servlet{
	
	public UploadMusicFile(){
		super();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doGet(req, resp);
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
		//temp path for testing purpose on local system
		String filePath = "D:\\tempdata";
		
		try {
			//iterate over all uploaded files
			uploadedItems = upload.parseRequest(req);
			Iterator iter = uploadedItems.iterator();
			while(iter.hasNext()){
				fileItem = (FileItem)iter.next();
				if(fileItem.isFormField() == false){
					if(fileItem.getSize() > 0){
						InputStream uploadedFile = null;
						String myFullFileName = fileItem.getName(), myFileName = "", slashType = (myFullFileName.lastIndexOf("\\")>0) ? "\\" : "/" ;  //windows or UNIX Path
						
						int startIndex = myFullFileName.lastIndexOf(slashType);
						
						//igonore the path and get filename
						myFileName = myFullFileName.substring(startIndex + 1, myFullFileName.length());
						
						//create new File object
						//uploadedFile = new File(filePath, myFileName);
						
						//write the uploaded file to the system
						//fileItem.write(uploadedFile);
						
						//send to ByteArray
						ByteArray.inputStreamToByteArray(uploadedFile);
						uploadedFile.close();
					}
				}
			}
			
		} catch (FileUploadException fue) {
			fue.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}



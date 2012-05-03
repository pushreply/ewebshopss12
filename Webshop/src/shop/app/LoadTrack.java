package shop.app;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.db4o.ObjectContainer;

import shop.dao.DBObject;
import shop.dao.GenericDaoImpl;
import shop.dao.IGenericDao;
import shop.dto.DBTrack;
import shop.util.ErrorHandler;

/**
 * Servlet implementation class loadTrack. Handles the playback of tracks by
 * their ID. Sends Bytearray outputstream to browser.
 * 
 * @author Andreas
 */
@WebServlet("/loadTrack")
public class LoadTrack extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoadTrack() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException {

		ObjectContainer db = new DBObject().getConnection();
		IGenericDao<DBTrack> dao = new GenericDaoImpl<DBTrack>(DBTrack.class,
				db);
		DBTrack track = null;
		try {
			track = dao.read(request.getParameter("identifier"));
		} catch (InstantiationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		if (track != null) {
			
		byte[] mp3File = track.getFile();
		ServletOutputStream stream = null;
		BufferedInputStream buf = null;

		ErrorHandler errorHandler = new ErrorHandler();
		try {
			stream = response.getOutputStream();
			// set response headers
			response.setContentType("audio/mpeg");
			response.addHeader("Content-Disposition", "inline");
			response.setContentLength((int) mp3File.length);

			ByteArrayInputStream input = new ByteArrayInputStream(mp3File);
			buf = new BufferedInputStream(input);
			int readBytes = 0;

			// read from the file; write to the ServletOutputStream
			while ((readBytes = buf.read()) != -1) {
				stream.write(readBytes);
			}
		} catch (IOException ioe) {
			errorHandler.toUser(
					"Etwas mit der Weiterleitung ist schief gelaufen.", ioe);
		} finally {
			try {
				if (stream != null)
					stream.close();
				if (buf != null)
					buf.close();
			} catch (IOException e) {
				errorHandler
						.toUser("Ein technischer Fehler ist aufgetreten", e);
			}
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}

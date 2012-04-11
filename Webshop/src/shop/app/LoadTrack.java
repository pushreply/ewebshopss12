package shop.app;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.config.EmbeddedConfiguration;

import shop.dao.DAOTrack;
import shop.dto.DBTrack;

/**
 * Servlet implementation class loadTrack
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
			HttpServletResponse response) throws ServletException, IOException {

//		for test purposes
//		int trackID = new Integer(request.getParameter("trackID"));
		int trackID = 1;
		ObjectContainer db = null;
		EmbeddedConfiguration config = Db4oEmbedded.newConfiguration();
		String file = "WebshopDB.dbf";

		// Klasse Track
		config.common().objectClass(DBTrack.class).cascadeOnUpdate(true);
		config.common().objectClass(DBTrack.class).cascadeOnDelete(true);
		config.common().objectClass(DBTrack.class).objectField("file")
				.indexed(true);
		config.common().objectClass(DBTrack.class).objectField("trackTitle")
				.indexed(true);
		config.common().objectClass(DBTrack.class).objectField("trackArtist")
				.indexed(true);
		config.common().objectClass(DBTrack.class).objectField("trackDate")
				.indexed(true);
		config.common().objectClass(DBTrack.class).objectField("trackGenre")
				.indexed(true);
		config.common().objectClass(DBTrack.class).objectField("trackNumber")
				.indexed(true);
		config.common().objectClass(DBTrack.class)
				.objectField("trackDiskNumber").indexed(true);
		db = Db4oEmbedded.openFile(config, file);
		
		DBTrack track = DAOTrack.retrieveTrackByID(db, trackID);
		byte[] mp3 = track.getFile();

		ServletOutputStream stream = null;
		try {
			stream = response.getOutputStream();
			// set response headers
			response.setContentType("audio/mpeg");
			response.addHeader("Content-Disposition", "attachment; filename="
					+ trackID + ".mp3");
			response.setContentLength((int) mp3.length);
//			stream.write(mp3);
			stream.flush();
		} catch (IOException ioe) {
			throw new ServletException(ioe.getMessage());
		} finally {
			if (stream != null)
				stream.close();
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

package shop.actions;

/**
 * @author Schneider Sergej
 * 
 * 
 */

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shop.dao.GenericDaoImpl;
import shop.dao.IGenericDao;
import shop.dto.DBAlbum;
import shop.dto.DBCategory;
import shop.dto.DBKeyword;
import shop.dto.DBTrack;
import shop.util.ByteArray;
import shop.util.ErrorHandler;
import shop.util.Trackfactory;

import com.db4o.ObjectContainer;

import de.vdheide.mp3.ID3v2DecompressionException;
import de.vdheide.mp3.ID3v2IllegalVersionException;
import de.vdheide.mp3.ID3v2WrongCRCException;
import de.vdheide.mp3.NoMP3FrameException;


/** upload path definition **/
/** and File Size with 10 MB **/
@WebServlet(urlPatterns = { "/upload" })
@MultipartConfig(location = "C:\\projekt", maxFileSize = 10485760L)
// 10MB.
public class UploadMusicFile {

	/** Variablen festlegung fuer UplaodMisikFile **/
	
	protected HttpServletRequest request;
	protected HttpServletResponse response;
	protected ObjectContainer db;
	ErrorHandler errorHandler = new ErrorHandler();

	/** Konstruktor f�r UplaodMusikFiele **/
	public UploadMusicFile(HttpServletRequest request,
			HttpServletResponse response, ObjectContainer db)
			throws ServletException, IOException {
		this.request = request;
		this.response = response;
		this.db = db;

		/** **/
		if (request.getParameter("senden") != null
				&& "senden".equals(request.getParameter("senden"))) {
			albumprocess(request, response, db);
		} else if (request.getParameter("senden") != null
				&& "coverchange".equals(request.getParameter("senden"))){
				coverchange(request, response, db);
			
		} else
		{
			process(request, response, db);
		}

	}

	protected void coverchange(HttpServletRequest request,
			HttpServletResponse response, ObjectContainer db) throws ServletException {
		try {
			MultipartMap map = new MultipartMap(request, this);
			
			IGenericDao<DBAlbum> daoAlbum = new GenericDaoImpl<DBAlbum>(DBAlbum.class, db);
			
			DBAlbum dbalbum = daoAlbum.read(map.getParameter("albumid"));

			dbalbum.setCover(ByteArray.getBytesFromFile(map.getFile("coverpage")));
			
			daoAlbum.update(dbalbum);
			
			
			request.setAttribute("isAdmin", true);
			request.setAttribute("album", dbalbum);
			
			
			request.getRequestDispatcher("/albumanzeigen.jsp").forward(request,
					response);
		} catch (Exception e) {
			errorHandler.toUser("Bitte geben neues Cover ein", e);
		}
			
		
	}

	protected void process(HttpServletRequest request,
			HttpServletResponse response, ObjectContainer db)
			throws ServletException {
		MultipartMap map = null;
		DBTrack dbTrack = null;
		try {
			map = new MultipartMap(request, this);
			dbTrack = Trackfactory.createTrack(map.getFile("file"));
		} catch (ID3v2WrongCRCException e) {
			errorHandler.toUser("Falscher CRC, wasauchimmerdasist", e);
		} catch (ID3v2DecompressionException e) {
			errorHandler.toUser("Die Kompression der MP3 stimmt nicht", e);
		} catch (ID3v2IllegalVersionException e) {
			errorHandler.toUser("Die MP3 enth�lt eine falsche ID3Tag Version",
					e);
		} catch (NoMP3FrameException e) {
			errorHandler.toUser("Das ist keine g�ltige MP3 Datei", e);
		} catch (IOException e) {
			errorHandler.toUser("Bitte w�hlen Sie eine MP3 Datei aus", e);
		}

		IGenericDao<DBTrack> dao = new GenericDaoImpl<DBTrack>(DBTrack.class,
				db);
		IGenericDao<DBAlbum> daoa = new GenericDaoImpl<DBAlbum>(DBAlbum.class,
				db);
		DBAlbum dbalbum = null;
		try {
			if (map.getParameter("identifier") != null
					&& !map.getParameter("identifier").isEmpty()) {
				dbalbum = daoa.read(map.getParameter("identifier"));
				dao.create(dbTrack);
				dbalbum.setTracks(dbTrack);
				daoa.update(dbalbum);
				map.getFile("file").delete();
				request.setAttribute("album", dbalbum);
				request.setAttribute("track", dbalbum.getTracks());
			}
		} catch (Exception e) {
			errorHandler
					.toUser("Beim Lesen der MP3 ist ein Fehler passiert, bitte versuchen sie es erneut",
							e);

		}
		try {
			request.setAttribute("isAdmin", true);
			request.getRequestDispatcher("/albumanzeigen.jsp").forward(request,
					response);
		} catch (IOException e) {
			errorHandler.toUser(
					"Es tut uns leid, wir haben ein internes Problem", e);
		}
	}

	protected void albumprocess(HttpServletRequest request,
			HttpServletResponse response, ObjectContainer db)
			throws ServletException {

		try {
			MultipartMap map = new MultipartMap(request, this);

			DBAlbum dbalbum = new DBAlbum();

			LinkedList<DBCategory> categories = new LinkedList<DBCategory>();
			
			String[] category = map.getParameterValues("category");
			
			IGenericDao<DBCategory> daoc = new GenericDaoImpl<DBCategory>(DBCategory.class, db);
			
			for (int i = 0; i <= category.length - 1; i++) {
				categories.add(daoc.read(category[i]));
			}
			
			// Category in DBAlbum reinsetzen
			dbalbum.setCategories(categories);

			LinkedList<DBKeyword> keywordies = new LinkedList<DBKeyword>();
			
			String[] keyword = map.getParameterValues("keyword");

			IGenericDao<DBKeyword> daok = new GenericDaoImpl<DBKeyword>(
					DBKeyword.class, db);

			for (int i = 0; i <= keyword.length - 1; i++) {
				keywordies.add(daok.read(keyword[i]));
			}

			dbalbum.setKeywords(keywordies);

			
			dbalbum.setAlbumTitel(map.getParameter("titel"));
			dbalbum.setArtist(map.getParameter("artist"));
			dbalbum.setPrice(Double.parseDouble(map.getParameter("price")));
			dbalbum.setLabel(map.getParameter("label"));
			dbalbum.setNumberOfTracks(Integer.parseInt(map.getParameter("trackanzahl")));
			dbalbum.setNumberOfDisks(Integer.parseInt(map.getParameter("diskanzahl")));
			dbalbum.setAmount(Integer.parseInt(map.getParameter("albumanzahl")));
			dbalbum.setCover(ByteArray.getBytesFromFile(map.getFile("coverpage")));
			
			IGenericDao<DBAlbum> daoAlbum = new GenericDaoImpl<DBAlbum>(DBAlbum.class, db);
			
			
			String identifier = daoAlbum.create(dbalbum);
			
			LinkedList<DBAlbum> ldbalbum = new LinkedList<DBAlbum>();
			ldbalbum.add(dbalbum);
			for(int i = 0; i < categories.size(); i++)
			{
				categories.get(i).setAlbums(ldbalbum);
			    daoc.update(categories.get(i));
			}
			
			for(int i = 0; i < keywordies.size(); i++)
			{
				keywordies.get(i).setAlbums(ldbalbum);
				daok.update(keywordies.get(i));
			}
			
			request.setAttribute("isAdmin", true);
			request.setAttribute("album", daoAlbum.read(identifier));
			
			
			request.getRequestDispatcher("/albumanzeigen.jsp").forward(request,
					response);
		} catch (Exception e) {
			errorHandler.toUser("Bitte geben Sie eine MP3 Datei an", e);
		}
	}

}

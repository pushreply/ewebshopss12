package shop.actions;

/**
 * @author Schneider Sergej
 */

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

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

@WebServlet(urlPatterns = { "/upload" })
@MultipartConfig(location = "C:\\projekt", maxFileSize = 10485760L)
// 10MB.
public class UploadAction extends AbstractAction {

	protected HttpServletRequest request;
	protected HttpServletResponse response;
	protected ObjectContainer db;
	ErrorHandler errorHandler = new ErrorHandler();

	public UploadAction(HttpServletRequest request,
			HttpServletResponse response, ObjectContainer db)
			throws ServletException, IOException {
		this.request = request;
		this.response = response;
		this.db = db;

		System.out.println("parameter ausgabe: "
				+ request.getParameter("senden"));
		if (request.getParameter("senden") != null
				&& "senden".equals(request.getParameter("senden"))) {
			albumprocess(request, response, db);
		} else {
			System.out.println("parameter mp3: "
					+ request.getParameter("uploadFileSubmitButton"));
			process(request, response, db);
		}

	}

	protected void process(HttpServletRequest request,
			HttpServletResponse response, ObjectContainer db)
			throws ServletException {
		System.out.println("ich bin in prozess");
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
			errorHandler.toUser("Die MP3 enthält eine falsche ID3Tag Version",
					e);
		} catch (NoMP3FrameException e) {
			errorHandler.toUser("Das ist keine gültige MP3 Datei", e);
		} catch (IOException e) {
			errorHandler.toUser("Bitte wählen Sie eine MP3 Datei aus", e);
		}

		IGenericDao<DBTrack> dao = new GenericDaoImpl<DBTrack>(DBTrack.class,
				db);
		IGenericDao<DBAlbum> daoa = new GenericDaoImpl<DBAlbum>(DBAlbum.class,
				db);
		DBAlbum dbalbum = null;
		try {
			if (map.getParameter("identifier") != null && !map.getParameter("identifier").isEmpty()) {
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
			request.getRequestDispatcher("/trackhinzufuegen.jsp").forward(
					request, response);
		} catch (IOException e) {
			errorHandler.toUser(
					"Es tut uns leid, wir haben ein internes Problem", e);
		}
	}

	protected void albumprocess(HttpServletRequest request,
			HttpServletResponse response, ObjectContainer db)
			throws ServletException {
		System.out.println("ich bin albumprocess");

		try {
			MultipartMap map = new MultipartMap(request, this);
			
			DBAlbum dbalbum = new DBAlbum();
			
			List<DBCategory> categories = new LinkedList<DBCategory>();
			String[] category = map.getParameterValues("category");
			IGenericDao<DBCategory> daoc = new GenericDaoImpl<DBCategory>(DBCategory.class,db);
			for(int i = 0; i <= category.length-1; i++)
			{
				categories.add(daoc.read(category[i]));	
			}
			
			// Category in DBAlbum reinsetzen
			dbalbum.setCategories(categories);
			
			List<DBKeyword> keywordies = new LinkedList<DBKeyword>();
			String[] keyword = map.getParameterValues("keyword");
			
			
			IGenericDao<DBKeyword> daok = new GenericDaoImpl<DBKeyword>(DBKeyword.class,db);
			
			for(int i = 0; i <= keyword.length-1; i++)
			{
				keywordies.add(daok.read(keyword[i]));	
			}
			
			dbalbum.setKeywords(keywordies);
			
			dbalbum.setAlbumTitel(map.getParameter("titel"));
			dbalbum.setArtist(map.getParameter("artist"));
			dbalbum.setPrice(Double.parseDouble(map.getParameter("price")));
			dbalbum.setLabel(map.getParameter("label"));
			dbalbum.setNumberOfTracks(Integer.parseInt(map
					.getParameter("trackanzahl")));
			dbalbum.setNumberOfDisks(Integer.parseInt(map
					.getParameter("diskanzahl")));
			dbalbum.setAmount(Integer.parseInt(map.getParameter("albumanzahl")));

			dbalbum.setCover(ByteArray.getBytesFromFile(map
					.getFile("coverpage")));
			IGenericDao<DBAlbum> daoAlbum = new GenericDaoImpl<DBAlbum>(
					DBAlbum.class, db);
			daoAlbum.create(dbalbum);
			request.setAttribute("album", dbalbum);
			request.getRequestDispatcher("/trackhinzufuegen.jsp").forward(
					request, response);
		} catch (Exception e) {
			errorHandler.toUser("Bitte geben Sie eine MP3 Datei an", e);
		}
	}

}

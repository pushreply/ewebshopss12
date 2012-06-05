package shop.actions;

/**
 * @author Schneider Sergej
 * 
 * Funktion
 * Track hochladen trackadd
 * Album hochladen albumadd
 * Cover aendern coverchange
 */

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.omg.Dynamic.Parameter;

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

	/** Konstruktor für UplaodMusikFiele **/
	public UploadMusicFile(HttpServletRequest request,
			HttpServletResponse response, ObjectContainer db)
			throws ServletException, IOException {
		this.request = request;
		this.response = response;
		this.db = db;

		/** Weiter Leitung an jeweilige Bestimmung
		    Cover aendern
		    Album hochladen
		    Track hochladden **/
		// Weiterletung an Album hochladen
		if (request.getParameter("senden") != null
				&& "senden".equals(request.getParameter("senden"))) {
			albumadd(request, response, db);
			
			// Weiterleitung an Cover aendern
		} else if (request.getParameter("senden") != null
				&& "coverchange".equals(request.getParameter("senden"))){
				coverchange(request, response, db);
				
			// Weiterleitung an Track hochladen
		} else
		{
			trackadd(request, response, db);
		}

	}
	
	/**
	 * @author Sergej Schneider
	 *  
	 * Funktion Cover aendern 
	 * Bekommt {@link HttpServletRequest} {@link HttpServletResponse} und {@link ObjectContainer}
	 * mit der {@link MultipartMap} werden alle {@link Parameter} zerlegt und Bild hochgeladen.
	 * {@link IGenericDao} wird eine Verbindung zur Datenbank aufgebaut und mit der funktion daoAlbum.read wird bestimmter
	 * Album geholt die dieser ID. Setzen wir ein neuer Cover dbalbum.setCover(ByteArray.getBytesFromFile(map.getFile("coverpage")));
	 * und speichern mit der Funktion daoAlbum.update(dbalbum)
	 *  
	 * Weiterleitung an die Album einzeigen jsp
	 * 
	 * */
	protected void coverchange(HttpServletRequest request,
			HttpServletResponse response, ObjectContainer db) throws ServletException {
		try {
			
			// File Hochladen
			MultipartMap map = new MultipartMap(request, this);
			
			// Eine verbildung mit Datenbank aufbauen
			IGenericDao<DBAlbum> daoAlbum = new GenericDaoImpl<DBAlbum>(DBAlbum.class, db);
			
			// Aus Datenbank Album auslesen mit ID
			DBAlbum dbalbum = daoAlbum.read(map.getParameter("albumid"));

			// Cover ins ByteArray umwandeln und in die Klasse Albumt setzen
			dbalbum.setCover(ByteArray.getBytesFromFile(map.getFile("coverpage")));
			
			// File loeschen
			map.getFile("coverpage").delete();
			
			// Album updaten
			daoAlbum.update(dbalbum);
			
			
			request.setAttribute("isAdmin", true);
			request.setAttribute("album", dbalbum);
			
			// Weiterletung an Album einzeigen jsp
			request.getRequestDispatcher("/albumanzeigen.jsp").forward(request,
					response);
		} catch (Exception e) {
			errorHandler.toUser("Bitte geben neues Cover ein", e);
		}
			
		
	}
	
	/**
	 * @author Sergej Schneider
	 * @param request
	 * @param response
	 * @param db
	 * @throws ServletException
	 * 
	 * Funktion Track zum Album hochladen
	 * 
	 */

	protected void trackadd(HttpServletRequest request,
			HttpServletResponse response, ObjectContainer db)
			throws ServletException {
		
		// Variablen werden festgeledt
		MultipartMap map = null;
		DBTrack dbTrack = null;
		
		
		try {
			
			// Formular und File hochladen mir zerlegt
			map = new MultipartMap(request, this);
			
			// Mp3 File wird an die Klasse Trackfactory uebergeben
			// und ins Objekt Track reigesetzt
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

		// Verbindung mit Datenbank mit Track
		IGenericDao<DBTrack> dao = new GenericDaoImpl<DBTrack>(DBTrack.class,
				db);
		
		// Verbindung mit Datanbank mit Album
		IGenericDao<DBAlbum> daoa = new GenericDaoImpl<DBAlbum>(DBAlbum.class,
				db);
		
		// Leeres Albumt wird erzeugt
		DBAlbum dbalbum = null;
		
		try {
			if (map.getParameter("identifier") != null
					&& !map.getParameter("identifier").isEmpty()) {
				
				// Album nach id aus Datenbank ausgelesen
				dbalbum = daoa.read(map.getParameter("identifier"));
				
				// Neues Track wird gespeichert
				dao.create(dbTrack);
				
				// Neues Track wird zur Album hinzufuegt
				dbalbum.setTracks(dbTrack);
				
				// Album wird update
				daoa.update(dbalbum);
				
				// File wird geloescht
				map.getFile("file").delete();
				
				// Attribute werden gesetzt
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
			
			// Weiterleitung an Album anzeigen jsp
			request.getRequestDispatcher("/albumanzeigen.jsp").forward(request,
					response);
		} catch (IOException e) {
			errorHandler.toUser(
					"Es tut uns leid, wir haben ein internes Problem", e);
		}
	}
	
	/**
	 * @author Sergej Schneider
	 * @param request
	 * @param response
	 * @param db
	 * @throws ServletException
	 * 
	 * Funtion neues Album Hochladen
	 */

	protected void albumadd(HttpServletRequest request,
			HttpServletResponse response, ObjectContainer db)
			throws ServletException {

		try {
			MultipartMap map = new MultipartMap(request, this);

			// Leeres Object von Klasse Album
			DBAlbum dbalbum = new DBAlbum();

			LinkedList<DBCategory> categories = new LinkedList<DBCategory>();
			
			// Kategorie aus Parameter auslesen
			String[] category = map.getParameterValues("category");
			
			// Verbindung mit Datenbank durch Klasse Kategory
			IGenericDao<DBCategory> daoc = new GenericDaoImpl<DBCategory>(DBCategory.class, db);
			
			// Kategory auslesen
			for (int i = 0; i <= category.length - 1; i++) {
				categories.add(daoc.read(category[i]));
			}
			
			// Category in DBAlbum reinsetzen
			dbalbum.setCategories(categories);

			LinkedList<DBKeyword> keywordies = new LinkedList<DBKeyword>();
			
			// Schagwoerter auslesen aus der Parameter
			String[] keyword = map.getParameterValues("keyword");

			// Verbindung mit Datanbank durch Schlagwoerter
			IGenericDao<DBKeyword> daok = new GenericDaoImpl<DBKeyword>(
					DBKeyword.class, db);

			// Schlagwoerte auslesen
			for (int i = 0; i <= keyword.length - 1; i++) {
				keywordies.add(daok.read(keyword[i]));
			}

			// Schlagwoerter setzen
			dbalbum.setKeywords(keywordies);

			// Parameter auslesen und zur Album hinzufuegen
			dbalbum.setAlbumTitel(map.getParameter("titel"));
			dbalbum.setArtist(map.getParameter("artist"));
			dbalbum.setPrice(Double.parseDouble(map.getParameter("price")));
			dbalbum.setLabel(map.getParameter("label"));
			dbalbum.setNumberOfTracks(Integer.parseInt(map.getParameter("trackanzahl")));
			dbalbum.setNumberOfDisks(Integer.parseInt(map.getParameter("diskanzahl")));
			dbalbum.setAmount(Integer.parseInt(map.getParameter("albumanzahl")));
			dbalbum.setCover(ByteArray.getBytesFromFile(map.getFile("coverpage")));
			
			// Cover loeschen
			map.getFile("coverpage").delete();
			
			// Verbindung mit Datanbank durch Album
			IGenericDao<DBAlbum> daoAlbum = new GenericDaoImpl<DBAlbum>(DBAlbum.class, db);
			
			//Speicher neues Album in Datenbank
			String identifier = daoAlbum.create(dbalbum);
			
			LinkedList<DBAlbum> ldbalbum = new LinkedList<DBAlbum>();
			
			ldbalbum.add(dbalbum);
			
			// Album zur Kategorie hinzufuegen
			for(int i = 0; i < categories.size(); i++)
			{
				categories.get(i).setAlbums(ldbalbum);
			    daoc.update(categories.get(i));
			}
			
			// Album zur Schlagwoerter hinzufuegen
			for(int i = 0; i < keywordies.size(); i++)
			{
				keywordies.get(i).setAlbums(ldbalbum);
				daok.update(keywordies.get(i));
			}
			
			// Variablen festlegen
			request.setAttribute("isAdmin", true);
			request.setAttribute("album", daoAlbum.read(identifier));
			
			// Weiterleitung an Album anzeigen jps
			request.getRequestDispatcher("/albumanzeigen.jsp").forward(request,
					response);
		} catch (Exception e) {
			errorHandler.toUser("Bitte geben Sie eine MP3 Datei an", e);
		}
	}

}

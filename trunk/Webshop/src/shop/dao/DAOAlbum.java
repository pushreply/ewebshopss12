package shop.dao;

/**
 * @author mukunzi
 */

import java.util.LinkedList;

import shop.dto.DBAlbum;
import shop.dto.DBCategory;
import shop.dto.DBKeyword;
import shop.dto.DBTrack;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.ext.DatabaseFileLockedException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DAOAlbum {
   
	 private static final Log log = LogFactory.getLog(DAOTrack.class);
	
	/**
	 * insert a new album in the Database
	 * @param album
	 * @param db
	 */
	public void inserAlbum(DBAlbum album, ObjectContainer db) {
		
		if (log.isInfoEnabled()) {
			log.debug("ENTER inserAlbum");
		}
		
		try {
			db.store(album);
		} catch (DatabaseFileLockedException e) {
			
			if (log.isErrorEnabled()) {
				log.error("inserAlbum - DB-Fehler", e);
			}
			
		} finally {
			db.close();
			
			if(log.isDebugEnabled()){
				log.debug("LEAVE inserAlbum");
			}

		}
	}
	
	

	/**
	 * Retrieve a specific album from database
	 * @param db
	 * @param albID
	 * @return
	 */
	public DBAlbum retrieveAllAlbumByID(ObjectContainer db, int albID) {
		
		if (log.isInfoEnabled()) {
			log.debug("ENTER retrieveAllAlbumByID"+albID);
		}

		DBAlbum album = null;

		try {

			DBAlbum al = new DBAlbum(albID, null, null, null, 0, 0.0, 0, 0,
					null, null, null, null);

			ObjectSet<DBAlbum> result = db.queryByExample(al);

			album = result.next();

		} catch (DatabaseFileLockedException e) {
			if (log.isErrorEnabled()) {
				log.error("retrieveAllAlbumByID - DB-Fehler", e);
			}
		} finally {
			db.close();
		}
		
		if(log.isDebugEnabled()){
			log.debug("LEAVE retrieveAllAlbumByID");
		}

		return album;
	}
	
	

	/**
	 * Retrieve all albums from database
	 * @param db
	 * @return
	 */
	public LinkedList<DBAlbum> retrieveAllAlbums(ObjectContainer db) {
		
		if (log.isInfoEnabled()) {
			log.debug("ENTER retrieveAllAlbums");
		}

		LinkedList<DBAlbum> albums = new LinkedList<DBAlbum>();

		try {

			DBAlbum album = new DBAlbum();

			ObjectSet<DBAlbum> result = db.queryByExample(album);

			while (result.hasNext()) {
				album = result.next();
				albums.add(album);
			}
		} catch (DatabaseFileLockedException e) {
			if (log.isErrorEnabled()) {
				log.error("retrieveAllAlbums - DB-Fehler", e);
			}
		} finally {
			db.close();
		}
		
		if(log.isDebugEnabled()){
			log.debug("LEAVE retrieveAllAlbums"+albums.size());
		}

		return albums;
	}
    
	
	
	
	/**
	 * Retrieve all album's categories
	 * @param db
	 * @param albID
	 * @return
	 */
	public LinkedList<DBCategory> retrieveAlbumsCategories(ObjectContainer db, int albID) {
		
		if (log.isInfoEnabled()) {
			log.debug("ENTER retrieveAlbumsCategories"+albID);
		}
		
		LinkedList<DBCategory> categories = new LinkedList<DBCategory>();

		try {
			
			DBAlbum al = new DBAlbum(albID, null, null, null, 0, 0.0, 0, 0,
					     null, null, null, null);

			ObjectSet<DBAlbum> result = db.queryByExample(al);
            
			categories = result.next().getCategories();

		} catch (DatabaseFileLockedException e) {
			if (log.isErrorEnabled()) {
				log.error("retrieveAlbumsCategories - DB-Fehler", e);
			}
		} finally {
			db.close();
		}
		
		if(log.isDebugEnabled()){
			log.debug("LEAVE retrieveAlbumsCategories"+categories.size());
		}

		return categories;
	}
    
	
	
	
	/**
	 * Retrieve all album's keywords
	 * @param db
	 * @param albID
	 * @return
	 */
	public LinkedList<DBKeyword> retrieveAlbumsKeywords(ObjectContainer db ,int albID) {
		
		if (log.isInfoEnabled()) {
			log.debug("ENTER retrieveAlbumsKeywords");
		}

		LinkedList<DBKeyword> keywords = new LinkedList<DBKeyword>();

		try {
			
			DBAlbum al = new DBAlbum(albID, null, null, null, 0, 0.0, 0, 0,
					null, null, null, null);

			ObjectSet<DBAlbum> result = db.queryByExample(al);
            
			keywords = result.next().getKeywords();

		} catch (DatabaseFileLockedException e) {
			if (log.isErrorEnabled()) {
				log.error("retrieveAlbumsKeywords - DB-Fehler", e);
			}
		} finally {
			db.close();
		}
        
		if(log.isDebugEnabled()){
			log.debug("LEAVE retrieveAlbumsKeywords"+keywords.size());
		}
		return keywords;
	}
    
	
	
	/**
	 * Retrieve all album's tracks
	 * @param db
	 * @param albID
	 * @return
	 */
	public LinkedList<DBTrack> retrieveAlbumsTracks(ObjectContainer db ,int albID) {
		
		if (log.isInfoEnabled()) {
			log.debug("ENTER retrieveAlbumsTracks"+albID);
		}

		LinkedList<DBTrack> tracks = new LinkedList<DBTrack>();

		try {
			
			DBAlbum al = new DBAlbum(albID, null, null, null, 0, 0.0, 0, 0,
					null, null, null, null);

			ObjectSet<DBAlbum> result = db.queryByExample(al);
            
			tracks = result.next().getTracks();

		} catch (DatabaseFileLockedException e) {
			if (log.isErrorEnabled()) {
				log.error("retrieveAlbumsTracks - DB-Fehler", e);
			}
		} finally {
			db.close();
		}
		
		if(log.isDebugEnabled()){
			log.debug("LEAVE retrieveAlbumsTracks:"+tracks.size());
		}

		return tracks;

	}
	
	/**
	 * 
	 * @param db
	 * @param albID
	 * @param coverpath
	 * @param albumTitel
	 * @param artist
	 * @param numberOfDisks
	 * @param price
	 * @param amount
	 * @param numberOfTracks
	 * @param label
	 */
	public void editAlbum(ObjectContainer db, int albID,String coverpath,String albumTitel,
			String artist, int numberOfDisks, double price, int amount, int numberOfTracks,
			String label) {
		
		if (log.isInfoEnabled()) {
			log.debug("ENTER editAlbum"+albID);
		}

		try {

			DBAlbum album = new DBAlbum(albID, null, null, null, 0, 0.0, 0, 0,
					null, null, null, null);

			ObjectSet<DBAlbum> result = db.queryByExample(album);

			album = result.next();
			db.commit();
			db.commit();
			
			album.setCoverpath(coverpath);
			album.setAlbumTitel(albumTitel);
			album.setArtist(artist);
			album.setNumberOfDisks(numberOfDisks);
			album.setPrice(price);
			album.setAmount(amount);
			album.getNumberOfTracks();
            
			db.commit();

		} catch (DatabaseFileLockedException e) {
			if (log.isErrorEnabled()) {
				log.error("editAlbum - DB-Fehler", e);
			}
		} finally {
			db.close();
			
			if(log.isDebugEnabled()){
				log.debug("LEAVE editAlbum:");
			}
		}


	}

}

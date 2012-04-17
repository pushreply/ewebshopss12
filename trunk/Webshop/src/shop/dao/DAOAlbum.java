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

public class DAOAlbum {

	
	/**
	 * insert a new album in the Database
	 * @param album
	 * @param db
	 */
	public void inserAlbum(DBAlbum album, ObjectContainer db) {
		try {
			db.store(album);
		} catch (DatabaseFileLockedException e) {
			e.printStackTrace();
		} finally {
			db.close();
		}
	}
	
	

	/**
	 * Retrieve a specific album from database
	 * @param db
	 * @param albID
	 * @return
	 */
	public DBAlbum retrieveAllAlbumByID(ObjectContainer db, int albID) {

		DBAlbum album = null;

		try {

			DBAlbum al = new DBAlbum(albID, null, null, null, 0, 0.0, 0, 0,
					null, null, null, null);

			ObjectSet<DBAlbum> result = db.queryByExample(al);

			album = result.next();

		} catch (DatabaseFileLockedException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			db.close();
		}

		return album;
	}
	
	

	/**
	 * Retrieve all albums from database
	 * @param db
	 * @return
	 */
	public LinkedList<DBAlbum> retrieveAllAlbums(ObjectContainer db) {

		LinkedList<DBAlbum> linkedListDBAlbum = new LinkedList<DBAlbum>();

		try {

			DBAlbum album = new DBAlbum();

			ObjectSet<DBAlbum> result = db.queryByExample(album);

			while (result.hasNext()) {
				album = result.next();
				linkedListDBAlbum.add(album);
			}
		} catch (DatabaseFileLockedException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			db.close();
		}

		return linkedListDBAlbum;
	}
    
	
	
	
	/**
	 * Retrieve all album's categories
	 * @param db
	 * @param albID
	 * @return
	 */
	public LinkedList<DBCategory> retrieveAlbumsCategories(ObjectContainer db, int albID) {
		
		LinkedList<DBCategory> categories = new LinkedList<DBCategory>();

		try {
			
			DBAlbum al = new DBAlbum(albID, null, null, null, 0, 0.0, 0, 0,
					null, null, null, null);

			ObjectSet<DBAlbum> result = db.queryByExample(al);
            
			categories = result.next().getCategories();

		} catch (DatabaseFileLockedException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			db.close();
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

		LinkedList<DBKeyword> keywords = new LinkedList<DBKeyword>();

		try {
			
			DBAlbum al = new DBAlbum(albID, null, null, null, 0, 0.0, 0, 0,
					null, null, null, null);

			ObjectSet<DBAlbum> result = db.queryByExample(al);
            
			keywords = result.next().getKeywords();

		} catch (DatabaseFileLockedException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			db.close();
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

		LinkedList<DBTrack> tracks = new LinkedList<DBTrack>();

		try {
			
			DBAlbum al = new DBAlbum(albID, null, null, null, 0, 0.0, 0, 0,
					null, null, null, null);

			ObjectSet<DBAlbum> result = db.queryByExample(al);
            
			tracks = result.next().getTracks();

		} catch (DatabaseFileLockedException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			db.close();
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
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			db.close();
		}


	}

}
package shop.dao;

/**
 * @author mukunzi
 * @author Andreas
 */

import java.util.LinkedList;
import java.util.UUID;

import shop.dto.DBTrack;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.ext.DatabaseClosedException;
import com.db4o.ext.DatabaseFileLockedException;
import com.db4o.ext.DatabaseReadOnlyException;
import com.db4o.ext.Db4oIOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DAOTrack {

    private static final Log log = LogFactory.getLog(DAOTrack.class);

	/*
	 * insert a new track
	 */
	public static void insertTrack(DBTrack track, ObjectContainer db) {
		
		if (log.isInfoEnabled()) {
			log.debug("ENTER insertTrack");
		}
		
		track.setTrackID(UUID.randomUUID());

		try {
			db.store(track);
		} catch (DatabaseFileLockedException e) {
			if (log.isErrorEnabled()) {
				log.error("insertTrack - DB-Fehler", e);
			}
			
		} finally {
			db.close();
		}

	}
	
	
	
	
public static void editTrack(ObjectContainer db, UUID trackID , String trackTitel, String trackArtist, 
		                     int trackDate, String trackGenre, int trackNumber, int trackDiskNumber) {
		
		if (log.isInfoEnabled()) {
			log.debug("ENTER editTrack"+ trackID);
		}

		try {
			DBTrack track;

			ObjectSet<DBTrack> result = db.queryByExample(new DBTrack(trackID,
					null, null, null, 0, null, 0, 0));
			track = result.next();
			
			track.setTrackTitle(trackTitel);
			track.setTrackArtist(trackArtist);
			track.setTrackDate(trackDate);
			track.setTrackGenre(trackGenre);
			track.setTrackNumber(trackNumber);
			track.setTrackDiskNumber(trackDiskNumber);
			
			db.store(track);

		} catch (Db4oIOException e) {
			if (log.isErrorEnabled()) {
				log.error("editTrack - DB-Fehler", e);
			}
		} catch (DatabaseClosedException e) {
			if (log.isErrorEnabled()) {
				log.error("editTrack - DB-Fehler", e);
			}
		} catch (DatabaseReadOnlyException e) {
			if (log.isErrorEnabled()) {
				log.error("editTrack - DB-Fehler", e);
			}
		} finally {
			db.close();
		}

	}
	
	
	
	

	/*
	 * delete a track
	 */
	public static void deleteTrack(UUID trackID, ObjectContainer db) {
		
		if (log.isInfoEnabled()) {
			log.debug("ENTER deleteTrack"+ trackID);
		}

		try {
			DBTrack track;

			ObjectSet<DBTrack> result = db.queryByExample(new DBTrack(trackID,
					null, null, null, 0, null, 0, 0));
			track = result.next();
			db.delete(track);
			db.commit();

		} catch (Db4oIOException e) {
			if (log.isErrorEnabled()) {
				log.error("deleteTrack - DB-Fehler", e);
			}
		} catch (DatabaseClosedException e) {
			if (log.isErrorEnabled()) {
				log.error("deleteTrack - DB-Fehler", e);
			}
		} catch (DatabaseReadOnlyException e) {
			if (log.isErrorEnabled()) {
				log.error("deleteTrack - DB-Fehler", e);
			}
		} finally {
			db.close();
		}

	}
	

	/*
	 * retrieve all tracks
	 */
	public static LinkedList<DBTrack> retrieveAllTracks(ObjectContainer db) {
		
		if (log.isInfoEnabled()) {
			log.debug("ENTER retrieveAllTracks");
		}

		LinkedList<DBTrack> tracks = new LinkedList<DBTrack>();

		try {

			ObjectSet<DBTrack> result = db.queryByExample(DBTrack.class);
			DBTrack track;

			while (result.hasNext()) {
				track = result.next();
				tracks.add(track);
			}
		} catch (DatabaseFileLockedException e) {
			if (log.isErrorEnabled()) {
				log.error("retrieveAllTracks - DB-Fehler", e);
			}
		} finally {
			db.close();
		}
		
		if(log.isDebugEnabled()){
			log.debug("LEAVE retrieveAllTracks: Anzahl aller Tracks"+tracks.size());
		}

		return tracks;
	}
	

	/*
	 * Retrieve a specific track
	 */
	public static DBTrack retrieveTrackByID(ObjectContainer db, UUID trackID) {
		
		if (log.isInfoEnabled()) {
			log.debug("ENTER retrieveTrackByID"+trackID);
		}

		DBTrack track = null;

		try {

			ObjectSet<DBTrack> result = db.queryByExample(new DBTrack(trackID,
					null, null, null, 0, null, 0, 0));

			track = result.next();

		} catch (DatabaseFileLockedException e) {
			if (log.isErrorEnabled()) {
				log.error("retrieveTrackByID - DB-Fehler", e);
			}
		} finally {
			db.close();
		}
		
		if(log.isDebugEnabled()){
			log.debug("LEAVE retrieveTrackByID");
		}

		return track;
	}
}

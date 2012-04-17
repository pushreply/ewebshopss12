package shop.dao;

/**
 * @author mukunzi
 * @author Andreas
 */

import java.util.LinkedList;

import shop.dto.DBTrack;
import shop.util.Sequence;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.ext.DatabaseClosedException;
import com.db4o.ext.DatabaseFileLockedException;
import com.db4o.ext.DatabaseReadOnlyException;
import com.db4o.ext.Db4oIOException;

//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;

public class DAOTrack {

	// private static final Log log = LogFactory.getLog(DAOTrack.class);

	/*
	 * insert a new track
	 */
	public static void insertTrack(DBTrack track, ObjectContainer db) {
//		Sequence found = new Sequence(0, null);
//		found.setName("TrackSeq");
//		ObjectSet<Sequence> result = db.queryByExample(found);
//		Sequence trackSeq = result.get(0);//
		track.setTrackID(2);
//		db.store(trackSeq);

		try {
			db.store(track);
		} catch (DatabaseFileLockedException e) {
			e.printStackTrace();
		} finally {
			db.close();
		}

	}

	/*
	 * delete a track
	 */
	public static void deleteTrack(int trackID, ObjectContainer db) {

		try {
			DBTrack track;

			ObjectSet<DBTrack> result = db.queryByExample(new DBTrack(trackID,
					null, null, null, 0, null, 0, 0));
			track = result.next();
			db.delete(track);
			db.commit();

		} catch (Db4oIOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DatabaseClosedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DatabaseReadOnlyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			db.close();
		}

	}

	/*
	 * retrieve all tracks
	 */
	public static LinkedList<DBTrack> retrieveAllTracks(ObjectContainer db) {

		LinkedList<DBTrack> tracks = new LinkedList<DBTrack>();

		try {

			ObjectSet<DBTrack> result = db.queryByExample(DBTrack.class);
			DBTrack track;

			while (result.hasNext()) {
				track = result.next();
				tracks.add(track);
			}
		} catch (DatabaseFileLockedException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			db.close();
		}

		return tracks;
	}

	/*
	 * Retrieve a specific track
	 */
	public static DBTrack retrieveTrackByID(ObjectContainer db, int trackID) {

		DBTrack track = null;

		try {

			ObjectSet<DBTrack> result = db.queryByExample(new DBTrack(trackID,
					null, null, null, 0, null, 0, 0));

			track = result.next();

		} catch (DatabaseFileLockedException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			db.close();
		}

		return track;
	}
}

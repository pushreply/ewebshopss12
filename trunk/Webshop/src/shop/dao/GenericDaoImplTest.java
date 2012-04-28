package shop.dao;

import java.io.File;
import java.util.List;
import java.util.UUID;

import org.junit.Test;

import shop.dto.DBCategory;
import shop.dto.DBTrack;
import shop.util.Trackfactory;

import com.db4o.ObjectContainer;

public class GenericDaoImplTest {

	/**
	 * 
	 */
	@Test
	public void create() {
		ObjectContainer db = new DBObject().getConnection();
		GenericDaoImpl<DBTrack> underTest = new GenericDaoImpl<DBTrack>(
				DBTrack.class, db);

		DBCategory newCat = new DBCategory("testCategoryName");
		File file = new File("WebContent/images/wwm.mp3");
	
		DBTrack underTrack = Trackfactory.createTrack(file);
		
		UUID newId = underTest.create(underTrack);
		System.err.println(newId);
	}

	@Test
	public void read() {
		ObjectContainer db = new DBObject().getConnection();
		GenericDaoImpl<DBTrack> underTest = new GenericDaoImpl<DBTrack>(
				DBTrack.class, db);
		List<DBTrack> asd = underTest.readAll(db);
		System.err.println(asd.get(0).getTrackArtist());
	}

}

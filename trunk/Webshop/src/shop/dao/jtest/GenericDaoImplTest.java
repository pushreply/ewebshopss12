package shop.dao.jtest;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.junit.Test;

import shop.dao.DBObject;
import shop.dao.GenericDaoImpl;
import shop.dao.IGenericDao;
import shop.dto.DBCategory;
import shop.dto.DBTrack;
import shop.util.Trackfactory;

import com.db4o.ObjectContainer;
import com.db4o.ext.DatabaseClosedException;
import com.db4o.ext.Db4oIOException;

import de.vdheide.mp3.ID3v2DecompressionException;
import de.vdheide.mp3.ID3v2IllegalVersionException;
import de.vdheide.mp3.ID3v2WrongCRCException;
import de.vdheide.mp3.NoMP3FrameException;

public class GenericDaoImplTest {

	/**
	 * @throws NoMP3FrameException 
	 * @throws IOException 
	 * @throws ID3v2IllegalVersionException 
	 * @throws ID3v2DecompressionException 
	 * @throws ID3v2WrongCRCException 
	 * 
	 */
	@Test
	public void create() throws ID3v2WrongCRCException, ID3v2DecompressionException, ID3v2IllegalVersionException, IOException, NoMP3FrameException {
		ObjectContainer db = new DBObject().getConnection();
		IGenericDao<DBTrack> underTest = new GenericDaoImpl<DBTrack>(
				DBTrack.class, db);

		File file = new File("WebContent/images/wwm.mp3");
	
		DBTrack underTrack = Trackfactory.createTrack(file);
		
		String newId = underTest.create(underTrack);
		System.err.println(newId);
	}

	@Test
	public void read() throws Db4oIOException, DatabaseClosedException, InstantiationException, IllegalAccessException {
		ObjectContainer db = new DBObject().getConnection();
		IGenericDao<DBTrack> underTest = new GenericDaoImpl<DBTrack>(
				DBTrack.class, db);
		List<DBTrack> asd = underTest.readAll();
		System.err.println(asd.get(0).getTrackArtist());
	}

}

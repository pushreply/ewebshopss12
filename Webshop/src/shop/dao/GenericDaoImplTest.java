package shop.dao;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import shop.dto.DBAlbum;
import shop.dto.DBCategory;
import shop.dto.DBCustomer;
import shop.dto.DBKeyword;

import com.db4o.ObjectContainer;
import com.db4o.ext.DatabaseClosedException;
import com.db4o.ext.Db4oIOException;

import de.vdheide.mp3.ID3v2DecompressionException;
import de.vdheide.mp3.ID3v2IllegalVersionException;
import de.vdheide.mp3.ID3v2WrongCRCException;
import de.vdheide.mp3.NoMP3FrameException;

/**
 * @author Andreas
 *
 */
public class GenericDaoImplTest {

	
	private ObjectContainer db;
	
	@Before
	public void before() {
		db = new DBObject().getConnection();
	}

	@After
	public void after() {
		db.close();
	}

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
		IGenericDao<DBCategory> underTest = new GenericDaoImpl<DBCategory>(
				DBCategory.class, db);
		String uuid = underTest.create(new DBCategory("newCategory" + Math.random()));
		assertNotNull(uuid);
		assertNotSame(uuid,"");
	}

	@Test
	public void read() throws Db4oIOException, DatabaseClosedException, InstantiationException, IllegalAccessException {
		IGenericDao<DBCategory> underTest = new GenericDaoImpl<DBCategory>(
				DBCategory.class, db);
		List<DBCategory> allCategories = underTest.readAll();
		String id = allCategories.get(0).getIdentifier().toString();
		assertNotNull(underTest.read(id));
	}
	
	@Test
	public void delete() throws Db4oIOException, DatabaseClosedException, InstantiationException, IllegalAccessException {
		IGenericDao<DBCategory> underTest = new GenericDaoImpl<DBCategory>(
				DBCategory.class, db);
		List<DBCategory> allCategories = underTest.readAll();
		String id = allCategories.get(allCategories.size()-1).getIdentifier().toString();
		underTest.delete(id);
		//no error = successfully tested
	}
	
	@Test
	public void readAll() throws Db4oIOException, DatabaseClosedException, InstantiationException, IllegalAccessException{
		IGenericDao<DBAlbum> underTest = new GenericDaoImpl<DBAlbum>(
				DBAlbum.class, db);
		List<DBAlbum> alben = underTest.readAll();
		assertNotNull(alben);
		assertNotNull(alben.get(0));
	}
	
	@Test
	public void testExistByAttributes_ONE_DOES_EXIST() {
		IGenericDao<DBKeyword> underTest = new GenericDaoImpl<DBKeyword>(DBKeyword.class, db);
		assertTrue(underTest.existByAttributes("keywordName", "Sommer 2012"));
	}
	
	@Test
	public void testExistByAttributes_ONE_DOES_NOT_EXIST() {
		IGenericDao<DBKeyword> underTest = new GenericDaoImpl<DBKeyword>(DBKeyword.class, db);
		assertFalse(underTest.existByAttributes("keywordName", "dieses keyword sollte nicht existieren"));
	}
	
	@Test
	public void testExistByAttributes_WRONG_ATTRIBUTENAME() {
		IGenericDao<DBKeyword> underTest = new GenericDaoImpl<DBKeyword>(DBKeyword.class, db);
		assertFalse(underTest.existByAttributes("", "Admin"));
	}
	
	@Test
	public void testExistByAttributes_TWO_DOES_EXIST() {
		IGenericDao<DBCustomer> underTest = new GenericDaoImpl<DBCustomer>(DBCustomer.class, db);
		assertTrue(underTest.existByAttributes("username", "Admin", "password", "Admin"));
	}
	
	@Test
	public void testExistByAttributes_TWO_WRONG_VALUE_1() {
		IGenericDao<DBCustomer> underTest = new GenericDaoImpl<DBCustomer>(DBCustomer.class, db);
		assertFalse(underTest.existByAttributes("username", "ungültigerName", "password", "Admin"));
	}
	
	@Test
	public void testExistByAttributes_TWO_WRONG_VALUE_2() {
		IGenericDao<DBCustomer> underTest = new GenericDaoImpl<DBCustomer>(DBCustomer.class, db);
		assertFalse(underTest.existByAttributes("username", "Admin", "password", "ungültigesPasswort"));
	}
	
	@Test
	public void testExistByAttributes_TWO_WRONG_VALUE_BOTH() {
		IGenericDao<DBCustomer> underTest = new GenericDaoImpl<DBCustomer>(DBCustomer.class, db);
		assertFalse(underTest.existByAttributes("username", "ungültigerName", "password", "ungültigesPasswort"));
	}
	
	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void testExistByAttributes_ODD_PARAMETERS() {
		IGenericDao<DBCustomer> underTest = new GenericDaoImpl<DBCustomer>(DBCustomer.class, db);
		underTest.existByAttributes("username", "Admin", "password", "Admin", "zusätzlicher nicht erlaubter Parameter");
	}
}

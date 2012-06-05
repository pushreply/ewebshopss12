package shop.dao.jtest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import shop.dao.DBObject;
import shop.dao.GenericDaoImpl;
import shop.dao.IGenericDao;
import shop.dto.DBCategory;
import shop.dto.DBKeyword;

import com.db4o.ObjectContainer;
import com.db4o.ext.DatabaseClosedException;
import com.db4o.ext.Db4oIOException;
import com.db4o.foundation.ChainedRuntimeException;

/**
 * 
 * @author Andreas
 * 
 */
public class DAOCategoryTest {

	ObjectContainer db;

	@Before
	public void before() {
		db = new DBObject().getConnection();
	}

	@After
	public void after() {
		db.close();
	}

	@Test
	public void create() {
		DBCategory newCat = new DBCategory("testCategoryName");
		IGenericDao<DBCategory> underTest = new GenericDaoImpl<DBCategory>(
				DBCategory.class, db);
		String newId = underTest.create(newCat);
		System.err.println(newId);
	}

	@Test
	public void asdad() {
		IGenericDao<DBCategory> underTest = new GenericDaoImpl<DBCategory>(
				DBCategory.class, db);
		List<DBCategory> listCategories = new LinkedList<DBCategory>();
		try {
			listCategories = underTest.readAll();
		} catch (Db4oIOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DatabaseClosedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		DBCategory item = listCategories.get(3);
		System.err.println("ist drin " + listCategories.contains(item));
		DBCategory item2 = new DBCategory("asdasd");
		System.err.println("asdasd ist drin " + listCategories.contains(item2));
		DBCategory item3 = new DBCategory("HipHop");
		System.err.println("HipHop ist drin " + listCategories.contains(item3));
	}

	@Test
	public void readAll() throws ChainedRuntimeException,
			DatabaseClosedException, InstantiationException,
			IllegalAccessException {
		IGenericDao<DBCategory> dao = new GenericDaoImpl<DBCategory>(
				DBCategory.class, db);
		List<DBCategory> asd = dao.readAll();
		for (DBCategory dbCategory : asd) {
			System.err.println(dbCategory.getCategoryName());
		}
	}

	@Test
	public void read() throws InstantiationException, IllegalAccessException {
		GenericDaoImpl<DBCategory> underTest = new GenericDaoImpl<DBCategory>(
				DBCategory.class, db);
		DBCategory category = underTest
				.read("be707880-3d68-4a35-9b10-00cc7ccd7142");
		System.err.println(category.getCategoryName());

	}
}

package shop.dao.jtest;

import java.util.List;
import java.util.UUID;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import shop.dao.DBObject;
import shop.dao.GenericDaoImpl;
import shop.dao.IGenericDao;
import shop.dto.DBCategory;

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
	public void before(){
		db = new DBObject().getConnection();
	}
	
	@After
	public void after(){
		db.close();
	}
	
//	@Test
//	public void test() {
//
//		DBCategory newCat = new DBCategory("asd");
//		DAOCategory.insertCategory(newCat, db);
//	}
//
//	@Test
//	public void test2() {
//		ObjectSet<DBCategory> categories = DAOCategory.retrieveAllCategories(db);
//		for (int i = 0; i < categories.size(); i++) {
//			DBCategory cat = (DBCategory) categories.get(i);
//			String out = cat.getCategoryName();
//			System.err.println(out);
//		}
//	}
	
	@Test
	public void create() {
		DBCategory newCat = new DBCategory("testCategoryName");
		IGenericDao<DBCategory> underTest = new GenericDaoImpl<DBCategory>(DBCategory.class,db);
		String newId = underTest.create(newCat);
		System.err.println(newId);
	}

	@Test
	public void readAll() throws ChainedRuntimeException, DatabaseClosedException, InstantiationException, IllegalAccessException {
		IGenericDao<DBCategory> dao = new GenericDaoImpl<DBCategory>(
				DBCategory.class, db);
		List<DBCategory> asd = dao.readAll();
		for (DBCategory dbCategory : asd) {
			System.err.println(dbCategory.getCategoryName());
		}
	}
	
	@Test
	public void read() throws InstantiationException, IllegalAccessException{
		GenericDaoImpl<DBCategory> underTest = new GenericDaoImpl<DBCategory>(
				DBCategory.class, db);
		DBCategory category = underTest.read("9a73c6e2-f7ac-49e4-945b-486916c26e22");
		System.err.println(category.getCategoryName());
	}
}

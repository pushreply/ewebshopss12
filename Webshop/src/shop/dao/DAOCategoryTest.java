package shop.dao;

import static org.junit.Assert.*;

import java.io.File;
import java.util.List;
import java.util.UUID;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import shop.dto.DBCategory;
import shop.dto.DBKeyword;
import shop.dto.DBTrack;
import shop.util.Trackfactory;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.sun.xml.internal.fastinfoset.algorithm.UUIDEncodingAlgorithm;

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
		UUID newId = underTest.create(newCat);
		System.err.println(newId);
	}

	@Test
	public void readAll() {
		IGenericDao<DBCategory> underTest = new GenericDaoImpl<DBCategory>(
				DBCategory.class, db);
		List<DBCategory> asd = underTest.readAll();
		for (DBCategory dbCategory : asd) {
			System.err.println(dbCategory.getCategoryName());
		}
	}
	
	@Test
	public void read(){
		GenericDaoImpl<DBCategory> underTest = new GenericDaoImpl<DBCategory>(
				DBCategory.class, db);
		DBCategory category = underTest.read(UUID.fromString("baaf25f2-0118-4435-907d-a0bd0559425d"));
		System.err.println(category.getCategoryName());
	}
}

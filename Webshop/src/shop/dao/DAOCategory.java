package shop.dao;

/**
 * @author mukunzi
 */

import java.util.LinkedList;
import java.util.UUID;

import shop.dto.DBCategory;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.ext.DatabaseClosedException;
import com.db4o.ext.DatabaseFileLockedException;
import com.db4o.ext.DatabaseReadOnlyException;
import com.db4o.ext.Db4oIOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DAOCategory {

	private static final Log log = LogFactory.getLog(DAOTrack.class);

	/*
	 * insert a new category
	 */
	public static void insertCategory(DBCategory category, ObjectContainer db) {
		category.setIdentifier(UUID.randomUUID());
	
		try {
			db.store((DBCategory) category);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.close();
		}
	}

	/*
	 * insert a new category
	 */
	public static void deleteCategory(UUID identifier, ObjectContainer db) {

		if (log.isInfoEnabled()) {
			log.debug("ENTER deleteCategory");
		}

		try {
			DBCategory category = new DBCategory();
			category.setIdentifier(identifier);
			ObjectSet<DBCategory> result = db.queryByExample(category);
			category = result.next();
			db.delete(category);
			db.commit();

		} catch (Db4oIOException e) {
			if (log.isErrorEnabled()) {
				log.error("deleteCategory - DB-Fehler", e);
			}
		} catch (DatabaseClosedException e) {
			if (log.isErrorEnabled()) {
				log.error("deleteCategory - DB-Fehler", e);
			}
		} catch (DatabaseReadOnlyException e) {
			if (log.isErrorEnabled()) {
				log.error("deleteCategory - DB-Fehler", e);
			}
		} finally {
			db.close();

			if (log.isDebugEnabled()) {
				log.debug("LEAVE deleteCategory");
			}

		}

	}

	/*
	 * update an existing category
	 */
	public static void updateCategory(UUID catID, ObjectContainer db,
			String categoryName) {
		if (log.isInfoEnabled()) {
			log.debug("ENTER updateCategory" + catID);
		}

		try {
			DBCategory category = new DBCategory();
			category.setIdentifier(catID);
			ObjectSet<DBCategory> result = db.queryByExample(category);
			category = result.next();
			category.setCategoryName(categoryName);
			db.store(category);

		} catch (Db4oIOException e) {
			if (log.isErrorEnabled()) {
				log.error("updateCategory - DB-Fehler", e);
			}
		} catch (DatabaseClosedException e) {
			if (log.isErrorEnabled()) {
				log.error("updateCategory - DB-Fehler", e);
			}
		} catch (DatabaseReadOnlyException e) {
			if (log.isErrorEnabled()) {
				log.error("updateCategory - DB-Fehler", e);
			}
		} finally {
			db.close();

			if (log.isDebugEnabled()) {
				log.debug("LEAVE updateCategory");
			}

		}

	}

	/*
	 * retrieve all categories
	 */
	public static ObjectSet<DBCategory> retrieveAllCategories(
			ObjectContainer db) {
		ObjectSet<DBCategory> result=null;

		try {
			result = db.queryByExample(DBCategory.class);
			return result;
		} catch (DatabaseFileLockedException e) {
			e.printStackTrace();
		} finally {
			db.close();
		}



		return result;
	}
}

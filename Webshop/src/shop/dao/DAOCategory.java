package shop.dao;

/**
 * @author mukunzi
 */

import java.util.LinkedList;

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
	public static void insertCategory(DBCategory category, ObjectContainer db)
	{	
		
		if (log.isInfoEnabled()) {
			log.debug("ENTER insertCategory");
		}
		
		try {
			db.store(category);
		}
		catch(DatabaseFileLockedException e)
		{
			if (log.isErrorEnabled()) {
				log.error("insertCategory - DB-Fehler", e);
			}
		}
		finally{
			db.close();
			
			if(log.isDebugEnabled()){
				log.debug("LEAVE insertCategory");
			}
			
		}
	}
	
	
	
	/*
	 * insert a new category
	 */
	public static void deleteCategory(int catID, ObjectContainer db)
	{	
		
		if (log.isInfoEnabled()) {
			log.debug("ENTER deleteCategory");
		}
		
		try {
			DBCategory category; 
			
			 ObjectSet<DBCategory> result = db.queryByExample(new DBCategory(catID, null));			
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
		}finally{
			db.close();
			
			if(log.isDebugEnabled()){
				log.debug("LEAVE deleteCategory");
			}
			
		}
		
	}
	
	
	
	/*
	 * update an existing category
	 */
	public static void updateCategory(int catID, ObjectContainer db, String categoryName)
	{
		if (log.isInfoEnabled()) {
			log.debug("ENTER updateCategory"+catID);
		}
		
		try {
			DBCategory category; 
			
			 ObjectSet<DBCategory> result = db.queryByExample(new DBCategory(catID, null));			
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
		}finally{
			db.close();
			
			if(log.isDebugEnabled()){
				log.debug("LEAVE updateCategory");
			}
			
		}
		
	}
	
	
	
	/*
	 * retrieve all categories
	 */
	public static LinkedList<DBCategory> retrieveAllCategories(ObjectContainer db) {
		
		if (log.isInfoEnabled()) {
			log.debug("ENTER retrieveAllCategories");
		}
		
		LinkedList<DBCategory> categories= new LinkedList<DBCategory>();
		
		try {
			
			DBCategory category = new DBCategory();
			
			ObjectSet<DBCategory> result = db.queryByExample(DBCategory.class);
			
			while(result.hasNext()) {
				category = result.next();
				categories.add(category);
			}
		}
		catch (DatabaseFileLockedException e) {
			if (log.isErrorEnabled()) {
				log.error("retrieveAllCategories - DB-Fehler", e);
			}
		}
		finally {
			db.close();
		}
		
		if(log.isDebugEnabled()){
			log.debug("LEAVE retrieveAllCategories" +categories.size());
		}
		
		return categories;
	}
}

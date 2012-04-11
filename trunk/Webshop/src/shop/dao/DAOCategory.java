package shop.dao;

/**
 * @author mukunzi
 */

import java.util.LinkedList;

import shop.dto.DBAlbum;
import shop.dto.DBCategory;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.ext.DatabaseClosedException;
import com.db4o.ext.DatabaseFileLockedException;
import com.db4o.ext.DatabaseReadOnlyException;
import com.db4o.ext.Db4oIOException;

public class DAOCategory {
	
	
	/*
	 * insert a new category
	 */
	public void insertCategory(DBCategory category, ObjectContainer db)
	{	
		try {
			db.store(category);
		}
		catch(DatabaseFileLockedException e)
		{
			e.printStackTrace();
		}
		finally{
			db.close();
		}
	}
	
	
	
	/*
	 * insert a new category
	 */
	public void deleteCategory(int catID, ObjectContainer db)
	{	
		try {
			DBCategory category; 
			
			 ObjectSet<DBCategory> result = db.queryByExample(new DBCategory(catID, null,null));			
			 category = result.next();
			 db.delete(category);
			 
		} catch (Db4oIOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DatabaseClosedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DatabaseReadOnlyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			db.close();
		}
		
	}
	
	
	
	/*
	 * update an existing category
	 */
	public void updateCategory(int catID, ObjectContainer db, String categoryName)
	{
		try {
			DBCategory category; 
			
			 ObjectSet<DBCategory> result = db.queryByExample(new DBCategory(catID, null,null));			
			 category = result.next();
			 category.setCategoryName(categoryName);
			 db.store(category);
			 
		} catch (Db4oIOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DatabaseClosedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DatabaseReadOnlyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			db.close();
		}
		
	}
	
	
	
	/*
	 * retrieve all category's album
	 */
	public LinkedList<DBAlbum> RetrieveAllCategorysAlbums(ObjectContainer db, int catID){
		
		LinkedList<DBAlbum> albums = new LinkedList<DBAlbum>();

		try {

			ObjectSet<DBCategory> result = db.queryByExample(new DBCategory(catID, null, null));
            
			albums = result.next().getAlbums();

		} catch (DatabaseFileLockedException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			db.close();
		}

		return albums;
		
	}
	
	
	/*
	 * retrieve all categories
	 */
	public LinkedList<DBCategory> retrieveAllCategories(ObjectContainer db) {
		
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
			// TODO: handle exception
			e.printStackTrace();
		}
		finally {
			db.close();
		}
		
		return categories;
	}
}

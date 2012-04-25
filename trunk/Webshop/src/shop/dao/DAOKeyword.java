package shop.dao;

/**
 * @author Benjamin, mukunzi
 */

import java.util.List;

import shop.dto.DBKeyword;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.ext.DatabaseClosedException;
import com.db4o.ext.DatabaseFileLockedException;
import com.db4o.ext.DatabaseReadOnlyException;
import com.db4o.ext.Db4oIOException;




/*
 * insert a new keyword
 */
public class DAOKeyword {
	
	public static void insertKeyword(DBKeyword keyword, ObjectContainer db)
	{	
		try {
			db.store(keyword);
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
	 * delete a specific keyword
	 */
	public static void deleteKeyword(int keyID, ObjectContainer db){
		
		try {
			DBKeyword keyword; 
			
			 ObjectSet<DBKeyword> result = db.queryByExample(new DBKeyword(keyID, null));			
			 keyword = result.next();
			 db.delete(keyword);
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
		}finally{
			db.close();
		}
		
	}
	
	
	/*
	 * edit a specific keyword
	 */
	public static void editKeyword(int keyID, String newKeywordName, ObjectContainer db){
		
		DBKeyword keyword; 
		
		try {
			ObjectSet<DBKeyword> result = db.queryByExample(new DBKeyword(keyID, null));			
			keyword = result.next();
			keyword.setKeywordName(newKeywordName);
			db.store(keyword);
			
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
	gibt eine Liste aller Keywords zurück
	 */
	public static ObjectSet<DBKeyword> retrieveAllKeywords(ObjectContainer db)
	{
		ObjectSet<DBKeyword> result = null;
		try
		{
			result = db.queryByExample(DBKeyword.class);
			return result;
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		return result;
	}
}
